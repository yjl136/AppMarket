package com.alinge.software.market.fragment.factory;

import android.support.v4.app.Fragment;
import com.alinge.software.market.fragment.base.BaseFragment;
import com.alinge.software.market.fragment.Brand.BrandFragment;
import com.alinge.software.market.fragment.category.CategoryFragment;
import com.alinge.software.market.fragment.home.HomeFragment;
import com.alinge.software.market.fragment.manager.ManagerFragment;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class FragmentFactory {

    private  Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();
    public  Fragment createFragment(int position) {
        BaseFragment fragment = null;
        fragment = mFragments.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new CategoryFragment();
            } else if (position == 2) {
                fragment = new BrandFragment();
            } else if (position == 3) {
                fragment = new ManagerFragment();
            }
        }
        if (fragment != null) {
            mFragments.put(position, fragment);
        }
        return fragment;
    }
}
