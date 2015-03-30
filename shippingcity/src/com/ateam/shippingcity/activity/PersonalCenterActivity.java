package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午1:52:57
 * @TODO 个人中心
 */
public class PersonalCenterActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personl_center_dialog);
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.TOP);
		
		init();
	}

	private void init() {
		findViewById(R.id.layout_personal_info).setOnClickListener(this);
		findViewById(R.id.txt_setting).setOnClickListener(this);
		findViewById(R.id.txt_my_integral).setOnClickListener(this);
		findViewById(R.id.txt_invite_friend).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_personal_info://个人信息
			startActivity(new Intent(this, PersonalInfoActivity.class));
			break;
		case R.id.txt_my_integral://我的积分
			startActivity(new Intent(this, PersonalMyIntegralActivity.class));
			break;
		case R.id.txt_invite_friend://邀请好友
			startActivity(new Intent(this, PersonalInviteFriendActivity.class));
			break;
		case R.id.txt_setting://设置
			startActivity(new Intent(this, PersonalSettingActivity.class));
			break;
		default:
			break;
		}
		finish();
	}
}
