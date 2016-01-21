package com.alinge.software.market.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alinge.software.market.R;
import com.alinge.software.market.net.VolleyUtils;
import com.alinge.software.market.net.utils.UrlUtils;
import com.alinge.software.market.utils.LogUtils;
import com.alinge.software.market.view.LoadPageView;
import com.android.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
public class TabFragment extends VolleyResponseFragment{
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private LoadPageView loadPageView;
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
        return loadPageView;

    }

    /**
     * 创建成功时候的view
     * @return
     */
    private View createSuccessView() {
         LayoutInflater inflate= LayoutInflater.from(getActivity());
        View view=inflate.inflate(R.layout.fragment_tab, null);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
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
        VolleyUtils.requestGet(UrlUtils.QUALITY_MAIN_URI, params,this);
    }


    /**
     * 加载成功回调
     * @param volleyError
     */
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if(volleyError!=null){
            loadPageView.stateChange(LoadPageView.STATE_ERROR);
        }
    }

    /**
     * 加载失败回调
     * @param object
     */
    @Override
    public void onResponse(Object object) {
        if(!TextUtils.isEmpty(object.toString())){
            loadPageView.stateChange(LoadPageView.STATE_SUCCESS);
        }else{
            loadPageView.stateChange(LoadPageView.STATE_EMPTY);
        }
    }

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

}
