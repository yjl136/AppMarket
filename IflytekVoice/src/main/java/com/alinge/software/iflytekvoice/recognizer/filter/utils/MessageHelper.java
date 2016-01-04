package com.alinge.software.iflytekvoice.recognizer.filter.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

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
     * @param user
     * @param content
     */
    public void sendMesaage(Context context,String user,String content){
        Uri uri=Uri.parse("smsto:"+user);
        Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
        context.startActivity(intent);
    }
    public Map<String,String> queryUser(Context context,String user){
        Map<String,String> phones=new HashMap<String,String>();
      ContentResolver cr= context.getContentResolver();
       Cursor cursor= cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,  null);
       while(cursor.moveToNext()){
            int contactIdIndex=cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId=cursor.getString(contactIdIndex);
            int displayNameIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String displayName=cursor.getString(displayNameIndex);
            int phoneNumIndex=cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            int phoneNum=cursor.getInt(phoneNumIndex);
            if(phoneNum>0){
                String where= ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId;
                Cursor phoneCursor= cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,where,null,null);
                if(phoneCursor.moveToFirst()){
                    int numIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String phone=cursor.getString(numIndex);
                    phones.put(displayName,phone);
                }
                phoneCursor.close();
            }

        }
        if(cursor!=null && !cursor.isClosed()){
            cursor.close();
        }
        return phones;
    }
}
