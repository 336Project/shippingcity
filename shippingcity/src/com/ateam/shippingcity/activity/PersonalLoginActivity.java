package com.ateam.shippingcity.activity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.LoginAndRegistAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.application.HBaseApp;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.model.User;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

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
	private HAutoCompleteTextView mEditUsername;
	private HAutoCompleteTextView mEditPassword;
	
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
		findViewById(R.id.btn_login).setOnClickListener(this);
		mEditUsername=(HAutoCompleteTextView) findViewById(R.id.et_username);
		mEditPassword=(HAutoCompleteTextView) findViewById(R.id.et_password);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_goto_regist://注册
			startActivity(new Intent(this, PersonalRegistActivity.class));
			break;
		case R.id.txt_forget_password://忘记密码
			startActivity(new Intent(this, PersonalForgetPasswordActivity.class));
			break;
		case R.id.btn_login:
			login();
			break;
		default:
			break;
		}
	}
	/**
	 * 
	 * 2015-3-30 下午5:11:26
	 * @TODO 登录
	 */
	private void login() {
		HRequestCallback<Respond<User>> requestCallback=new HRequestCallback<Respond<User>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Respond<User> parseJson(String jsonStr) {
				Type type=new com.google.gson.reflect.TypeToken<Respond<User>>(){}.getType();
				return (Respond<User>) JSONParse.jsonToObject(jsonStr, type);
			}
			
			@Override
			public void onSuccess(Respond<User> result) {
				if(result.getStatusCode().equals("200")&&result.getDatas()!=null){
					MyToast.showShort(PersonalLoginActivity.this, "登录成功");
					((HBaseApp)getApplication()).setUserssid(result.getDatas().getUserssid());
					finish();
				}else{
					String message="";
					try {
						message=new String(result.getMessage().getBytes(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						message="操作失败，请稍后再试";
					}
					MyToast.showShort(PersonalLoginActivity.this, message);
				}
				
			}
		};
		LoginAndRegistAccess access=new LoginAndRegistAccess(PersonalLoginActivity.this, requestCallback);
		access.login(mEditUsername.getText().toString(), mEditPassword.getText().toString());
	}
}
