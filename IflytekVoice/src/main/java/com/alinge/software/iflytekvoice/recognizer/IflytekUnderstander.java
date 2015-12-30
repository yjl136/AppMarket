package com.alinge.software.iflytekvoice.recognizer;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.alinge.software.iflytekvoice.recognizer.listener.KimiInitListener;
import com.alinge.software.iflytekvoice.utils.LogUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstander;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.TextUnderstander;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;

/**
 * 作者： yejianlin
 * 日期：2015/12/30
 * 作用：
 */
public class IflytekUnderstander {
    private Context context;
    private TextUnderstander mTextUnderstander;
    private SpeechUnderstander mSpeechUnderstander;

    private IflytekUnderstander(Context context) {
        this.context = context;
    }

    /**
     * 语音语义理解
     */
    public synchronized void understanderVoice() {
        initSpeechUnderstander();
        if (mSpeechUnderstander == null) {
            return;
        }
        if (mSpeechUnderstander.isUnderstanding()) {
            Toast.makeText(context, "请稍后再试一下吧", Toast.LENGTH_SHORT).show();
            return;
        }
        int code = mSpeechUnderstander.startUnderstanding(mUnderstanderListener);
        if (code == ErrorCode.SUCCESS) {
            LogUtils.info(null, "mSpeechUnderstander start understand success");
        } else {
            LogUtils.info(null, "mSpeechUnderstander start understand fail");
        }

    }

    private SpeechUnderstanderListener mUnderstanderListener = new SpeechUnderstanderListener() {
        @Override
        public void onVolumeChanged(int volume, byte[] bytes) {
            LogUtils.info(null, "volume:" + volume);
        }

        @Override
        public void onBeginOfSpeech() {
            LogUtils.info(null, "onBeginOfSpeech");
        }

        @Override
        public void onEndOfSpeech() {
            LogUtils.info(null, "onEndOfSpeech");
        }

        @Override
        public void onResult(UnderstanderResult understanderResult) {
            if (understanderResult != null) {
                LogUtils.info(null, "result:" + understanderResult.getResultString());
            } else {
                LogUtils.info(null, "understanderResult==null");
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            if (speechError != null) {
                LogUtils.info(null, "speechError message:" + speechError.getMessage() + "   speeche error code:" + speechError.getErrorCode() + "   speeche error desc:" + speechError.getErrorDescription());
            } else {
                LogUtils.info(null, "speechError==null");
            }
        }

        @Override
        public void onEvent(int eventType, int i1, int i2, Bundle bundle) {

        }
    };

    private void initSpeechUnderstander() {
        if (mSpeechUnderstander == null) {
            mSpeechUnderstander = SpeechUnderstander.getUnderstander();
            if (mSpeechUnderstander == null) {
                mSpeechUnderstander = SpeechUnderstander.createUnderstander(context, new KimiInitListener("SpeechUnderstander"));
            }
            if (mSpeechUnderstander != null) {
                setParam();
            }
        }

    }

    public void setParam() {
        //清空参数
        mSpeechUnderstander.setParameter(SpeechConstant.PARAMS, null);
        // 设置语言
        mSpeechUnderstander.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域
        mSpeechUnderstander.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mSpeechUnderstander.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mSpeechUnderstander.setParameter(SpeechConstant.VAD_EOS, "1000");
        // 设置标点符号，默认：1（有标点）
        mSpeechUnderstander.setParameter(SpeechConstant.ASR_PTT, "1");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mSpeechUnderstander.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mSpeechUnderstander.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/sud.wav");
    }


    private TextUnderstanderListener mTextUnderstanderListener = new TextUnderstanderListener() {
        @Override
        public void onResult(UnderstanderResult understanderResult) {
            if (understanderResult != null) {
                LogUtils.info(null, "result:" + understanderResult.getResultString());
            } else {
                LogUtils.info(null, "understanderResult==null");
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            if (speechError != null) {
                LogUtils.info(null, "speechError message:" + speechError.getMessage() + "   speeche error code:" + speechError.getErrorCode() + "   speeche error desc:" + speechError.getErrorDescription());
            } else {
                LogUtils.info(null, "speechError==null");
            }
        }
    };

    /**
     * 文本语义理解
     */
    public void understanderText(String text) {
        initTextUnderstander();
        if (mTextUnderstander == null) {
            return;
        }
        if (mTextUnderstander.isUnderstanding()) {
            Toast.makeText(context, "请稍后再试一下吧", Toast.LENGTH_SHORT).show();
            return;
        }
        int code = mTextUnderstander.understandText(text, mTextUnderstanderListener);
        if (code == ErrorCode.SUCCESS) {
            LogUtils.info(null, "mTextUnderstander start understand success");
        } else {
            LogUtils.info(null, "mTextUnderstander start understand fail");
        }
    }

    private synchronized void initTextUnderstander() {
        if (mTextUnderstander == null) {
            mTextUnderstander = TextUnderstander.getTextUnderstander();
            if (mTextUnderstander == null) {
                mTextUnderstander = TextUnderstander.createTextUnderstander(context, new KimiInitListener("TextUnderstander"));
            }
        }
    }

    public void release() {
        if (mTextUnderstander != null) {
            mTextUnderstander.cancel();
            mTextUnderstander.destroy();
            mTextUnderstander = null;
        }
        if (mSpeechUnderstander != null) {
            mSpeechUnderstander.cancel();
            mSpeechUnderstander.destroy();
            mSpeechUnderstander = null;
        }
    }
}
