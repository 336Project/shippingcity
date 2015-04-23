package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.HomeActivity;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午3:28:54
 * @TODO 修改手机号
 */
public class PersonalModifyMobileActivity extends HBaseActivity implements OnClickListener{
	public static final int STEP1=1;
	public static final int STEP2=2;
	public static final int STEP3=3;
	
	private HAutoCompleteTextView mEditMobile;
	private HAutoCompleteTextView mEditCode;
	private TextView mTxtGetCode;
	private GetCodeTimer codeTimer=new GetCodeTimer(60*1000, 1000);
	private LinearLayout mLayoutSuccess;
	private LinearLayout mLayoutModify;
	private Button mBtnModify;
	
	private PersonalAccess<Map<String, String>> access;
	private int step=STEP1;
	private String mobile="";
	private TextView mTxtMessage;
	
	private boolean isModifySuccess=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("手机号修改");
		setBaseContentView(R.layout.activity_personal_modify_mobile);
		getLeftIcon().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		init();
	}
	
	private void init(){
		mEditMobile=(HAutoCompleteTextView) findViewById(R.id.et_mobile);
		mEditCode=(HAutoCompleteTextView) findViewById(R.id.et_code);
		mEditMobile.setText(getIntent().getStringExtra("mobile"));
		mobile=mEditMobile.getText().toString();
		mTxtGetCode=(TextView) findViewById(R.id.txt_get_code);
		mTxtGetCode.setOnClickListener(this);
		mLayoutModify=(LinearLayout) findViewById(R.id.layout_modify);
		mLayoutSuccess=(LinearLayout) findViewById(R.id.layout_modify_success);
		mLayoutModify.setVisibility(View.VISIBLE);
		mLayoutSuccess.setVisibility(View.GONE);
		mBtnModify=(Button) findViewById(R.id.btn_modify_mobile);
		mBtnModify.setOnClickListener(this);
		mTxtMessage=(TextView) findViewById(R.id.txt_message);
		initRequset();
	}
	/**
	 * 
	 * 2015-4-2 下午3:46:15
	 * @TODO 初始化请求
	 */
	private void initRequset() {
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
					if(!result.isSuccess()){
						codeTimer.onFinish();
					}
					showMsg(PersonalModifyMobileActivity.this, result.getMessage());
					break;
				case STEP2:
					if(result.isSuccess()){
						step=STEP3;
						access.modifyMobile(mBaseApp.getUserssid(), mobile);
					}else{
						showMsg(PersonalModifyMobileActivity.this, result.getMessage());
					}
					break;
				case STEP3:
					if(result.isSuccess()){
						mBaseApp.getUser().setMobile(mobile);
						mTxtMessage.setText("您新的手机号"+mobile+"\n已绑定成功!");
						mLayoutModify.setVisibility(View.GONE);
						mLayoutSuccess.setVisibility(View.VISIBLE);
						mLayoutSuccess.setOnClickListener(PersonalModifyMobileActivity.this);
						isModifySuccess=true;
					}else{
						showMsg(PersonalModifyMobileActivity.this, result.getMessage());
					}
					break;
				default:
					break;
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
			mobile=mEditMobile.getText().toString();
			if(mobile.equals("")){
				showMsg(this, "手机号码不能为空");
				return;
			}
			getCode();
			break;
		case R.id.btn_modify_mobile://修改并绑定
			if(mobile.equals("")){
				showMsg(this, "手机号码不能为空");
				return;
			}
			if(mEditCode.getText().toString().equals("")){
				showMsg(this, "验证码不能为空");
				return;
			}
			step=STEP2;
			access.checkGeneralMobileMode(mBaseApp.getUserssid(),mobile, mEditCode.getText().toString());
			break;
		case R.id.layout_modify_success:
			mBaseApp.setUser(null);
			mBaseApp.setUserssid("");
			Intent intent=new Intent(this, HomeActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			showMsg(this, "手机号码修改成功，请重新登录");
			break;
		default:
			break;
		}
	}
	/**
	 * 
	 * 2015-4-2 下午3:44:59
	 * @TODO 获取验证码
	 */
	public void getCode(){
		step=STEP1;
		codeTimer.start();
		access.getGeneralMobileMode(mBaseApp.getUserssid(), mobile);
	}
	
	@Override
	public void onBackPressed() {
		if(isModifySuccess&&mLayoutSuccess.getVisibility()==View.VISIBLE){
			/*Intent data=new Intent();
			data.putExtra("mobile", mobile);
			setResult(RESULT_OK, data);*/
			mLayoutSuccess.performClick();
		}else{
			finish();
		}
	}
	
}
