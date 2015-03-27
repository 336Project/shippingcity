package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午1:54:03
 * @TODO 个人信息
 */
public class PersonalInfoActivity extends HBaseActivity implements OnClickListener{
	private TextView mTxtUserName;
	private TextView mTxtMobile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("个人信息");
		setBaseContentView(R.layout.activity_personal_info);
		initView();
	}

	private void initView() {
		findViewById(R.id.layout_modify_mobile).setOnClickListener(this);
		findViewById(R.id.layout_modify_name).setOnClickListener(this);
		findViewById(R.id.layout_portrait).setOnClickListener(this);
		mTxtUserName=(TextView) findViewById(R.id.txt_username);
		mTxtMobile=(TextView) findViewById(R.id.txt_mobile);
		findViewById(R.id.txt_modify_password).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_modify_mobile://修改手机号
			Bundle b=new Bundle();
			b.putString("mobile", mTxtMobile.getText().toString());
			jump(this, PersonalModifyMobileActivity.class,b);
			break;
		case R.id.layout_modify_name://修改名字
			b=new Bundle();
			b.putString("name", mTxtUserName.getText().toString());
			jump(this, PersonalModifyNameActivity.class,b);
			break;
		case R.id.layout_portrait://修改头像
			startActivityForResult(new Intent(this, PictureSelectDialogActivity.class), 1000);
			break;
		case R.id.txt_modify_password://修改密码
			jump(this, PersonalModifyPasswordActivity.class);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode , int resultCode , Intent data) {
		if(resultCode==RESULT_OK){
			
		}
	}
}
