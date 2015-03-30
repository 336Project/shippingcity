package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.TextViewPair;

import android.os.Bundle;
import android.text.Html;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午4:51:46
 * @TODO 积分规则
 */
public class PersonalIntegralRuleActivity extends HBaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("积分规则");
		setBaseContentView(R.layout.activity_personal_integral_rule);
		((TextViewPair)findViewById(R.id.txt1)).setNameText(Html.fromHtml("<font color='#858585'>发布货盘</font><br><small><font color='#FC9B9B'>每天积分上限：100</font></small>"));
	}
}
