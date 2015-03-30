package com.ateam.shippingcity.fragment;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.PalletAndQuoteCommonActivity;
import com.ateam.shippingcity.utils.SceenUtils;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 我的进价片段
 * 
 * @author Administrator
 * 
 */
public class MyQuoteFragment extends Fragment implements OnClickListener {

	private PalletAndQuoteCommonActivity activity;
	private PopupWindow pop_select;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		PalletAndQuoteCommonActivity palletAndQuoteCommonActivity = (PalletAndQuoteCommonActivity) getActivity();
		palletAndQuoteCommonActivity.getRightIcon().setVisibility(View.VISIBLE);
		View inflate = inflater.inflate(R.layout.fragment_my_quote, null);
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.fl_my_quote, new MyQuoteAllFragment());
		ft.commit();
		init();
		initSelectPopupWindow();
		return inflate;
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
		pop_select = new PopupWindow(view_select,SceenUtils.dip2px(getActivity(), 80),  LayoutParams.WRAP_CONTENT, true);
		pop_select.setBackgroundDrawable(new BitmapDrawable());
	}

	private void init() {
		activity = (PalletAndQuoteCommonActivity) getActivity();
		activity.getRightTxt().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop_select.showAsDropDown(activity.getRightTxt(), 0, -SceenUtils.dip2px(getActivity(), 5));
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_all:
			selectFragment(R.id.fl_my_quote, new MyQuoteAllFragment());
			break;
		case R.id.tv_sea_transport:
			selectFragment(R.id.fl_my_quote, new MyQuoteSeaTransportationFragment());
			break;
		default:
			break;
		}
	}
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
}
