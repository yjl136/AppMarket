package com.alinge.software.iflytekvoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alinge.software.iflytekvoice.recognizer.IflytekRecognizer;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private Button recognizerBt;
    private IflytekRecognizer recognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=(TextView)findViewById(R.id.showResult);
        recognizerBt=(Button)findViewById(R.id.recognizer);
        resultTv.setText("说说看");
        recognizer=new IflytekRecognizer(this);
        recognizerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.recognizer(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recognizer.release();
    }
}
