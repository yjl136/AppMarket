package com.alinge.software.iflytekvoice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alinge.software.iflytekvoice.recognizer.IflytekSynthesizer;
import com.alinge.software.iflytekvoice.recognizer.code.Code;
import com.alinge.software.iflytekvoice.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2016/1/15
 * 作用：
 */
public class VoiceHelperActivity extends Activity {
    private ImageView imageView;
    private IflytekSynthesizer synthesizer;
    private RecognizerReceiver mRecognizerReceiver;
    private  SynthesizerReceiver mSynthesizerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        imageView= (ImageView) findViewById(R.id.helper);
        synthesizer = new IflytekSynthesizer(this);
        initReceiver();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               v.setBackgroundResource(R.drawable.voice_animation);
                AnimationDrawable drawable=(AnimationDrawable)v.getBackground();
                synthesizer.synthesizer("早上好！小朋友");
                drawable.start();

            }
        });


    }
    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Code.RECOGNIZER_ACTION);
        filter.addAction(Code.UNDERSTANDER_ACTION);
        mRecognizerReceiver = new RecognizerReceiver();
        registerReceiver(mRecognizerReceiver, filter);

        IntentFilter synthesizerFilter=new IntentFilter();
        synthesizerFilter.addAction(Code.SYNTHESIZER_ACTION);
        SynthesizerReceiver msynSynthesizerReceiver=new SynthesizerReceiver();
        registerReceiver(msynSynthesizerReceiver,synthesizerFilter);

    }
    private class RecognizerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra(Code.STATUS_CODE, 0);
            String result = intent.getStringExtra(Code.RECOGNIZER_RESULT);
        }
    }
    private class SynthesizerReceiver extends  BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int code=intent.getIntExtra(Code.STATUS_CODE, 0);
            LogUtils.info("1/","systhesizer code:"+code);
            if(code==Code.BEGIN_SPEEK){
                //开始播放
            }/*else if(){

            }*/
        }
    }

}
