package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyQuoteSeaTransportLCLActivity extends HBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_my_quote_sea_transport_lcl);
		init();
	}

	private void init() {
		setActionBarTitle("货盘详情");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_quote_sea_transport_lcl, menu);
		return true;
	}

}