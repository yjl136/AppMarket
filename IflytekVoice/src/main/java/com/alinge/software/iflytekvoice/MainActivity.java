package com.alinge.software.iflytekvoice;

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

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private Button recognizerBt,synthesizerBt,shakeBt;
    private IflytekRecognizer recognizer;
    private IflytekSynthesizer synthesizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=(TextView)findViewById(R.id.showResult);
        recognizerBt=(Button)findViewById(R.id.recognizer);
        synthesizerBt=(Button)findViewById(R.id.synthesizer);
        shakeBt=(Button)findViewById(R.id.shake);
        resultTv.setText("onSpeakProgress。。。。。");
        recognizer=new IflytekRecognizer(this);
        synthesizer=new IflytekSynthesizer(this);
        recognizerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.recognizer(true);
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
        IntentFilter filter=new IntentFilter();
        filter.addAction(Code.RECOGNIZER_ACTION);
}

    private void startTipService() {
        Intent intent=new Intent(this,TipService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recognizer.release();
    }
}
