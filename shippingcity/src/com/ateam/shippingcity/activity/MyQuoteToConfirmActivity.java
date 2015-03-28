package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyQuoteToConfirmActivity extends HBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_quote_to_confirm);
		init();
	}

	private void init() {
		setActionBarTitle("货盘区");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_quote_to_confirm, menu);
		return true;
	}

}
