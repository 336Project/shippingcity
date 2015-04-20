package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 
 * @author 李晓伟
 * 2015-3-28 上午11:46:40
 * @TODO 忘记密码
 */
public class PersonalForgetPasswordActivity extends HBaseActivity implements OnClickListener{
	private TextView mTxtGetCode;
	private GetCodeTimer codeTimer=new GetCodeTimer(60*1000, 1000);
	private PersonalAccess<Map<String, String>> access;
	private boolean isGetCode=false;
	private HAutoCompleteTextView mEditCode;
	private HAutoCompleteTextView mEditMobile;
	private HAutoCompleteTextView mEditPassword;
	private HAutoCompleteTextView mEditConfirmPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("忘记密码");
		setBaseContentView(R.layout.activity_forget_password);
		init();
	}
	
	private void init() {
		mEditCode=(HAutoCompleteTextView) findViewById(R.id.et_code);
		mEditPassword=(HAutoCompleteTextView) findViewById(R.id.et_new_password);
		mEditConfirmPassword=(HAutoCompleteTextView) findViewById(R.id.et_confirm_password);
		mEditMobile=(HAutoCompleteTextView) findViewById(R.id.et_mobile);
		mTxtGetCode=(TextView) findViewById(R.id.txt_get_code);
		mTxtGetCode.setOnClickListener(this);
		findViewById(R.id.btn_sure).setOnClickListener(this);
		initRequest();
	}

	private void initRequest() {
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String,String>>>() {
			
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				if(isGetCode){
					codeTimer.onFinish();
				}
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				
				return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				showMsg(PersonalForgetPasswordActivity.this, result.getMessage());
				if(result.isSuccess()){
					if(!isGetCode){
						//showMsg(PersonalForgetPasswordActivity.this, "操作成功");
						finish();
					}
				}else{
					if(isGetCode){
						codeTimer.onFinish();
						//showMsg(PersonalForgetPasswordActivity.this, "手机号码格式错误");
					}
				}
			}
		};
		access=new PersonalAccess<Map<String,String>>(this, requestCallback);
	}

	/**
	 * 
	 * @author 李晓伟
	 * 2015-3-27 下午4:14:16
	 * @TODO
	 */
	class GetCodeTimer extends CountDownTimer{

		public GetCodeTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			if(codeTimer!=null){
				codeTimer.cancel();
			}
			mTxtGetCode.setEnabled(true);
			mTxtGetCode.setText("获取验证码");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			mTxtGetCode.setEnabled(false);
			mTxtGetCode.setText((millisUntilFinished/1000)+"秒");
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_get_code://获取验证码
			if(TextUtils.isEmpty(mEditMobile.getText().toString())){
				showMsg(this, "手机号码不能为空");
				return;
			}
			isGetCode=true;
			codeTimer.start();
			access.getMobileCodeFindpw(mEditMobile.getText().toString());
			break;
		case R.id.btn_sure:
			if(TextUtils.isEmpty(mEditMobile.getText().toString())){
				showMsg(this, "手机号码不能为空");
				return;
			}
			if(TextUtils.isEmpty(mEditCode.getText().toString())){
				showMsg(this, "验证码不能为空");
				return;
			}
			if(mEditPassword.getText().toString().equals("")){
				showMsg(this, "密码不能为空");
				return;
			}
			if(!mEditPassword.getText().toString().equals(mEditConfirmPassword.getText().toString())){
				showMsg(this, "两次输入的密码不同，请重新输入");
				return;
			}
			isGetCode=false;
			access.resetPassword(mEditMobile.getText().toString(), mEditCode.getText().toString(), mEditPassword.getText().toString());
			break;

		default:
			break;
		}
	}
}
