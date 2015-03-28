package com.ateam.shippingcity.fragment;

import com.ateam.shippingcity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我的进价-陆运片段
 * @author Administrator
 *
 */
public class MyQuoteLandTransportationFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_my_quote_land_transport, null);
		return inflate;
	}
}
