package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
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
		getRightIcon().setVisibility(View.VISIBLE);
		getRightIcon().setOnClickListener(this);
		setBaseContentView(R.layout.activity_personal_modify_password);
		
		mEditOldPassword=(HAutoCompleteTextView) findViewById(R.id.et_old_password);
		mEditNewPassword=(HAutoCompleteTextView) findViewById(R.id.et_new_password);
		mEditConfirmPassword=(HAutoCompleteTextView) findViewById(R.id.et_confirm_password);
	}
	@Override
	public void onClick(View v) {
		save();
	}
	
	private void save() {
		if(!mEditNewPassword.getText().toString().equals(mEditConfirmPassword.getText().toString())){
			showMsg(this, "两次输入的密码不同，请再次确认");
			return ;
		}
	}
}
