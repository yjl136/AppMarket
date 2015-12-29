package com.alinge.software.iflytekvoice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alinge.software.iflytekvoice.recognizer.IflytekRecognizer;
import com.alinge.software.iflytekvoice.recognizer.IflytekSynthesizer;
import com.alinge.software.iflytekvoice.recognizer.code.Code;
import com.alinge.software.iflytekvoice.service.TipService;
import com.alinge.software.iflytekvoice.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private Button recognizerBt, synthesizerBt, shakeBt;
    private IflytekRecognizer recognizer;
    private IflytekSynthesizer synthesizer;
    private RecognizerReceiver mRecognizerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        resultTv = (TextView) findViewById(R.id.showResult);
        recognizerBt = (Button) findViewById(R.id.recognizer);
        synthesizerBt = (Button) findViewById(R.id.synthesizer);
        shakeBt = (Button) findViewById(R.id.shake);
        resultTv.setText("onSpeakProgress。。。。。");
        recognizer = new IflytekRecognizer(this);
        synthesizer = new IflytekSynthesizer(this);
        recognizerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.recognizer(false);
            }
        });
        synthesizerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synthesizer.synthesizer(resultTv.getText().toString());
            }
        });

        shakeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTipService();
            }
        });

    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Code.RECOGNIZER_ACTION);
        mRecognizerReceiver=new RecognizerReceiver();
        registerReceiver(mRecognizerReceiver,filter);
    }

    private void startTipService() {
        Intent intent = new Intent(this, TipService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recognizer.release();
    }

    private  class RecognizerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code=intent.getIntExtra(Code.STATUS_CODE,0);
            String result=intent.getStringExtra(Code.RECOGNIZER_RESULT);
            doRecognizerResult(code,result);
        }
    }
    private void doRecognizerResult(int code,String result){
        LogUtils.info(null,"code:"+code+"  result:"+result);
    }
}
