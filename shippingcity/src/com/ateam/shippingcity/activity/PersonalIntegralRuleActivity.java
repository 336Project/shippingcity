package com.ateam.shippingcity.activity;


import java.util.List;


import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.IntegralRule;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.TextViewPair;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午4:51:46
 * @TODO 积分规则
 */
public class PersonalIntegralRuleActivity extends HBaseActivity {
	private LinearLayout mLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("积分规则");
		setBaseContentView(R.layout.activity_personal_integral_rule);
		//((TextViewPair)findViewById(R.id.txt1)).setNameText(Html.fromHtml("<font color='#858585'>发布货盘</font><br><small><font color='#FC9B9B'>每天积分上限：100</font></small>"));
		mLayout=(LinearLayout) findViewById(R.id.layout_integral_detail);
		request();
	}

	private void request() {
		HRequestCallback<Respond<List<IntegralRule>>> requestCallback=new HRequestCallback<Respond<List<IntegralRule>>>() {
			
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				onLoadFail();
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<IntegralRule>> parseJson(String jsonStr) {
				java.lang.reflect.Type type = new TypeToken<Respond<List<IntegralRule>>>() {
				}.getType();
				return (Respond<List<IntegralRule>>) JSONParse.jsonToObject(jsonStr, type);
			}
			
			@Override
			public void onSuccess(Respond<List<IntegralRule>> result) {
				if(result.isSuccess()){
					setupView(result.getDatas());
					/*for (IntegralRule rule : result.getDatas()) {
						System.out.println(rule.toString());
					}*/
				}
			}
		};
		PersonalAccess<List<IntegralRule>> access=new PersonalAccess<List<IntegralRule>>(this, requestCallback);
		access.getCreditRule(mBaseApp.getUserssid());
		
	}
	/**
	 * 
	 * 2015-4-2 下午1:55:46
	 * @param rule
	 * @TODO 设置规则详情
	 */
	private void setupView(List<IntegralRule> rules){
		if(rules!=null){
			for (IntegralRule rule : rules) {
				if(!TextUtils.isEmpty(rule.getCredit())){
					View view=LayoutInflater.from(this).inflate(R.layout.item_integral_rule, null);
					TextViewPair pair=(TextViewPair) view.findViewById(R.id.txt_rule);
					pair.setNameText(rule.getName());
					pair.setValueText(rule.getCredit());
					mLayout.addView(view);
				}
			}
		}
		/*if(rule!=null){
			String value;
			View view;
			value=rule.getCredit_edit();
			if(!TextUtils.isEmpty(value)){
				view=LayoutInflater.from(this).inflate(R.layout.item_integral_rule, null);
				TextViewPair pair=(TextViewPair) view.findViewById(R.id.txt_rule);
				pair.setNameText("完善个人资料");
				pair.setValueText(value);
				mLayout.addView(view);
			}
			value=rule.getCredit_companyedit();
			if(!TextUtils.isEmpty(value)){
				view=LayoutInflater.from(this).inflate(R.layout.item_integral_rule, null);
				TextViewPair pair=(TextViewPair) view.findViewById(R.id.txt_rule);
				pair.setNameText("完善公司资料");
				pair.setValueText(value);
				mLayout.addView(view);
			}
			value=rule.getCredit_login();
			if(!TextUtils.isEmpty(value)){
				view=LayoutInflater.from(this).inflate(R.layout.item_integral_rule, null);
				TextViewPair pair=(TextViewPair) view.findViewById(R.id.txt_rule);
				pair.setNameText("24小时登录");
				pair.setValueText(value);
				mLayout.addView(view);
			}
			value=rule.getCredit_user();
			if(!TextUtils.isEmpty(value)){
				view=LayoutInflater.from(this).inflate(R.layout.item_integral_rule, null);
				TextViewPair pair=(TextViewPair) view.findViewById(R.id.txt_rule);
				pair.setNameText("引导一位会员注册");
				pair.setValueText(value);
				mLayout.addView(view);
			}
			value=rule.getCredit_ip();
			if(!TextUtils.isEmpty(value)&&!TextUtils.isEmpty(rule.getCredit_maxip())){
				view=LayoutInflater.from(this).inflate(R.layout.item_integral_rule, null);
				TextViewPair pair=(TextViewPair) view.findViewById(R.id.txt_rule);
				pair.setNameText(Html.fromHtml("<font color='#858585'>引导一个IP访问</font><br><small><font color='#FC9B9B'>24小时积分上限："+rule.getCredit_maxip()+"</font></small>"));
				pair.setValueText(value);
				mLayout.addView(view);
			}
		}*/
	}
}
