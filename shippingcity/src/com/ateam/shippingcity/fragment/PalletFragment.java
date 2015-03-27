package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.id;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.adapter.TabFtagmentAdapter;
import com.ateam.shippingcity.widget.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 货盘区片段
 */
public class PalletFragment extends Fragment {

	private View view;
	public static final String[] TAB_TITLE={"申请中","投标中","还款中","已完成"};
	
	public PalletFragment() {
		// Required empty public constructor
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		initTab();
		view=inflater.inflate(R.layout.fragment_pallet, container, false);
		return view;
	}
	
	/**
	 * 添加tab中的内容
	 */
	private void initTab() {
		List<Fragment> fragments=new ArrayList<Fragment>();
		fragments.add(new PalletSeaTransportFragment());
		fragments.add(new PalletSeaTransportFragment());
		fragments.add(new PalletSeaTransportFragment());
		TabFtagmentAdapter adapter=new TabFtagmentAdapter(getChildFragmentManager(),TAB_TITLE,null,fragments);
		ViewPager tabPager=(ViewPager) view.findViewById(R.id.tab_pager);
		tabPager.setAdapter(adapter);
		tabPager.setOffscreenPageLimit(4);
		TabPageIndicator mTabIndicator= (TabPageIndicator)view.findViewById(R.id.tab_indicator);
		mTabIndicator.setViewPager(tabPager);
	}

}
