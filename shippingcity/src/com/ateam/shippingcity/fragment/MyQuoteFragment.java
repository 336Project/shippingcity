package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.MainActivity;
import com.ateam.shippingcity.adapter.TabFtagmentAdapter;
import com.ateam.shippingcity.utils.SceenUtils;
import com.ateam.shippingcity.widget.viewpagerindicator.TabPageIndicator;
import com.nineoldandroids.animation.ObjectAnimator;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/**
 * 我的进价片段
 * 
 * @author Administrator
 * 
 */
public class MyQuoteFragment extends Fragment implements OnClickListener {

	private MainActivity activity;
	private PopupWindow pop_select;
	private ArrayList<TextView> tv_List=new ArrayList<TextView>();
	public static final String[] TAB_TITLE={"待确认报价","历史报价"};
	private View inflate;
	private List<Fragment> fragments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MainActivity palletAndQuoteCommonActivity = (MainActivity) getActivity();
		palletAndQuoteCommonActivity.getRightIcon().setVisibility(View.VISIBLE);
		palletAndQuoteCommonActivity.getRightTxt().setVisibility(View.VISIBLE);
		palletAndQuoteCommonActivity.getRightTxt().setText("全部");
		
		inflate = inflater.inflate(R.layout.fragment_my_quote, null);
		initTab();
		init();
		initSelectPopupWindow();
		return inflate;
	}
	/**
	 * 添加tab中的内容
	 */
	private void initTab() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new MyQuoteToConfirmFragment());
		fragments.add(new MyQuoteToHistoryFragment());
		TabFtagmentAdapter adapter=new TabFtagmentAdapter(getChildFragmentManager(),TAB_TITLE,null,fragments);
		ViewPager vp_my_quote_all=(ViewPager) inflate.findViewById(R.id.vp_my_quote_all);
		vp_my_quote_all.setAdapter(adapter);
		vp_my_quote_all.setOffscreenPageLimit(2);
		TabPageIndicator mTabIndicator= (TabPageIndicator)inflate.findViewById(R.id.tab_indicator);
		mTabIndicator.setViewPager(vp_my_quote_all);
	}
	
	private void initSelectPopupWindow() {
		View view_select = LayoutInflater.from(getActivity()).inflate(
				R.layout.pop_selectefrommyquote, null);
		TextView tv_all = (TextView) view_select.findViewById(R.id.tv_all);
		TextView tv_sea_transport = (TextView) view_select
				.findViewById(R.id.tv_sea_transport);
		TextView tv_air_transport = (TextView) view_select
				.findViewById(R.id.tv_air_transport);
		TextView tv_land_transport = (TextView) view_select
				.findViewById(R.id.tv_land_transport);
		tv_all.setOnClickListener(this);
		tv_sea_transport.setOnClickListener(this);
		tv_air_transport.setOnClickListener(this);
		tv_land_transport.setOnClickListener(this);
		tv_all.setSelected(true);
		tv_List.add(tv_all);
		tv_List.add(tv_sea_transport);
		tv_List.add(tv_air_transport);
		tv_List.add(tv_land_transport);
		
		pop_select = new PopupWindow(view_select,SceenUtils.dip2px(getActivity(), 80),  LayoutParams.WRAP_CONTENT, true);
		pop_select.setBackgroundDrawable(new BitmapDrawable());
		pop_select.setOnDismissListener(new OnDismissListener() {
			
			public void onDismiss() {
				ObjectAnimator oa = ObjectAnimator.ofFloat(activity.getRightIcon(), "rotationX",180, 0);
				oa.start();
			}
		});
	}

	private void init() {
		activity = (MainActivity) getActivity();
		activity.getRightTxt().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop_select.showAsDropDown(activity.getRightTxt(), 0, -SceenUtils.dip2px(getActivity(), 5));
				  ObjectAnimator oa = ObjectAnimator.ofFloat(activity.getRightIcon(), "rotationX",0, 180);
				  oa.start();
			}
		});
		activity.getRightTxt().setTextColor(Color.parseColor("#6CA2EF"));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_all:
//			selectFragment(R.id.fl_my_quote, new MyQuoteAllFragment());
			changeTvListState(0);
			break;
		case R.id.tv_sea_transport:
//			selectFragment(R.id.fl_my_quote, new MyQuoteSeaTransportationFragment());
			changeTvListState(1);
			break;
		case R.id.tv_air_transport:
//			selectFragment(R.id.fl_my_quote, new MyQuoteAirTransportationFragment());
			changeTvListState(2);
			break;
		case R.id.tv_land_transport:
//			selectFragment(R.id.fl_my_quote, new MyQuoteLandTransportationFragment());
			changeTvListState(3);
			break;
		default:
			break;
		}
	}
	@SuppressWarnings("unused")
	private void selectFragment(int id,Fragment fragment){
		dismissPop(pop_select);
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(id, fragment);
		ft.commit();
	}
	private void dismissPop(PopupWindow pop){
		if(pop!=null&&pop.isShowing()) pop.dismiss();
	}
	private void changeTvListState(int id){
		for (int i = 0; i < tv_List.size(); i++) {
			TextView textView = tv_List.get(i);
			if(id==i){
				textView.setSelected(true);
				MyQuoteToConfirmFragment fragment = (MyQuoteToConfirmFragment) fragments.get(0);
				fragment.select(i);
				fragment.setMode(i);
			}
			else{
				textView.setSelected(false);
			}
		}
		dismissPop(pop_select);
	}
}
