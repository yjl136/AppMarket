package com.alinge.software.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alinge.software.market.R;
import com.alinge.software.market.utils.LogUtils;

public class TabFragment_backup extends Fragment
{
	public static final String TITLE = "title";
	private String mTitle = "Defaut Value";
	private TextView mTextView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mTitle = getArguments().getString(TITLE);
			LogUtils.info(null,"title:"+mTitle+"------oncreate---------");
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_tab_backup, container, false);
		mTextView = (TextView) view.findViewById(R.id.id_info);
		mTextView.setText(mTitle);
		return view;

	}

	public static TabFragment_backup newInstance(String title)
	{
		TabFragment_backup tabFragment = new TabFragment_backup();
		Bundle bundle = new Bundle();
		bundle.putString(TITLE, title);
		tabFragment.setArguments(bundle);
		return tabFragment;
	}

}
