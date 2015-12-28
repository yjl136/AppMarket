package com.alinge.software.iflytekvoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alinge.software.iflytekvoice.recognizer.IflytekRecognizer;
import com.alinge.software.iflytekvoice.recognizer.IflytekSynthesizer;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private Button recognizerBt,synthesizerBt;
    private IflytekRecognizer recognizer;
    int test;
    int test2;
    private IflytekSynthesizer synthesizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=(TextView)findViewById(R.id.showResult);
        recognizerBt=(Button)findViewById(R.id.recognizer);
        synthesizerBt=(Button)findViewById(R.id.synthesizer);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recognizer.release();
    }
}
