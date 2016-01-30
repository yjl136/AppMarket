package com.alinge.software.market.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alinge.software.market.fragment.factory.FragmentFactory;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class FragmentAdapter extends FragmentPagerAdapter {
         private int tabCount;
         private FragmentFactory factory;
        public FragmentAdapter(FragmentManager fm,int count) {
            super(fm);
            this.tabCount=count;
            factory=new FragmentFactory();
        }

        @Override
        public Fragment getItem(int position) {
            return factory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabCount;
        }


}
