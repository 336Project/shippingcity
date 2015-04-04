package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.HomeActivity;
import com.ateam.shippingcity.R;
import com.ateam.shippingcity.utils.CheckUpdateUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午9:37:03
 * @TODO 设置
 */
public class PersonalSettingActivity extends HBaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("设置");
		setBaseContentView(R.layout.activity_personal_setting);
		init();
	}

	private void init() {
		((TextView)findViewById(R.id.txt_version)).setText("当前版本"+CheckUpdateUtil.getInstance(this).getVersionName());
		findViewById(R.id.layout_check_update).setOnClickListener(this);
		findViewById(R.id.txt_feedback).setOnClickListener(this);
		findViewById(R.id.txt_guide).setOnClickListener(this);
		findViewById(R.id.btn_logout).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_check_update://检测更新
			CheckUpdateUtil.getInstance(this).check(true);
			break;
		case R.id.txt_feedback://意见反馈
			jump(this, PersonalFeedbackActivity.class);
			break;
		case R.id.txt_guide:
			jump(this, PersonalGuideActivity.class);
			break;
		case R.id.btn_logout://退出登录
			mBaseApp.setUser(null);
			mBaseApp.setUserssid(null);
			Intent intent=new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setClass(this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
