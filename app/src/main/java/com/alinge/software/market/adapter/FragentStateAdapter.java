package com.alinge.software.market.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alinge.software.market.fragment.factory.FragmentFactory;

/**
 * 作者： yejianlin
 * 日期：2016/1/11
 * 作用：
 */
public class FragentStateAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public FragentStateAdapter(FragmentManager fm,int count) {
        super(fm);
        this.tabCount=count;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
