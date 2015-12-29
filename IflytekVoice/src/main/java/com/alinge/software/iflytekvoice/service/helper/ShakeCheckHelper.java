package com.alinge.software.iflytekvoice.service.helper;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.alinge.software.iflytekvoice.service.ServiceManager;
import com.alinge.software.iflytekvoice.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2015/12/28
 * 作用：摇晃的检测帮助类
 */
public class ShakeCheckHelper implements SensorEventListener {
    //达到晃动的默认值
    private final static int SPEED =15;
    private  final static int TIME_INTERVAL=100;
    private static  ShakeCheckHelper shakeHelper;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private long lastCheckTime;
    private  OnShakeListener shakeListener;
    private ShakeCheckHelper() {
    }
    public  static ShakeCheckHelper getHelper(){
        if(shakeHelper==null){
            synchronized (ShakeCheckHelper.class){
                if(shakeHelper==null){
                    shakeHelper=new ShakeCheckHelper();
                }
            }
        }
        return shakeHelper;
    }
    public void startCheck(Context context) {
        mSensorManager = ServiceManager.getSensorManager(context);
        if (mSensorManager != null) {
            mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    public void stopCheck() {
        if(mSensorManager!=null){
            mSensorManager.unregisterListener(this);
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        long curentCheckTime=System.currentTimeMillis();
        long timeInterval=curentCheckTime-lastCheckTime;
        if(timeInterval<TIME_INTERVAL){
            return;
        }
        lastCheckTime=curentCheckTime;
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];
      //  LogUtils.info(null,"X:"+x+" Y:"+y+"  Z:"+z);
        if(Math.abs(x)>=SPEED ||Math.abs(y)>=SPEED ||Math.abs(z)>=SPEED  ){
            if(shakeListener!=null){
                shakeListener.onShake();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void setOnShakeListener( OnShakeListener shakeListener){
        this.shakeListener=shakeListener;
    }
    public interface OnShakeListener{
         void onShake();
    }
}
