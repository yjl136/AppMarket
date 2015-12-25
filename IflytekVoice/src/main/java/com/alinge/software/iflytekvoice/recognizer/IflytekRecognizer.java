package com.alinge.software.iflytekvoice.recognizer;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.alinge.software.iflytekvoice.recognizer.parser.JsonParser;
import com.alinge.software.iflytekvoice.utils.LogUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 作者： yejianlin
 * 日期：2015/12/25
 * 作用：讯飞语音识别
 */
public class IflytekRecognizer {
    private SpeechRecognizer mRecognizer;
    private RecognizerDialog mRecognizerDialog;
    private Context context;
    //保留一次识别的结果
    private HashMap<String, String> mResults = new LinkedHashMap<String, String>();

    public IflytekRecognizer(Context context) {
        this.context = context;
    }

    private  RecognizerListener mRecognizerListener = new RecognizerListener() {
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
        public void onResult(RecognizerResult recognizerResult, boolean isLast) {
            if (recognizerResult != null) {
                LogUtils.info(null, "resutlt:  " + recognizerResult.getResultString());
                doResult(recognizerResult,isLast);
            } else {
                LogUtils.info(null, "resutlt: null");
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

    private  RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean islast) {
            if (recognizerResult != null) {
                LogUtils.info(null, "resutlt:  " + recognizerResult.getResultString());
                doResult(recognizerResult,islast);
            } else {
                LogUtils.info(null, "resutlt: null");
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

    private void doResult(RecognizerResult results, boolean isLast) {
        String text = JsonParser.parseIatResult(results.getResultString());
        String sn = null;
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mResults.put(sn, text);
        if (isLast) {
            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mResults.keySet()) {
                resultBuffer.append(mResults.get(key));
            }

        }

    }

    /**
     * 开始录音识别
     * @param isShowDialog 在录音事是否显示ui
     */
    public void recognizer(boolean isShowDialog) {
        //将上一次的结果清空
         clearResult();
        if (isShowDialog) {
            startRecognizerDialog();
        } else {
            startRecognizer();
        }
    }
    /**
     * 无界面识别
     */
    private synchronized void startRecognizer() {
        //检查是否初始化
        initRecognizer();
        if (mRecognizer != null) {
            int code = mRecognizer.startListening(mRecognizerListener);
            if (code != ErrorCode.SUCCESS) {
                LogUtils.error(null, "code:" + code);
                Toast.makeText(context, "识别失败,请重新输入", Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * 有界面识别
     */

    private synchronized void startRecognizerDialog() {
        if (mRecognizerDialog == null) {
            mRecognizerDialog = new RecognizerDialog(context, new InitListener() {
                @Override
                public void onInit(int code) {
                    if (code == ErrorCode.SUCCESS) {
                        LogUtils.error(null, "RecognizerDialog init susscess");
                    } else {
                        LogUtils.error(null, "RecognizerDialog init fail code:" + code);
                    }
                }
            });
        }
        if (mRecognizerDialog != null) {
            mRecognizerDialog.setListener(mRecognizerDialogListener);
            mRecognizerDialog.show();
        }

    }

    private  void initRecognizer() {
        if (mRecognizer == null) {
            //SpeechRecognizer默认是单列，先获取，获取不到才去创建
            mRecognizer=SpeechRecognizer.getRecognizer();
            if(mRecognizer==null){
                mRecognizer = SpeechRecognizer.createRecognizer(context, new InitListener() {
                    @Override
                    public void onInit(int code) {
                        if (code == ErrorCode.SUCCESS) {
                            //成功设置参数
                            setParam();
                            LogUtils.error(null, "SpeechRecognizer init susscess ");
                        } else {
                            LogUtils.error(null, "SpeechRecognizer init fail");
                        }
                    }
                });
            }
        }
    }
    /**
     *设置相关默认参数
     */
    public void setParam() {
        // 设置听写引擎
        mRecognizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");
        // 设置语言
        mRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域
        mRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin ");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mRecognizer.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mRecognizer.setParameter(SpeechConstant.VAD_EOS, "1000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mRecognizer.setParameter(SpeechConstant.ASR_PTT, "1");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mRecognizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mRecognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
        // 设置听写结果是否结果动态修正，为“1”则在听写过程中动态递增地返回结果，否则只在听写结束之后返回最终结果
        // 注：该参数暂时只对在线听写有效
        mRecognizer.setParameter(SpeechConstant.ASR_DWA, "0");
    }
    public void release() {
        if (mRecognizer != null) {
            mRecognizer.cancel();
            mRecognizer.destroy();
            mRecognizer=null;
        }
        if (mRecognizerDialog != null) {
            mRecognizerDialog.cancel();
            mRecognizerDialog.destroy();
            mRecognizerDialog=null;
        }
    }
    private void clearResult(){
        if(mResults!=null && mResults.size()>=0){
            mResults.clear();
        }
    }
}
