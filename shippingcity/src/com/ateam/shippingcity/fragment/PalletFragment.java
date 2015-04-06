package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.id;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.activity.MainActivity;
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
	private MainActivity activity;
	public static final String[] TAB_TITLE={"全部","海运","空运","陆运"};
	
	public PalletFragment() {
		// Required empty public constructor
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view=inflater.inflate(R.layout.fragment_pallet, container, false);
		initTab();
		init();
		return view;
	}
	
	private void init() {
		activity = (MainActivity) getActivity();
		activity.getRightTxt().setVisibility(View.GONE);
		activity.getRightIcon().setVisibility(View.GONE);
	}
	/**
	 * 添加tab中的内容
	 */
	private void initTab() {
		List<Fragment> fragments=new ArrayList<Fragment>();
		fragments.add(getFragment("全部"));
		fragments.add(getFragment("海运"));
		fragments.add(getFragment("空运"));
		fragments.add(getFragment("陆运"));
		TabFtagmentAdapter adapter=new TabFtagmentAdapter(getChildFragmentManager(),TAB_TITLE,null,fragments);
		ViewPager tabPager=(ViewPager) view.findViewById(R.id.tab_pager);
		tabPager.setAdapter(adapter);
		tabPager.setOffscreenPageLimit(4);
		TabPageIndicator mTabIndicator= (TabPageIndicator)view.findViewById(R.id.tab_indicator);
		mTabIndicator.setViewPager(tabPager);
	}
	/**
	 * 获取片段
	 * @param type 
	 * @return
	 */
	private PalletTransportFragment getFragment(String type){
		PalletTransportFragment pallet=new PalletTransportFragment();
		Bundle bundle = new Bundle();  
		bundle.putString("type", type);  
		pallet.setArguments(bundle); 
		return pallet;
	}
}
