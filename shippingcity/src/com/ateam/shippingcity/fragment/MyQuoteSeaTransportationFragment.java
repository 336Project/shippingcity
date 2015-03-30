package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.PalletAndQuoteCommonActivity;
import com.ateam.shippingcity.adapter.TabFtagmentAdapter;
import com.ateam.shippingcity.widget.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我的进价-全部片段
 * @author Administrator
 *
 */
public class MyQuoteSeaTransportationFragment extends Fragment{
	public static final String[] TAB_TITLE={"待确认报价","历史报价"};
	private View inflate;
	private PalletAndQuoteCommonActivity activity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.fragment_my_quote_all, container, false);
		initTab();
		init();
		return inflate;
	}
	private void init() {
		activity = (PalletAndQuoteCommonActivity) getActivity();
		activity.getRightTxt().setText("全部");
	}
	/**
	 * 添加tab中的内容
	 */
	private void initTab() {
		List<Fragment> fragments=new ArrayList<Fragment>();
		fragments.add(new MyQuoteToConfirmFragment("海运"));
		fragments.add(new MyQuoteToHistoryFragment("海运"));
		TabFtagmentAdapter adapter=new TabFtagmentAdapter(getChildFragmentManager(),TAB_TITLE,null,fragments);
		ViewPager vp_my_quote_all=(ViewPager) inflate.findViewById(R.id.vp_my_quote_all);
		vp_my_quote_all.setAdapter(adapter);
		vp_my_quote_all.setOffscreenPageLimit(2);
		TabPageIndicator mTabIndicator= (TabPageIndicator)inflate.findViewById(R.id.tab_indicator);
		mTabIndicator.setViewPager(vp_my_quote_all);
	}
}
