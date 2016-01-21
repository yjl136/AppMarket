package com.alinge.software.market.net.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：用于缓存图片
 */
public class BitMapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> lruCache;
    private static BitMapCache mBitMapCache;

    private BitMapCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        lruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }
    /**
     * 创建单例的目的是为了防止同一个应用有多个缓存，造成同一张图片存在多个缓存区内
     * @return
     */
    public static BitMapCache getInstance() {
        if (mBitMapCache == null) {
            synchronized (BitMapCache.class){
                if(mBitMapCache==null){
                    mBitMapCache = new BitMapCache();
                }
            }
        }
        return mBitMapCache;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}
