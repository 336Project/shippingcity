package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
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
 * 我的进价-陆运片段
 * @author Administrator
 *
 */
public class MyQuoteLandTransportationFragment extends Fragment{
	private MainActivity activity;
	private View inflate;
	public static final String[] TAB_TITLE={"待确认报价","历史报价"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.fragment_my_quote_all, container, false);
		initTab();
		init();
		return inflate;
	}
	private void init() {
		activity = (MainActivity) getActivity();
		activity.getRightTxt().setText("陆运");
	}
	/**
	 * 添加tab中的内容
	 */
	private void initTab() {
		List<Fragment> fragments=new ArrayList<Fragment>();
		fragments.add(new MyQuoteToConfirmFragment());
		fragments.add(new MyQuoteToHistoryFragment());
		TabFtagmentAdapter adapter=new TabFtagmentAdapter(getChildFragmentManager(),TAB_TITLE,null,fragments);
		ViewPager vp_my_quote_all=(ViewPager) inflate.findViewById(R.id.vp_my_quote_all);
		vp_my_quote_all.setAdapter(adapter);
		vp_my_quote_all.setOffscreenPageLimit(2);
		TabPageIndicator mTabIndicator= (TabPageIndicator)inflate.findViewById(R.id.tab_indicator);
		mTabIndicator.setViewPager(vp_my_quote_all);
	}
}
