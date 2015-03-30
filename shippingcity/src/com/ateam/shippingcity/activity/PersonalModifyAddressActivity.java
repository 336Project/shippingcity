package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午3:28:54
 * @TODO 修改公司地址
 */
public class PersonalModifyAddressActivity extends HBaseActivity {
	private HAutoCompleteTextView mEditName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("公司地址");
		getRightIcon().setVisibility(View.VISIBLE);
		setBaseContentView(R.layout.activity_personal_modify_address);
		
		mEditName=(HAutoCompleteTextView) findViewById(R.id.et_address);
		mEditName.setText(getIntent().getExtras().get("address").toString());
	}
}
