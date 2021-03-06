package com.alinge.software.market.fragment.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alinge.software.market.R;
import com.alinge.software.market.fragment.base.BaseFragment;
import com.alinge.software.market.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class ManagerFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.info(null, "ManagerFragment------->onCreate");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager, container,false);
    }
}
