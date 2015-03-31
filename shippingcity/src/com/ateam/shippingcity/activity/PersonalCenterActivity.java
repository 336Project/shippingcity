package com.ateam.shippingcity.activity;

import java.lang.reflect.Type;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.application.HBaseApp;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.model.User;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.imageview.CircleImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午1:52:57
 * @TODO 个人中心
 */
public class PersonalCenterActivity extends Activity implements OnClickListener{
	private CircleImageView mPortrait;//头像
	private TextView mUsername;//姓名
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
		
		mPortrait=(CircleImageView) findViewById(R.id.iv_user_portrait);
		mUsername=(TextView) findViewById(R.id.txt_username);
		User user=((HBaseApp)getApplication()).getUser();
		if(user!=null){
			mUsername.setText(user.getTruename());
		}else{
			request();
		}
	}

	private void request() {
		HRequestCallback<Respond<User>> requestCallback=new HRequestCallback<Respond<User>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Respond<User> parseJson(String jsonStr) {
				Type type=new com.google.gson.reflect.TypeToken<Respond<User>>(){}.getType();
				return (Respond<User>) JSONParse.jsonToObject(jsonStr, type);
			}
			
			@Override
			public void onSuccess(Respond<User> result) {
				if(result.getDatas()!=null){
					((HBaseApp)getApplication()).setUser(result.getDatas());
					mUsername.setText(result.getDatas().getTruename());
				}
			}
		};
		PersonalAccess<User> access=new PersonalAccess<User>(this, requestCallback);
		access.setIsShow(false);
		access.getUserInfo(((HBaseApp)getApplication()).getUserssid());
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
