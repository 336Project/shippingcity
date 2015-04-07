package com.ateam.shippingcity.activity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.model.User;
import com.ateam.shippingcity.utils.JSONParse;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午1:54:03
 * @TODO 个人信息
 */
public class PersonalInfoActivity extends HBaseActivity implements OnClickListener{
	public static final int REQ_MODIFY_MOBILE=100;
	public static final int REQ_MODIFY_NAME=101;
	public static final int REQ_MODIFY_ADDRESS=102;
	public static final int REQ_CODE_P=1000;
	public static final int REQ_CODE_I=1001;
	public static final int REQ_CODE_W=1002;
	public static final int REQ_CODE_B=1003;
	private User user;
	private TextView mTxtUserName;
	private TextView mTxtMobile;
	private TextView mTxtAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("个人信息");
		setBaseContentView(R.layout.activity_personal_info);
		user=mBaseApp.getUser();
		initView();
	}

	private void initView() {
		if(user==null)return;
		findViewById(R.id.layout_modify_mobile).setOnClickListener(this);
		findViewById(R.id.layout_modify_name).setOnClickListener(this);
		findViewById(R.id.layout_pick_portrait).setOnClickListener(this);
		findViewById(R.id.layout_pick_business_licence).setOnClickListener(this);
		findViewById(R.id.layout_pick_IDCard).setOnClickListener(this);
		findViewById(R.id.layout_pick_work_card).setOnClickListener(this);
		findViewById(R.id.layout_modify_address).setOnClickListener(this);
		findViewById(R.id.txt_modify_password).setOnClickListener(this);
		mTxtUserName=(TextView) findViewById(R.id.txt_username);
		mTxtUserName.setText(user.getTruename());//姓名
		mTxtMobile=(TextView) findViewById(R.id.txt_mobile);
		mTxtMobile.setText(user.getMobile());//手机号
		mTxtAddress=(TextView) findViewById(R.id.txt_address);
		if(!TextUtils.isEmpty(user.getCom_address())){
			mTxtAddress.setText(user.getCom_address());//公司地址
		}
		ImageView avatar=(ImageView) findViewById(R.id.iv_avatar);//头像
		if(!TextUtils.isEmpty(user.getAvatar())){
			ImageLoader.getInstance().displayImage(user.getAvatar(), avatar);
		}
		if(!TextUtils.isEmpty(user.getVpicture().get(0))){
			ImageLoader.getInstance().displayImage(user.getVpicture().get(0), (ImageView)findViewById(R.id.iv_vtruename));//身份证
		}
		TextView txtTruename=((TextView)findViewById(R.id.txt_vtruename_status));//实名认证状态
		txtTruename.setText(user.getStatus_truename());
		if(user.getVtruename().equals("3")){
			txtTruename.setTextColor(Color.rgb(70,159,233));
			txtTruename.setCompoundDrawablesWithIntrinsicBounds(R.drawable.personal_information_a_certified_icon, 0, 0, 0);
		}
		if(!TextUtils.isEmpty(user.getVpicture().get(1))){
			ImageLoader.getInstance().displayImage(user.getVpicture().get(1), (ImageView)findViewById(R.id.iv_card));//工牌
		}
		TextView txtStatus=((TextView)findViewById(R.id.txt_card_status));//工牌认证状态
		txtStatus.setText(user.getStatus_truename());
		if(user.getVtruename().equals("3")){
			txtStatus.setTextColor(Color.rgb(70,159,233));
			txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.personal_information_a_certified_icon, 0, 0, 0);
		}
		((TextView)findViewById(R.id.txt_company_name)).setText(user.getCompany());//公司名称
		if(!TextUtils.isEmpty(user.getVpicture().get(2))){
			ImageLoader.getInstance().displayImage(user.getVpicture().get(2), (ImageView)findViewById(R.id.iv_vcompany));//营业执照
		}
		TextView txtCompany=((TextView)findViewById(R.id.txt_vcompany_status));//公司认证状态
		txtCompany.setText(user.getStatus_company());
		if(user.getVcompany().equals("3")){
			txtCompany.setTextColor(Color.rgb(70,159,233));
			txtCompany.setCompoundDrawablesWithIntrinsicBounds(R.drawable.personal_information_a_certified_icon, 0, 0, 0);
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_modify_mobile://修改手机号
			Intent intent=new Intent(this, PersonalModifyMobileActivity.class);
			intent.putExtra("mobile", mTxtMobile.getText().toString());
			startActivityForResult(intent, REQ_MODIFY_MOBILE);
			break;
		case R.id.layout_modify_name://修改名字
			intent=new Intent(this, PersonalModifyNameActivity.class);
			intent.putExtra("name", mTxtUserName.getText().toString());
			startActivityForResult(intent, REQ_MODIFY_NAME);
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
		case R.id.layout_modify_address://修改公司地址
			intent=new Intent(this, PersonalModifyAddressActivity.class);
			intent.putExtra("address", mTxtAddress.getText().toString());
			startActivityForResult(intent, REQ_MODIFY_ADDRESS);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode , int resultCode , Intent data) {
		if(resultCode==RESULT_OK){
			if(requestCode==REQ_CODE_P){//头像
				Uri uri=data.getData();
				uploadAvatar(uri);
			}else if(requestCode==REQ_CODE_I){//身份证
				
			}else if(requestCode==REQ_CODE_W){//工牌
				
			}else if(requestCode==REQ_CODE_B){//营业执照
				
			}else if(requestCode==REQ_MODIFY_MOBILE){//修改手机号
				mTxtMobile.setText(data.getStringExtra("mobile"));
			}else if(requestCode==REQ_MODIFY_NAME){//修改名称
				mTxtUserName.setText(data.getStringExtra("name"));
			}else if(requestCode==REQ_MODIFY_ADDRESS){//修改公司地址
				mTxtAddress.setText(data.getStringExtra("address"));
			}
			//System.out.println("uri----"+data.getData().toString());
		}
	}
	/**
	 * 
	 * 2015-4-3 下午3:38:17
	 * @param uri
	 * @TODO 上传头像
	 */
	private void uploadAvatar(Uri uri){
		if(uri!=null){
			ContentResolver cr=getContentResolver();
			InputStream is=null;
			try {
				is=cr.openInputStream(uri);
			} catch (FileNotFoundException e) {
			}
			if(is!=null){
				HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String, String>>>() {
					
					@SuppressWarnings("unchecked")
					@Override
					public Respond<Map<String, String>> parseJson(String jsonStr) {
						return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
					}
					
					@Override
					public void onSuccess(Respond<Map<String, String>> result) {
						showMsg(PersonalInfoActivity.this, result.getMessage());
					}
				};
				PersonalAccess<Map<String, String>> access=new PersonalAccess<Map<String,String>>(this,requestCallback);
				access.modifyAvatar(mBaseApp.getUserssid(), is);
			}
		}else{
			showMsg(this, "图片错误");
		}
	}
}
