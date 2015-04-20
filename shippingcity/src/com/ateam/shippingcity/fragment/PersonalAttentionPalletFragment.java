package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.adapter.TabFtagmentAdapter;
import com.ateam.shippingcity.widget.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 关注的货盘区片段
 */
public class PersonalAttentionPalletFragment extends Fragment {

	private View view;
	private ViewPager tabPager;
	private List<Fragment> fragments;
	public static final String[] TAB_TITLE={"全部","海运","空运","陆运"};
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
	}
	/**
	 * 添加tab中的内容
	 */
	private void initTab() {
		fragments=new ArrayList<Fragment>();
		fragments.add(getFragment("全部"));
		fragments.add(getFragment("海运"));
		fragments.add(getFragment("空运"));
		fragments.add(getFragment("陆运"));
		TabFtagmentAdapter adapter=new TabFtagmentAdapter(getChildFragmentManager(),TAB_TITLE,null,fragments);
		tabPager=(ViewPager) view.findViewById(R.id.tab_pager);
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
	private PersoanlAttentionPalletTransportFragment getFragment(String type){
		PersoanlAttentionPalletTransportFragment pallet=new PersoanlAttentionPalletTransportFragment();
		Bundle bundle = new Bundle();  
		bundle.putString("type", type);  
		pallet.setArguments(bundle); 
		return pallet;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==1000){
				int index=tabPager.getCurrentItem();
				if(index==0){
					((PersoanlAttentionPalletTransportFragment)fragments.get(0)).request();
					((PersoanlAttentionPalletTransportFragment)fragments.get(1)).request();
					((PersoanlAttentionPalletTransportFragment)fragments.get(2)).request();
					((PersoanlAttentionPalletTransportFragment)fragments.get(3)).request();
				}else if(index==1){
					((PersoanlAttentionPalletTransportFragment)fragments.get(0)).request();
					((PersoanlAttentionPalletTransportFragment)fragments.get(index)).request();
				}else if(index==2){
					((PersoanlAttentionPalletTransportFragment)fragments.get(0)).request();
					((PersoanlAttentionPalletTransportFragment)fragments.get(index)).request();
				}else if(index==3){
					((PersoanlAttentionPalletTransportFragment)fragments.get(0)).request();
					((PersoanlAttentionPalletTransportFragment)fragments.get(index)).request();
				}
				
			}
		}
	}
}
