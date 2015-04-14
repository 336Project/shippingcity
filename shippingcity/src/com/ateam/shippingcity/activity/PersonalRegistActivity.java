package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 上午11:54:56
 * @TODO 注册
 */
public class PersonalRegistActivity extends HBaseActivity implements OnClickListener{
	public static final int STEP1=1;//获取验证码
	public static final int STEP2=2;//
	private TextView mTxtGetCode;
	private GetCodeTimer codeTimer=new GetCodeTimer(60*1000, 1000);
	private HAutoCompleteTextView mEditMobile;
	private HAutoCompleteTextView mEditCode;
	private CheckBox mCheckAgress;
	private PersonalAccess<Map<String, String>> access;
	
	//private String mobile="";
	private int step=STEP1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("注册");
		setBaseContentView(R.layout.activity_regist);
		init();
	}
	
	private void init() {
		mTxtGetCode=(TextView) findViewById(R.id.txt_get_code);
		mTxtGetCode.setOnClickListener(this);
		findViewById(R.id.txt_protocol).setOnClickListener(this);
		findViewById(R.id.btn_regist).setOnClickListener(this);
		mEditMobile=(HAutoCompleteTextView) findViewById(R.id.et_mobile);
		mEditCode=(HAutoCompleteTextView) findViewById(R.id.et_code);
		mCheckAgress=(CheckBox) findViewById(R.id.cb_agree);
		initRequest();
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
			step=STEP1;
			//mobile=mEditMobile.getText().toString();
			if(mEditMobile.getText().toString().length()>0){
				codeTimer.start();
				access.getMobileCodeRegister(mEditMobile.getText().toString());
			}else{
				showMsg(this, "请输入手机号码");
			}
			break;
		case R.id.txt_protocol://协议
			jump(this, PersonalRegistProtocolActivity.class);
			break;
		case R.id.btn_regist:
			step=STEP2;
			if(mEditMobile.getText().toString().length()<=0){
				showMsg(this, "请输入手机号码");
				return;
			}
			if(mCheckAgress.isChecked()){
				if(mEditCode.getText().toString().length()>0){
					access.checkMobileCode(mEditMobile.getText().toString(), mEditCode.getText().toString());
				}else{
					showMsg(this, "请输入验证码");
				}
			}else{
				showMsg(this, "请先同意注册协议");
			}
			break;
		default:
			break;
		}
	}
	

	/**
	 * 
	 * 2015-3-31 下午5:32:29
	 * @TODO 获取验证码
	 */
	private void initRequest() {
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String,String>>>() {
			
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				codeTimer.onFinish();
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				
				return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				switch (step) {
					case STEP1:
						if(!result.getStatusCode().equals("200")){
							codeTimer.onFinish();
						}
						showMsg(PersonalRegistActivity.this, result.getMessage());
						break;
					case STEP2:
						if(result.getStatusCode().equals("200")){
							Intent intent=new Intent(PersonalRegistActivity.this, PersonalPerfectInfoActivity.class);
							intent.putExtra("mobile", mEditMobile.getText().toString());
							startActivity(intent);
						}else{
							showMsg(PersonalRegistActivity.this, result.getMessage());
						}
						break;
					default:
						break;
				}
				
				
			}
		};
		access=new PersonalAccess<Map<String,String>>(this, requestCallback);
	}
}
