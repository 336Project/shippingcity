package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午3:28:54
 * @TODO 修改密码
 */
public class PersonalModifyPasswordActivity extends HBaseActivity implements OnClickListener{
	private HAutoCompleteTextView mEditOldPassword;
	private HAutoCompleteTextView mEditNewPassword;
	private HAutoCompleteTextView mEditConfirmPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("密码修改");
		getRightTxt().setVisibility(View.VISIBLE);
		getRightTxt().setText("保存");
		getRightTxt().setTextColor(getResources().getColor(R.color.txt_sumbit_color));
		getRightTxt().setOnClickListener(this);
		setBaseContentView(R.layout.activity_personal_modify_password);
		
		mEditOldPassword=(HAutoCompleteTextView) findViewById(R.id.et_old_password);
		mEditNewPassword=(HAutoCompleteTextView) findViewById(R.id.et_new_password);
		mEditConfirmPassword=(HAutoCompleteTextView) findViewById(R.id.et_confirm_password);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_right:
			save();
			break;

		default:
			break;
		}
	}
	
	private void save() {
		if(!mEditNewPassword.getText().toString().equals(mEditConfirmPassword.getText().toString())){
			showMsg(this, "两次输入的密码不同，请再次确认");
			return ;
		}
		
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String, String>>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				if(result.isSuccess()){
					finish();
				}
				showMsg(PersonalModifyPasswordActivity.this, result.getMessage());
			}
		};
		
		PersonalAccess<Map<String, String>> access=new PersonalAccess<Map<String,String>>(this, requestCallback);
		access.modifyPassword(mBaseApp.getUserssid(), mEditOldPassword.getText().toString(), mEditNewPassword.getText().toString());
	}
}
