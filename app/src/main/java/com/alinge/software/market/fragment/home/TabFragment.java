package com.alinge.software.market.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alinge.software.market.R;
import com.alinge.software.market.adapter.AppAdapter;
import com.alinge.software.market.adapter.decoration.AppItemDecoration;
import com.alinge.software.market.fragment.base.VolleyResponseFragment;
import com.alinge.software.market.net.VolleyUtils;
import com.alinge.software.market.net.bean.AppInfo;
import com.alinge.software.market.net.bean.QualityResultInfo;
import com.alinge.software.market.net.utils.UrlUtils;
import com.alinge.software.market.utils.LogUtils;
import com.alinge.software.market.view.LoadPageView;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFragment extends VolleyResponseFragment implements SwipeRefreshLayout.OnRefreshListener,AppAdapter.OnItemClickListener {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private LoadPageView loadPageView;
    private AppAdapter mAppAdapter;
    private  SwipeRefreshLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            LogUtils.info(null, "title:" + mTitle + "------oncreate---------");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.info(null, "title:" + mTitle + "------onDetach---------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.info(null, "title:" + mTitle + "------onDestroy---------");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadPageView = new LoadPageView(getActivity()) {
            @Override
            public View createSuccessView() {
                return TabFragment.this.createSuccessView();
            }

            @Override
            public void loadData() {
                load();
            }
        };
        layout=new SwipeRefreshLayout(getActivity());
        SwipeRefreshLayout.LayoutParams lp=new SwipeRefreshLayout.LayoutParams(-1,-1);
        layout.addView(loadPageView,lp);
        layout.setColorSchemeColors(R.color.HomeTitleBarBg);
        layout.setOnRefreshListener(this);
        return layout;

    }

    /**
     * 创建成功时候的view
     *
     * @return
     */
    private View createSuccessView() {
        LayoutInflater inflate = LayoutInflater.from(getActivity());
        View view = inflate.inflate(R.layout.fragment_tab, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new AppItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAppAdapter = new AppAdapter(getActivity());
        recyclerView.setAdapter(mAppAdapter);
        return view;
    }

    /**
     * 加载网络数据
     */
    private void load() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", UrlUtils.getAppKey(getActivity().getApplicationContext()));
        params.put("productId", UrlUtils.PRODUCT_ID);
        params.put("count", UrlUtils.ITEM_COUNT);
        params.put("bannerCount", UrlUtils.ITEM_COUNT);
        params.put("machineName", UrlUtils.getMachineType());
        VolleyUtils.requestGet(UrlUtils.QUALITY_MAIN_URI, params, this);
    }


    /**
     * 加载成功回调
     *
     * @param volleyError
     */
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null) {
            loadPageView.stateChange(LoadPageView.STATE_ERROR);
        }
        layout.setRefreshing(false);
    }

    /**
     * 加载失败回调
     * @param object
     */
    @Override
    public void onResponse(Object object) {
        if (!TextUtils.isEmpty(object.toString())) {
            try {
                QualityResultInfo result = new QualityResultInfo();
                result.fromJson((JSONObject) object);
                if(result.getResultCode()>0){
                    List<AppInfo> apps=null;
                    if (Type.HOTS.equals(mTitle)) {
                        apps=result.getGiftsLists();
                    } else if (Type.RECOMMEND.equals(mTitle)) {
                        apps=result.getRecommendLists();
                    } else if (Type.NEWS.equals(mTitle)) {
                        apps=result.getNewestLists();
                    }
                    mAppAdapter.setLists(apps);
                    loadPageView.stateChange(LoadPageView.STATE_SUCCESS);
                }else{
                    loadPageView.stateChange(LoadPageView.STATE_ERROR);
                }

            } catch (Exception e) {
                loadPageView.stateChange(LoadPageView.STATE_ERROR);
            }
        } else {
            loadPageView.stateChange(LoadPageView.STATE_EMPTY);
        }
        layout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        load();
    }

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onItemClick(View itemView) {
       AppInfo app = (AppInfo)itemView.getTag();
        if(app!=null){
            Toast.makeText(getActivity(),app.getSoftwareName(),Toast.LENGTH_SHORT).show();
        }
    }
}
