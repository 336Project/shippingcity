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
 * @TODO 登录
 */
public class PersonalLoginActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personl_login_dialog);
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.TOP);
		init();
	}

	private void init() {
		findViewById(R.id.txt_goto_regist).setOnClickListener(this);
		findViewById(R.id.txt_forget_password).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_goto_regist://注册
			startActivity(new Intent(this, PersonalInfoActivity.class));
			break;
		case R.id.txt_forget_password://忘记密码
			startActivity(new Intent(this, PersonalSettingActivity.class));
			break;
		default:
			break;
		}
		finish();
	}
}
