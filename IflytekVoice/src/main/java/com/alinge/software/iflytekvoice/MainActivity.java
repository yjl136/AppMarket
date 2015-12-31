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
import com.alinge.software.iflytekvoice.recognizer.IflytekUnderstander;
import com.alinge.software.iflytekvoice.recognizer.code.Code;
import com.alinge.software.iflytekvoice.recognizer.filter.FilterResult;
import com.alinge.software.iflytekvoice.service.TipService;
import com.alinge.software.iflytekvoice.utils.LogUtils;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private Button recognizerBt, synthesizerBt, shakeBt,understanderBt,textUnderstanderBt;
    private IflytekRecognizer recognizer;
    private IflytekSynthesizer synthesizer;
    private RecognizerReceiver mRecognizerReceiver;
    private IflytekUnderstander understander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        resultTv = (TextView) findViewById(R.id.showResult);
        recognizerBt = (Button) findViewById(R.id.recognizer);
        synthesizerBt = (Button) findViewById(R.id.synthesizer);
        understanderBt = (Button) findViewById(R.id.understander);
        textUnderstanderBt = (Button) findViewById(R.id.textUnderstander);
        shakeBt = (Button) findViewById(R.id.shake);
        resultTv.setText("onSpeakProgress。。。。。");
        recognizer = new IflytekRecognizer(this);
        synthesizer = new IflytekSynthesizer(this);
        understander=new IflytekUnderstander(this);
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

        understanderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                understander.understanderVoice();
            }
        });
        textUnderstanderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                understander.understanderText("斗鱼Tv");
            }
        });

    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Code.RECOGNIZER_ACTION);
        filter.addAction(Code.UNDERSTANDER_ACTION);
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
        try{
            if(code==Code.UNDERSTANDER_SUCCESS){
                JSONObject obj=new JSONObject(result);
                FilterResult filterResult=new FilterResult();
                filterResult.fromJson(obj);
               // LogUtils.info(null,filterResult.toString());
            }
        }catch (Exception e){
            LogUtils.info(null," exception:"+e.getMessage());
        }

    }
}
