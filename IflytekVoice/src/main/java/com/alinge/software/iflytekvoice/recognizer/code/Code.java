package com.alinge.software.iflytekvoice.recognizer.code;

/**
 * 作者： yejianlin
 * 日期：2015/12/29
 * 作用：
 */
public class Code {

    /***************广播携带的状态值******************/
    public final  static String STATUS_CODE="code";


    /***************recognizer状态值******************/
    //空闲状态
    public  final static int FREE = 0x100;
    //开始录音
    public  final static int BEGINSPEECH = 0x101;
    //结束录音
    public  final static int ENDSPEECH = 0x102;
    //识别
    public  final static int RECOGNIZER = 0x103;
    //网络错误
    public final static int NETERROR = 0x104;


    /***************广播action值******************/
    public final static String RECOGNIZER_ACTION="action_recognizer";
}
