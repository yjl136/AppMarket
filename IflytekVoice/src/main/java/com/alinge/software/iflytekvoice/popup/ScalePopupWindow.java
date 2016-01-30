package com.alinge.software.iflytekvoice.popup;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.alinge.software.iflytekvoice.R;
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
import com.alinge.software.iflytekvoice.recognizer.filter.utils.TimeHelper;
import com.alinge.software.iflytekvoice.utils.DensityHelper;
import com.alinge.software.iflytekvoice.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/26
 * 作用：
 */
public class ScalePopupWindow extends PopupWindow implements Animation.AnimationListener{
    private Activity context;
    private View popupView;
    private View animatorView;
    private ImageView imageView;
    private IflytekSynthesizer synthesizer;
    private RecognizerReceiver mRecognizerReceiver;
    private  SynthesizerReceiver mSynthesizerReceiver;
    private IflytekUnderstander understander;
    private AnimationDrawable drawable;
    private SynthesizerReceiver msynSynthesizerReceiver;

    public ScalePopupWindow(Context context) {
        super(context);
        this.context = (Activity) context;
        init();
    }

    private void init() {
        initWindow();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        animatorView = popupView.findViewById(R.id.animator);
        imageView = (ImageView) popupView.findViewById(R.id.helper);
    }

    /**
     * 初始化window
     */
    private void initWindow() {
        setContentView(getPopupView(context));
       // setWidth(calWidth(context));
        //setHeight(calHeight(context));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(true);
        setAnimationStyle(0);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                release();
            }
        });
    }

    private void release() {
        understander.stopUnderstanding();
        synthesizer.stopSpeeking();
        if(understander!=null){
            understander.release();
        }
        if(synthesizer!=null){
            synthesizer.release();
        }
        if(mRecognizerReceiver!=null){
           context. unregisterReceiver(mRecognizerReceiver);
            mRecognizerReceiver=null;
        }
        if(msynSynthesizerReceiver!=null){
            context.unregisterReceiver(msynSynthesizerReceiver);
            msynSynthesizerReceiver=null;
        }
        if(mSynthesizerReceiver!=null){
            context. unregisterReceiver(msynSynthesizerReceiver);
            mSynthesizerReceiver=null;
        }
    }

    /**
     * showWindow
     */
    public void show() {
        showAtLocation(context.findViewById(android.R.id.content),
                Gravity.RIGHT | Gravity.CENTER_HORIZONTAL,
                0,
                0);
        animatorView.clearAnimation();
        animatorView.startAnimation(getDefaultScaleAnimation());

    }

    /**
     * 计算宽度
     *
     * @param context
     * @return
     */
    private int calWidth(Context context) {
        return DensityHelper.getScreenWidth(context) / 3;
    }

    /**
     * 计算高度
     *
     * @param context
     * @return
     */
    private int calHeight(Context context) {
        return DensityHelper.getScreenHeight(context) / 2;
    }

    /**
     * 获取popup的view
     *
     * @param context
     * @return
     */
    private View getPopupView(Context context) {
        if (popupView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            popupView = inflater.inflate(R.layout.activity_helper, null);
        }
        return popupView;
    }
    /**
     * 生成ScaleAnimation
     */
    protected Animation getScaleAnimation(float fromX, float toX, float fromY, float toY,
                                          int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        Animation scaleAnimation =
                new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType,
                        pivotYValue);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    /**
     * 生成自定义ScaleAnimation
     */
    protected Animation getDefaultScaleAnimation() {
        Animation scaleAnimation =
                new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(this);
        return scaleAnimation;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        synthesizer = new IflytekSynthesizer(context);
        understander=new IflytekUnderstander(context);
        initReceiver();
        checkHours();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setBackgroundResource(R.drawable.voice_hear_animation);
                drawable=(AnimationDrawable)imageView.getBackground();
                drawable.start();
                understander.understanderVoice();
            }
        });
    }
    private  void checkHours(){
        int drawble;
        String text;
        int hours= TimeHelper.getHours();
        if(hours<10){
            //早上
            drawble=R.drawable.voice_morning_animation;
            text="早上好！，小朋友";
        }else if(hours>=10 && hours<=14){
            //中午
            drawble=R.drawable.voice_midday_animation;
            text="中午好！，小朋友";
        }else if(hours>14 && hours<20){
            //下午
            drawble=R.drawable.voice_morning_animation;
            text="下午好！，小朋友";
        }else {
            //晚上
            drawble=R.drawable.voice_night_animation;
            text="晚上好！，小朋友";
        }
        imageView.setBackgroundResource(drawble);
        drawable=(AnimationDrawable)imageView.getBackground();
        synthesizer.synthesizer(text);
    }
    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Code.UNDERSTANDER_ACTION);
        mRecognizerReceiver = new RecognizerReceiver();
       context.registerReceiver(mRecognizerReceiver, filter);

        IntentFilter synthesizerFilter=new IntentFilter();
        synthesizerFilter.addAction(Code.SYNTHESIZER_ACTION);
        msynSynthesizerReceiver=new SynthesizerReceiver();
       context.registerReceiver(msynSynthesizerReceiver, synthesizerFilter);

    }
    private class RecognizerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra(Code.STATUS_CODE, 0);
            imageView.setBackgroundResource(R.drawable.voice_speek_animation);
            drawable = (AnimationDrawable)imageView.getBackground();
            if(code==Code.NO_SPEEKING_XUNFEI){
                synthesizer.synthesizer("你好像没有说话哦！");
                return;
            }else if(code==Code.NETWORK_ERROR_XUNFEI || code==Code.NETWORK_TIMEOUT_XUNFEI){
                synthesizer.synthesizer("网络错误，快叫爸爸、妈妈来连接网络吧！");
                return;
            }else if (code==Code.UNDERSTANDER_FAILD){
                synthesizer.synthesizer("我听不清楚，请大点声");
                return;
            }
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
                    ResolveInfo resolveInfo= AppHelper.queryApp(context, rawText);
                    if(resolveInfo!=null){
                        if(rawText.contains("打开")){
                            AppHelper.launchApp(context,resolveInfo);
                        }else if(rawText.contains("删除")){
                            AppHelper.uninstallApp(context,resolveInfo);
                        }else{
                            AppHelper.launchApp(context,resolveInfo);
                        }
                    }else{
                        synthesizer.synthesizer("你说清楚点，我不知道你想干什么");
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.info(null, " exception:" + e.getMessage());
        }

    }
    private class SynthesizerReceiver extends  BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra(Code.STATUS_CODE, 0);
            LogUtils.info("1/", "systhesizer code:" + code);
            if(code==Code.BEGIN_SPEEK){
                if(drawable!=null && !drawable.isRunning()){
                    drawable.start();
                }
            }else if( code==Code.SYNTHESIZER_FAILD||code==Code.SYNTHESIZER_SUCCESS){
                if(drawable!=null && drawable.isRunning()){
                    drawable.stop();
                    //暂停的时候在动画最后一帧
                    int frameNum=drawable.getNumberOfFrames();
                    Drawable lastDrawable=drawable.getFrame(frameNum - 1);
                    imageView.setBackground(lastDrawable);
                }
            }
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
                AppHelper.launchApp(context, appName);
            } else if (AppOperation.UNINSTALL.equals(opration)) {
                AppHelper.uninstallApp(context, appName);
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
                    MessageHelper.sendMesaage(context, name, content);
                } else {
                    MessageHelper.sendMessage(context);
                }
            } else if (MessageOperation.VIEW.equalsIgnoreCase(opration)) {
                MessageHelper.viewMessage(context);
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
