package com.alinge.software.market.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alinge.software.market.fragment.base.BaseFragment;
import com.alinge.software.market.R;
import com.alinge.software.market.utils.LogUtils;
import com.viewpagerindicator.TitlePageIndicator;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class HomeFragment extends BaseFragment {

    private String[] mTitles = new String[]{Type.NEWS, Type.RECOMMEND, Type.HOTS};
    private TitlePageIndicator mPagerIndicator;
    private ViewPager mViewPager;
    private Context activity;
    private FragmentPagerAdapter mAdapter;
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
        LogUtils.info(null, "HomeFragment------->onCreateView");
        return content;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.info(null, "HomeFragment------->onDestroyView");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.info(null, "HomeFragment------->onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.info(null, "HomeFragment------->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.info(null, "HomeFragment------->onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.info(null, "HomeFragment------->onStop");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.info(null, "HomeFragment------->onActivityCreated");
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
        mPagerIndicator = (TitlePageIndicator) content.findViewById(R.id.pagerIndicator);
        mViewPager = (ViewPager) content.findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(2);

    }

    private void initDatas() {


        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }
    /*    mAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mTitles.length;
            }
        };*/
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position % mTitles.length];
            }
        };
        mViewPager.setAdapter(mAdapter);
        mPagerIndicator.setViewPager(mViewPager);
    }

    private void initEvents() {
       /* mViewPager.addOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mPagerIndicator.scroll(position, positionOffset);
            }
        });*/
    }
}
