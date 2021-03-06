package me.chunyu.spike.wcl_leakcanary_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_tv_text) TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * me.chunyu.spike.wcl_leakcanary_demo.MainActivity has leaked:
         * GC ROOT static me.chunyu.spike.wcl_leakcanary_demo.LeakSingle.sInstance
         * references me.chunyu.spike.wcl_leakcanary_demo.LeakSingle.mContext
         * leaks me.chunyu.spike.wcl_leakcanary_demo.MainActivity instance
         */
//        LeakSingle.getInstance(this).setRetainedTextView(mTvText);

        /**
         * me.chunyu.spike.wcl_leakcanary_demo.MainActivity has leaked:
         * GC ROOT static me.chunyu.spike.wcl_leakcanary_demo.LeakSingle.sInstance
         * references me.chunyu.spike.wcl_leakcanary_demo.LeakSingle.mTextView
         * references android.support.v7.widget.AppCompatTextView.mContext
         * leaks me.chunyu.spike.wcl_leakcanary_demo.MainActivity instance
         */
        LeakSingle.getInstance(this.getApplication()).setRetainedTextView(mTvText);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        // 防止内泄露
       LeakSingle.getInstance(this.getApplication()).removeRetainedTextView();
    }
}
