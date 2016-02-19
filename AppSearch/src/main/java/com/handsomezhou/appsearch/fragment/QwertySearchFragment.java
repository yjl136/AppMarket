package com.handsomezhou.appsearch.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.handsomezhou.appsearch.R;
import com.handsomezhou.appsearch.adapter.AppInfoAdapter;
import com.handsomezhou.appsearch.helper.AppInfoHelper;
import com.handsomezhou.appsearch.model.AppInfo;
import com.handsomezhou.appsearch.util.AppUtil;
import com.handsomezhou.appsearch.util.ViewUtil;
import com.handsomezhou.appsearch.view.SearchBox;
import com.handsomezhou.appsearch.view.SearchBox.OnSearchBox;

public class QwertySearchFragment extends BaseFragment implements OnSearchBox{
	private static final String TAG="QwertySearchFragment";
	private GridView mQwertySearchGv;
	private TextView mSearchResultPromptTv;
	private SearchBox mSearchBox;
	private AppInfoAdapter mAppInfoAdapter;
	
	@Override
	public void onResume() {
		refreshView();
		super.onResume();
	}
	
	@Override
	protected void initData() {
		setContext(getActivity());
		mAppInfoAdapter = new AppInfoAdapter(getContext(),
				R.layout.app_info_grid_item, AppInfoHelper.getInstance()
						.getQwertySearchAppInfos());
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_qwerty_search, container, false);
		mQwertySearchGv=(GridView) view.findViewById(R.id.qwerty_search_grid_view);
		mQwertySearchGv.setAdapter(mAppInfoAdapter);
		mSearchResultPromptTv = (TextView) view
				.findViewById(R.id.search_result_prompt_text_view);
		mSearchBox=(SearchBox) view.findViewById(R.id.search_box);
		mSearchBox.setOnSearchBox(this);
		return view;
	}

	@Override
	protected void initListener() {
		mQwertySearchGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo=(AppInfo) parent.getItemAtPosition(position);
				AppUtil.startApp(getContext(), appInfo);
				
			}
		});

		mQwertySearchGv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo=(AppInfo) parent.getItemAtPosition(position);
	
				AppUtil.uninstallApp(getContext(), appInfo);
			
				return true;
			}
		});

	}

	/*start: OnSearchBox*/
	@Override
	public void onSearchTextChanged(String curCharacter) {
		search(curCharacter);
		refreshView();
		
	}
	/*end: OnSearchBox*/
	
	
	public void refreshView() {
		refreshQwertySearchGv();
	}
	
	public void updateSearch(){
		if(null==mSearchBox){
			return;
		}
		
		search(mSearchBox.getSearchEtInput());
	}
	
	private void refreshQwertySearchGv() {
		if (null == mQwertySearchGv) {
			return;
		}

		BaseAdapter baseAdapter = (BaseAdapter) mQwertySearchGv.getAdapter();
		Log.i(TAG, "getCount"+baseAdapter.getCount()+"");
		if (null != baseAdapter) {
			baseAdapter.notifyDataSetChanged();
			if (baseAdapter.getCount() > 0) {
				ViewUtil.showView(mQwertySearchGv);
				ViewUtil.hideView(mSearchResultPromptTv);
			} else {
				ViewUtil.hideView(mQwertySearchGv);
				ViewUtil.showView(mSearchResultPromptTv);
			}
		}
	}
	
	private void search(String keyword) {
		Log.i(TAG, "search=["+keyword+"]");
		String curCharacter;
		if (null == keyword) {
			curCharacter = keyword;
		} else {
			curCharacter = keyword.trim();
		}
		
		if (TextUtils.isEmpty(curCharacter)) {
			AppInfoHelper.getInstance().qwertySearch(null);
		} else {
			AppInfoHelper.getInstance().qwertySearch(curCharacter);
		}
	}
	

}
