package com.alinge.software.iflytekvoice.recognizer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.alinge.software.iflytekvoice.recognizer.code.Code;
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
    private int curentCode = Code.SYNTHESIZER_FREE;

    public IflytekSynthesizer(Context context) {
        this.context = context.getApplicationContext();
    }

    private SynthesizerListener mSynthesizerListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {
            notifyStatusChange(Code.BEGIN_SPEEK);
            LogUtils.info(null, " onSpeakBegin ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            LogUtils.info(null, " onBufferProgress percent: " + percent);
        }

        @Override
        public void onSpeakPaused() {
            notifyStatusChange(Code.PAUSER_SPEEK);
            LogUtils.info(null, " onSpeakPaused");
        }

        @Override
        public void onSpeakResumed() {
            notifyStatusChange(Code.RESUMED_SPEEK);
            LogUtils.info(null, " onSpeakResumed");
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            notifyStatusChange(Code.SPEEKING);
            LogUtils.info(null, " onSpeakProgress percent:" + percent);
        }

        @Override
        public void onCompleted(SpeechError speechError) {
            if (speechError == null) {
                notifyStatusChange(Code.SYNTHESIZER_SUCCESS);
                LogUtils.info(null, "onCompleted");
            } else {
                notifyStatusChange(Code.SYNTHESIZER_FAILD);
                LogUtils.info(null, speechError.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {
            LogUtils.info(null, " onEvent");
        }
    };

    public void synthesizer(String text) {
        initSynthesizer();
        if (mSynthesizer != null) {
            int code = mSynthesizer.startSpeaking(text, mSynthesizerListener);
            if (code == ErrorCode.SUCCESS) {
                LogUtils.info(null, "synthesier success");
            } else {
                LogUtils.info(null, "synthesier error");
            }
        }

    }

    private synchronized void initSynthesizer() {
        if (mSynthesizer == null) {
            mSynthesizer = SpeechSynthesizer.getSynthesizer();
            if (mSynthesizer == null) {
                mSynthesizer = SpeechSynthesizer.createSynthesizer(context, new InitListener() {
                    @Override
                    public void onInit(int code) {
                        if (code == ErrorCode.SUCCESS) {
                            LogUtils.info(null, "SpeechSynthesizer init success");
                        } else {
                            LogUtils.info(null, "SpeechSynthesizer init fail");
                        }
                    }
                });
            }
            if (mSynthesizer != null) {
                setParam();
            }
        }

    }

    public void release() {
        if (mSynthesizer != null) {
            mSynthesizer.destroy();
            mSynthesizer = null;
        }

    }

    private void setParam() {
        // 清空参数
        mSynthesizer.setParameter(SpeechConstant.PARAMS, null);
        mSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "aisxa");
        //设置合成语速
        mSynthesizer.setParameter(SpeechConstant.SPEED, "70");
        //  mSynthesizer.setParameter(SpeechConstant.BACKGROUND_SOUND,"1");
        //设置合成音调
        mSynthesizer.setParameter(SpeechConstant.PITCH, "60");
        //设置合成音量
        mSynthesizer.setParameter(SpeechConstant.VOLUME, "100");
        //设置播放器音频流类型
        mSynthesizer.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mSynthesizer.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mSynthesizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mSynthesizer.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");
    }

    private void notifyStatusChange(int code) {
        resetCurentStatus(code);
        //发送广播通知外部状态改变
        Intent intent = new Intent();
        intent.setAction(Code.SYNTHESIZER_ACTION);
        intent.putExtra(Code.STATUS_CODE, code);
        context.sendBroadcast(intent);
    }

    private void resetCurentStatus(int code) {
        if (code == Code.SYNTHESIZER_FAILD || code == Code.SYNTHESIZER_SUCCESS) {
            curentCode = Code.SYNTHESIZER_FREE;
        } else {
            curentCode = code;
        }
    }

    public void stopSpeeking() {
        if (mSynthesizer != null) {
            mSynthesizer.stopSpeaking();
        }
    }
}
