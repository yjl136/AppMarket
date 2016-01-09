package com.alinge.software.market.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alinge.software.market.MainActivity;
import com.alinge.software.market.listener.PageChangeListener;
import com.alinge.software.market.R;
import com.alinge.software.market.utils.LogUtils;
import com.alinge.software.market.view.PagerIndicatorView;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class HomeFragment extends BaseFragment {
    private String[] mTitles = new String[]{"最新上架", "编辑推荐", "热门软件", "个人推荐"};
    private PagerIndicatorView mPagerIndicator;
    private ViewPager mViewPager;
    private Context activity;
    private FragmentStatePagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = getActivity();
        LogUtils.info(null, "HomeFragment------->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_home, container, false);
        initView(content);
        initDatas();
        initEvents();
        return content;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.info(null, "HomeFragment------->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.info(null, "HomeFragment------->onDetach");
    }

    private void initView(View content) {
        mPagerIndicator = (PagerIndicatorView) content.findViewById(R.id.pagerIndicator);
        mViewPager = (ViewPager) content.findViewById(R.id.viewPager);
    }

    private void initDatas() {
        mPagerIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }
        mAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mTitles.length;
            }
        };
       /* mAdapter = new FragmentPagerAdapter(getChildFragmentManager()){
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
        };*/
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initEvents() {
        mViewPager.addOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mPagerIndicator.scroll(position, positionOffset);
            }
        });
    }
}
