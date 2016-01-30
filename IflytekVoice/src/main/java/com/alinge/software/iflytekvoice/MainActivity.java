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

import com.alinge.software.iflytekvoice.popup.ScalePopupWindow;
import com.alinge.software.iflytekvoice.recognizer.IflytekRecognizer;
import com.alinge.software.iflytekvoice.recognizer.IflytekSynthesizer;
import com.alinge.software.iflytekvoice.recognizer.IflytekUnderstander;
import com.alinge.software.iflytekvoice.recognizer.code.Code;
import com.alinge.software.iflytekvoice.recognizer.filter.AnswerResult;
import com.alinge.software.iflytekvoice.recognizer.filter.FilterResult;
import com.alinge.software.iflytekvoice.recognizer.filter.SemanticResult;
import com.alinge.software.iflytekvoice.recognizer.filter.ServiceType;
import com.alinge.software.iflytekvoice.recognizer.filter.app.AppOperation;
import com.alinge.software.iflytekvoice.recognizer.filter.app.AppSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.message.MessageOperation;
import com.alinge.software.iflytekvoice.recognizer.filter.message.MessageSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.utils.AppHelper;
import com.alinge.software.iflytekvoice.recognizer.filter.utils.MessageHelper;
import com.alinge.software.iflytekvoice.service.TipService;
import com.alinge.software.iflytekvoice.utils.ApkInstaller;
import com.alinge.software.iflytekvoice.utils.LogUtils;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.util.UserWords;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private Button recognizerBt, synthesizerBt, shakeBt, understanderBt, textUnderstanderBt;
    private IflytekRecognizer recognizer;
    private IflytekSynthesizer synthesizer;
    private RecognizerReceiver mRecognizerReceiver;
    private IflytekUnderstander understander;
    private Button uploadBt, voiceHelper, changeFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        resultTv = (TextView) findViewById(R.id.showResult);
        recognizerBt = (Button) findViewById(R.id.recognizer);
        synthesizerBt = (Button) findViewById(R.id.synthesizer);
        understanderBt = (Button) findViewById(R.id.understander);
        voiceHelper = (Button) findViewById(R.id.voiceHelper);
        changeFileName = (Button) findViewById(R.id.changeFileName);
        textUnderstanderBt = (Button) findViewById(R.id.textUnderstander);
        uploadBt = (Button) findViewById(R.id.upload);
        shakeBt = (Button) findViewById(R.id.shake);
        resultTv.setText("onSpeakProgress。。。。。");
        recognizer = new IflytekRecognizer(this);
        synthesizer = new IflytekSynthesizer(this);
        understander = new IflytekUnderstander(this);
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

      /*  understanderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                understander.understanderVoice();
            }
        });*/
        textUnderstanderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                understander.understanderText("斗鱼Tv");
            }
        });
        uploadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserWords uw = new UserWords();
                ArrayList<String> words = new ArrayList<String>();
                words.add("app");
                words.add("应用");
                words.add("APP");
                uw.putWords("家长助手", words);
                recognizer.uploadUserWords(uw.toString());
            }
        });
        voiceHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否安装了语记
                if(SpeechUtility.getUtility().checkServiceInstalled()){
                    ApkInstaller installer=new ApkInstaller(MainActivity.this);
                    installer.install();
                }else{
                    //已经安装了语记
                   // Intent intent=new Intent(MainActivity.this,VoiceHelperActivity.class);
                    //startActivity(intent);
                    ScalePopupWindow scalePopupWindow=new ScalePopupWindow(MainActivity.this);
                    scalePopupWindow.show();
                }
            }
        });
        changeFileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Code.RECOGNIZER_ACTION);
        filter.addAction(Code.UNDERSTANDER_ACTION);
        mRecognizerReceiver = new RecognizerReceiver();
        registerReceiver(mRecognizerReceiver, filter);
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

    private class RecognizerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra(Code.STATUS_CODE, 0);
            String result = intent.getStringExtra(Code.RECOGNIZER_RESULT);
            doRecognizerResult(code, result);
        }
    }

    private void doRecognizerResult(int code, String result) {
        LogUtils.info(null, "code:" + code + "  result:" + result);
        try {
            if (code == Code.UNDERSTANDER_SUCCESS) {
                JSONObject obj = new JSONObject(result);
                FilterResult filterResult = new FilterResult();
                filterResult.fromJson(obj);
                int rc = filterResult.getRc();
                if (rc == 0) {
                    String serviceType = filterResult.getService();
                    doFilter(serviceType, filterResult);
                } else {
                    LogUtils.error(null, "rc:" + rc + "  rawText" + filterResult.getRawText());
                    String rawText=filterResult.getRawText();
                }
            }
        } catch (Exception e) {
            LogUtils.info(null, " exception:" + e.getMessage());
        }

    }

    private void doFilter(String serviceType, FilterResult filterResult) throws JSONException {
        String opration = filterResult.getOperation();
        if (ServiceType.BAI_KE.equals(serviceType)
                || ServiceType.FQA.equals(serviceType)
                || ServiceType.OPEN_QA.equals(serviceType)
                || ServiceType.CHAT.equals(serviceType)
                || ServiceType.DATE_TIME.equals(serviceType)
                || ServiceType.CALC.equals(serviceType)) {
            AnswerResult ar = filterResult.getAnswerResult();
            String text = ar.getText();
            synthesizer.synthesizer(text);
            LogUtils.info(null, "text:" + text + " oparation:" + opration);

        } else if (ServiceType.APP.equals(serviceType)) {
            SemanticResult semantic = filterResult.getSemanticResult();
            AppSlots slots = (AppSlots) semantic.getSlots(serviceType);
            String appName = slots.getName();
            LogUtils.info(null, "open app name:" + appName);
            if (AppOperation.LAUNCH.equals(opration)) {
                AppHelper.launchApp(this, appName);
            } else if (AppOperation.UNINSTALL.equals(opration)) {
                AppHelper.uninstallApp(this, appName);
            } else {
                LogUtils.info(null, " app oparation:" + opration);
            }
        } else if (ServiceType.MAP.equals(serviceType)) {

        } else if (ServiceType.MESSAGE.equals(serviceType)) {
            SemanticResult sms = filterResult.getSemanticResult();
            MessageSlots smsSlots = (MessageSlots) sms.getSlots(serviceType);
            if (MessageOperation.SEND.equalsIgnoreCase(opration)) {
                if (smsSlots != null) {
                    String content = smsSlots.getContent();
                    String name = smsSlots.getName();
                    MessageHelper.sendMesaage(this, name, content);
                } else {
                    MessageHelper.sendMessage(this);
                }
            } else if (MessageOperation.VIEW.equalsIgnoreCase(opration)) {
                MessageHelper.viewMessage(this);
            }

        } else if (ServiceType.TELEPHONE.equals(serviceType)) {

        } else if (ServiceType.SCHEDULE.equals(serviceType)) {

        } else if (ServiceType.TRANSLATION.equals(serviceType)) {

        } else if (ServiceType.WEATHER.equals(serviceType)) {

        } else if (ServiceType.WEBSITE.equals(serviceType)) {

        } else if (ServiceType.WEBSEARCH.equals(serviceType)) {

        }

    }
}
