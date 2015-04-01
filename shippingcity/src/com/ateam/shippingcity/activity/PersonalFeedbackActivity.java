package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午11:39:37
 * @TODO 意见反馈
 */
public class PersonalFeedbackActivity extends HBaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("意见反馈");
		getRightTxt().setVisibility(View.VISIBLE);
		getRightTxt().setText("提交");
		setBaseContentView(R.layout.activity_personal_feedback);
	}
}
