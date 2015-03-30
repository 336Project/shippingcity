package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyQuoteHistoryActivity extends HBaseActivity {

	private TextView tv_mode_of_transportation; //运输方式
	private TextView tv_placeBegin; //起点
	private TextView tv_placeEnd; //终点
	private TextView tv_transportTimeBegin;//运输开始时间
	private TextView tv_transportTimeEnd;//运输结束时间
	private TextView tv_palletDescribe; //货盘描述
	private TextView tv_remark;//备注
	private TextView tv_quotation_deadline;//报价截止日期
	private ImageView iv_winType;//中标状态

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_my_quote_history);
		init();
		initView();
	}
	private void init() {
		setActionBarTitle("货盘详情");
	}
	private void initView() {
		tv_mode_of_transportation = (TextView) findViewById(R.id.tv_mode_of_transportation);
		tv_placeBegin = (TextView) findViewById(R.id.tv_placeBegin);
		tv_placeEnd = (TextView) findViewById(R.id.tv_placeEnd);
		tv_transportTimeBegin = (TextView) findViewById(R.id.tv_transportTimeBegin);
		tv_transportTimeEnd = (TextView) findViewById(R.id.tv_transportTimeEnd);
		tv_palletDescribe = (TextView) findViewById(R.id.tv_palletDescribe);
		tv_remark = (TextView) findViewById(R.id.tv_remark);
		tv_quotation_deadline = (TextView) findViewById(R.id.tv_quotation_deadline);
		iv_winType = (ImageView) findViewById(R.id.iv_winType);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_quote_history, menu);
		return true;
	}

}
