package com.alinge.software.market;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alinge.software.market.net.VolleyUtils;
import com.alinge.software.market.view.AsycImageView;

/**
 * 作者： yejianlin
 * 日期：2016/2/4
 * 作用：
 */
public class AppDetailActivity extends AppCompatActivity {
    private final String CONTENT_URI = "content://downloads/my_downloads";
    private AsycImageView mImageView;
    private ProgressBar pb;
    private Button downBt;
    private DownloadManager downloadManager;
    private long downId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mImageView = (AsycImageView) findViewById(R.id.appIcon);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(1);

        downBt = (Button) findViewById(R.id.downBt);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        getContentResolver().registerContentObserver(Uri.parse(CONTENT_URI), true, new DownloadInfoChange(null));
        final String downUrl = getIntent().getStringExtra("downurl");
        String appUrl = getIntent().getStringExtra("appicon");
        final String packageName = getIntent().getStringExtra("package");
        VolleyUtils.display(mImageView, appUrl);
        pb.setMax(100);
        downBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downUrl));
                request.setShowRunningNotification(true);
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.getDownloadCacheDirectory().getAbsolutePath(), packageName + ".apk");
                request.setTitle(downUrl);
                try {
                    downId = downloadManager.enqueue(request);
                } catch (Exception ex) {
                    Log.e("2/4", "message:" + ex.getMessage());
                }

            }
        });
    }

    private class DownloadInfoChange extends ContentObserver {
        public DownloadInfoChange(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            queryDownloadInfo();
        }
    }

    private void queryDownloadInfo() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downId);
        Cursor cursor = downloadManager.query(query);
        if (cursor == null || !cursor.moveToFirst()) {
            return;
        }
        int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
        int totalSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
        int downSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        int progress=(int)(downSize*1.0/totalSize*100);
        pb.setProgress(progress);
        switch (status){
            case DownloadManager.STATUS_PENDING:
                downBt.setText(".....");
                Log.i("2/4","STATUS_PENDING");
                break;
            case DownloadManager.STATUS_RUNNING:
                Log.i("2/4","STATUS_RUNNING");
                downBt.setText("正在下载。。。");
                break;
            case DownloadManager.STATUS_PAUSED:
                Log.i("2/4","STATUS_PAUSED");
                downBt.setText("暂停");
                break;
            case DownloadManager.STATUS_FAILED:
                Log.i("2/4","STATUS_FAILED");
                break;
            case  DownloadManager.STATUS_SUCCESSFUL:
                Log.i("2/4","STATUS_SUCCESSFUL");
                downBt.setText("下载成功");
                break;
        }
    }
}
