package com.alinge.software.iflytekvoice.recognizer;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import com.alinge.software.iflytekvoice.utils.LogUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
/**
 * 作者： yejianlin
 * 日期：2015/12/28
 * 作用：讯飞语音合成
 */
public class IflytekSynthesizer {
    private Context context;
    private SpeechSynthesizer mSynthesizer;
    public  IflytekSynthesizer(Context context){
        this.context=context;
    }
    private SynthesizerListener mSynthesizerListener=new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {
            LogUtils.info(null," onSpeakBegin ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            LogUtils.info(null," onBufferProgress percent: "+percent);
        }

        @Override
        public void onSpeakPaused() {
            LogUtils.info(null," onSpeakPaused");
        }

        @Override
        public void onSpeakResumed() {
            LogUtils.info(null," onSpeakResumed");
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            LogUtils.info(null, " onSpeakProgress percent:"+percent);
        }

        @Override
        public void onCompleted(SpeechError speechError) {
            if(speechError==null){
                LogUtils.info(null,"onCompleted");
            }else{
                LogUtils.info(null,speechError.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {
            LogUtils.info(null," onEvent");
        }
    };
    public void synthesizer(String text){
        initSynthesizer();
        if(mSynthesizer!=null){
           int code= mSynthesizer.startSpeaking(text,mSynthesizerListener);
            if(code==ErrorCode.SUCCESS){
                LogUtils.info(null,"synthesier success");
            }else{
                LogUtils.info(null,"synthesier error");
            }
        }

    }
    private synchronized void initSynthesizer(){
        if(mSynthesizer==null){
            mSynthesizer=SpeechSynthesizer.getSynthesizer();
            if(mSynthesizer==null){
                mSynthesizer=SpeechSynthesizer.createSynthesizer(context, new InitListener() {
                    @Override
                    public void onInit(int code) {
                        if(code== ErrorCode.SUCCESS){
                            LogUtils.info(null,"SpeechSynthesizer init success");
                        }else{
                            LogUtils.info(null,"SpeechSynthesizer init fail");
                        }
                    }
                });
            }
        if (mSynthesizer!=null){
            setParam();
        }
        }

    }
    private void setParam(){
        // 清空参数
        mSynthesizer.setParameter(SpeechConstant.PARAMS, null);
        mSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            // 设置在线合成发音人
        mSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
            //设置合成语速
        mSynthesizer.setParameter(SpeechConstant.SPEED,  "50");
            //设置合成音调
        mSynthesizer.setParameter(SpeechConstant.PITCH,  "50");
            //设置合成音量
        mSynthesizer.setParameter(SpeechConstant.VOLUME,  "50");
        //设置播放器音频流类型
        mSynthesizer.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mSynthesizer.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mSynthesizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mSynthesizer.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.wav");
    }
}
