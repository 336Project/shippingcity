package com.ateam.shippingcity.fragment;

import com.ateam.shippingcity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 我的进价片段
 * @author Administrator
 *
 */
public class MyQuoteFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_my_quote, null);
	}
}
