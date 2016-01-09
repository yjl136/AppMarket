package com.alinge.software.market;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.alinge.software.market.adapter.FragmentAdapter;
import com.alinge.software.market.listener.PageChangeListener;
import com.alinge.software.market.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mRadioGroup = (RadioGroup) findViewById(R.id.tabGroup);
        mViewPager.addOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
        });
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mRadioGroup.getChildCount());
        mViewPager.setAdapter(mFragmentAdapter);


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            RadioButton preButton;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton curButton = (RadioButton) group.findViewById(checkedId);
                if (curButton != null && preButton != curButton) {
                    if (preButton != null) {
                        preButton.setSelected(false);
                    }else{
                        group.getChildAt(0).setSelected(false);
                    }
                    curButton.setSelected(true);
                    preButton = curButton;
                }
                changeViewPager(checkedId);
            }
        });
        mRadioGroup.getChildAt(0).setSelected(true);
    }

    public void changeViewPager(int checkedId) {
       int position =getPosition(checkedId);
        mViewPager.setCurrentItem(position,true);
    }
    public int getPosition(int checkedId){
        int position = 0;
        switch (checkedId){
        case R.id.homeTab:
            position = 0;
                break;
        case R.id.typeTab:
            position = 1;
                break;
        case R.id.brandTab:
            position = 2;
                break;
        case R.id.managerTab:
            position = 3;
                break;
        }
        return  position;
    }
}
