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
	public static final int REQ_CODE_P=1000;
	public static final int REQ_CODE_I=1001;
	public static final int REQ_CODE_W=1002;
	public static final int REQ_CODE_B=1003;
	
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
		findViewById(R.id.layout_pick_portrait).setOnClickListener(this);
		findViewById(R.id.layout_pick_business_licence).setOnClickListener(this);
		findViewById(R.id.layout_pick_IDCard).setOnClickListener(this);
		findViewById(R.id.layout_pick_work_card).setOnClickListener(this);
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
		case R.id.layout_pick_portrait://修改头像
			startActivityForResult(new Intent(this, PictureSelectDialogActivity.class), REQ_CODE_P);
			break;
		case R.id.layout_pick_IDCard://修改身份证
			startActivityForResult(new Intent(this, PictureSelectDialogActivity.class), REQ_CODE_I);
			break;
		case R.id.layout_pick_work_card://修改工牌
			startActivityForResult(new Intent(this, PictureSelectDialogActivity.class), REQ_CODE_W);
			break;
		case R.id.layout_pick_business_licence://修改营业执照
			startActivityForResult(new Intent(this, PictureSelectDialogActivity.class), REQ_CODE_B);
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
			if(requestCode==REQ_CODE_P){//头像
				
			}else if(requestCode==REQ_CODE_I){//身份证
				
			}else if(requestCode==REQ_CODE_W){//工牌
				
			}else if(requestCode==REQ_CODE_B){//营业执照
				
			}
			System.out.println("uri----"+data.getData().toString());
		}
	}
}
