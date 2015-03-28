package com.ateam.shippingcity.fragment;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.PalletAndQuoteCommonActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
/**
 * 我的进价片段
 * @author Administrator
 *
 */
public class MyQuoteFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		PalletAndQuoteCommonActivity palletAndQuoteCommonActivity = (PalletAndQuoteCommonActivity) getActivity();
		palletAndQuoteCommonActivity.getRightIcon().setVisibility(View.VISIBLE);
		View inflate = inflater.inflate(R.layout.fragment_my_quote, null);
		FrameLayout fl_my_quote = (FrameLayout) inflate.findViewById(R.id.fl_my_quote);
		
		return inflate;
	}
}
