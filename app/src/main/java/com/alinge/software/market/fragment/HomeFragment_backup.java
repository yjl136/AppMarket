package com.alinge.software.market.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alinge.software.market.R;
import com.alinge.software.market.adapter.PictureAdapter;
import com.alinge.software.market.listener.PageChangeListener;
import com.alinge.software.market.net.VolleyUtils;
import com.alinge.software.market.net.utils.UrlUtils;
import com.alinge.software.market.utils.LogUtils;
import com.alinge.software.market.view.PagerIndicatorView;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class HomeFragment_backup extends BaseFragment {
    private String[] mTitles = new String[]{"最新上架", "编辑推荐", "热门软件", "个人推荐"};
    private PagerIndicatorView mPagerIndicator;
    private ViewPager mViewPager;
    private ViewPager picViewPager;
    private Context activity;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private int[] png = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = getActivity();
        LogUtils.info(null, "HomeFragment------->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_home_backup, container, false);
        initView(content);
        initDatas();
        initEvents();
        getData();
        LogUtils.info(null, "HomeFragment------->onCreateView");
        return content;
    }

    private void getData() {
        Map<String ,String> params=new HashMap<String,String>();
        params.put("token",UrlUtils.getAppKey(getActivity().getApplicationContext()));
        params.put("productId",UrlUtils.PRODUCT_ID);
        params.put("count",UrlUtils.ITEM_COUNT);
        params.put("bannerCount",UrlUtils.ITEM_COUNT);
        params.put("machineName",UrlUtils.getMachineType());
      //  VolleyUtils.requestGet(UrlUtils.QUALITY_MAIN_URI,params);
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
        mPagerIndicator = (PagerIndicatorView) content.findViewById(R.id.pagerIndicator);
        mViewPager = (ViewPager) content.findViewById(R.id.viewPager);
        picViewPager = (ViewPager) content.findViewById(R.id.picVp);
        PictureAdapter pictureAdapter = new PictureAdapter(png, activity);
        picViewPager.setAdapter(pictureAdapter);

    }

    private void initDatas() {
        mPagerIndicator.setTitles(mTitles);

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
        };
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
