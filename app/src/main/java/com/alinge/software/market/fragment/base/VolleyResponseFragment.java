package com.alinge.software.market.fragment.base;

import android.support.v4.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：
 */
public class VolleyResponseFragment extends Fragment implements Response.ErrorListener,Response.Listener {
    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(Object o) {

    }
}
