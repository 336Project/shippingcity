package com.ateam.shippingcity.fragment;

import com.ateam.shippingcity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 我的进价-全部片段
 * @author Administrator
 *
 */
public class MyQuoteAllFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_my_quote_all, null);
		return inflate;
	}
}
