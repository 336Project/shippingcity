package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午3:28:54
 * @TODO 修改名字
 */
public class PersonalModifyNameActivity extends HBaseActivity {
	private HAutoCompleteTextView mEditName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("名字");
		getRightIcon().setVisibility(View.VISIBLE);
		setBaseContentView(R.layout.activity_modify_name);
		
		mEditName=(HAutoCompleteTextView) findViewById(R.id.et_name);
		mEditName.setText(getIntent().getExtras().get("name").toString());
	}
}
