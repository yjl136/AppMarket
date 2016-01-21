package com.alinge.software.market.view;
import android.content.Context;
import android.util.AttributeSet;
import com.alinge.software.market.R;
import com.android.volley.toolbox.NetworkImageView;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：可以自动加载网路图片的ImageView
 */
public class AsycImageView extends NetworkImageView {
    public AsycImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AsycImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AsycImageView(Context context) {
        super(context);
        init();
    }
    private  void init(){
        setDefaultImageResId(R.mipmap.icon_empty);
        setErrorImageResId(R.mipmap.icon_empty);
    }
}
