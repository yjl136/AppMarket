package com.alinge.software.iflytekvoice.recognizer.filter.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.Toast;
import com.alinge.software.iflytekvoice.utils.LogUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class MessageHelper {
    /**
     * 给某人发短信
     *
     * @param user
     * @param content
     */
    public static void sendMesaage(Context context, String user, String content) {
        Map<String, String> numbers = queryUser(context, user);
        String phone = numbers.get("vnd.android.cursor.item/phone_v2");
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, user + "不在您的联系人里", Toast.LENGTH_SHORT).show();
        } else {
            goToSmsActivity(context, content, phone);
        }

    }

    /**
     * 查看短信list
     * @param context
     */
    public static void viewMessage(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.mms", "com.android.mms.ui.ConversationList");
        context.startActivity(intent);
    }

    /**
     * 针对没有指定user的
     *
     * @param context
     */
    public static void sendMessage(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");
        context.startActivity(intent);
    }

    private static void goToSmsActivity(Context context, String content, String phone) {
        Uri uri = Uri.parse("smsto:" + phone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * 通过人名查找电话号码
     *
     * @param context
     * @param user    联系人名
     * @return 一个人有多个手机号
     */
    public static Map<String, String> queryUser(Context context, String user) {
        Map<String, String> numbers = new HashMap<String, String>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.RawContacts.CONTENT_URI, null, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + "=?", new String[]{user.toUpperCase()}, null, null);
        if (cursor.moveToFirst()) {
            int contactIdIndex = cursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID);
            int contactId = cursor.getInt(contactIdIndex);
            LogUtils.info(null, "contactId:" + contactId);
            Cursor dataCursor = cr.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.CONTACT_ID + "=?", new String[]{String.valueOf(contactId)}, null, null);
            while (dataCursor.moveToNext()) {
                int mimetypeIndex = dataCursor.getColumnIndex(ContactsContract.Data.MIMETYPE);
                String mimetype = dataCursor.getString(mimetypeIndex);
                int data1Index = dataCursor.getColumnIndex(ContactsContract.Data.DATA1);
                String number = dataCursor.getString(data1Index);
                numbers.put(mimetype, number);
            }
            closeCursor(dataCursor);

        }
        closeCursor(cursor);
        return numbers;
    }

    /**
     * 输出相应的列名和值
     *
     * @param cursor
     */
    private static void printColmnName(Cursor cursor) {
        while (cursor.moveToNext()) {
            int count = cursor.getColumnCount();
            LogUtils.info(null, "-----------------start--------------------");
            for (int index = 0; index < count; index++) {
                String columnName = cursor.getColumnName(index);
                Object values = null;
                int type = cursor.getType(index);
                if (type == Cursor.FIELD_TYPE_FLOAT) {
                    values = cursor.getFloat(index);
                } else if (type == Cursor.FIELD_TYPE_INTEGER) {
                    values = cursor.getInt(index);
                } else if (type == Cursor.FIELD_TYPE_STRING) {
                    values = cursor.getString(index);
                }
                LogUtils.info(null, columnName + " = " + values);
            }
            LogUtils.info(null, "-------------------end--------------------");
        }
        cursor.moveToFirst();
    }

    private static void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
