package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.HomeActivity;
import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午2:10:12
 * @TODO 完善资料
 */
public class PersonalPerfectInfoActivity extends HBaseActivity implements OnClickListener{
	private String mobile;
	private HAutoCompleteTextView mEditPassword;
	private HAutoCompleteTextView mEditConfirmPassword;//确认密码
	private HAutoCompleteTextView mEditTruename;//真实姓名
	private HAutoCompleteTextView mEditCompany;//公司
	private HAutoCompleteTextView mEditInviterMobile;//邀请人手机号码
	private Button mBtnCommit;//提交
	private PersonalAccess<Map<String, String>> access;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("完善资料");
		setBaseContentView(R.layout.activity_personal_perfect_info);
		init();
	}

	private void init() {
		mobile=getIntent().getStringExtra("mobile");
		mEditPassword=(HAutoCompleteTextView) findViewById(R.id.et_password);
		mEditConfirmPassword=(HAutoCompleteTextView) findViewById(R.id.et_confirm_password);
		mEditTruename=(HAutoCompleteTextView) findViewById(R.id.et_true_name);
		mEditCompany=(HAutoCompleteTextView) findViewById(R.id.et_company);
		mEditInviterMobile=(HAutoCompleteTextView) findViewById(R.id.et_inviter_mobile);
		mBtnCommit=(Button) findViewById(R.id.btn_commit);
		mBtnCommit.setOnClickListener(this);
		initRequest();
	}

	private void initRequest() {
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String, String>>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				
				return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				
				if(result.getStatusCode().equals("200")){
					showMsg(PersonalPerfectInfoActivity.this, "注册成功");
					Intent intent=new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.setClass(PersonalPerfectInfoActivity.this, HomeActivity.class);
					startActivity(intent);
					finish();
				}else{
					showMsg(PersonalPerfectInfoActivity.this, result.getMessage());
				}
				
			}
		};
		access=new PersonalAccess<Map<String, String>>(this, requestCallback);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_commit:
			String password=mEditPassword.getText().toString();
			String cpassword=mEditConfirmPassword.getText().toString();
			String truename=mEditTruename.getText().toString();
			String company=mEditCompany.getText().toString();
			String invitermobile=mEditInviterMobile.getText().toString();
			if(TextUtils.isEmpty(password)){
				showMsg(this, "密码不能为空");
				return ;
			}
			if(!password.equals(cpassword)){
				showMsg(this, "两次输入的密码不一致，请重新输入");
				return ;
			}
			if(TextUtils.isEmpty(truename)){
				showMsg(this, "真实姓名不能为空");
				return ;
			}
			if(TextUtils.isEmpty(company)){
				showMsg(this, "公司名称不能为空");
				return ;
			}
			access.register(mobile, password, cpassword, truename, company, invitermobile);
			break;

		default:
			break;
		}
	}
}
