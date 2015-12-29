package com.alinge.software.iflytekvoice.recognizer.code;

/**
 * 作者： yejianlin
 * 日期：2015/12/29
 * 作用：
 */
public class Code {

    /***************广播携带的状态值******************/
    public final  static String STATUS_CODE="code";
    public final  static String RECOGNIZER_RESULT="result";
    /***************recognizer状态值******************/
    //空闲状态
    public  final static int FREE = 0x100;
    //开始录音
    public  final static int BEGIN_SPEECH = 0x101;
    //结束录音
    public  final static int END_SPEECH = 0x102;
    //正在识别
    public  final static int RECOGNIZER = 0x103;
    //没有说话
    public final static int NO_SPEEKING = 0x105;
    //网络错误
    public final static int NETWORK_ERROR = 0x106;
    //识别成功
    public final static int RECOGNIZER_SUCCESS = 0x107;
    //识别失败
    public final static int RECOGNIZER_FAILD = 0x108;
    /***************广播action值******************/
    public final static String RECOGNIZER_ACTION="action_recognizer";
    /***************讯飞错误状态值******************/
    //没有说话
    public final static int NO_SPEEKING_XUNFEI =10118;
    public final static int NETWORK_ERROR_XUNFEI =20001;
}
