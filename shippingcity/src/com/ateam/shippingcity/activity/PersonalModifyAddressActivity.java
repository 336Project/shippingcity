package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午3:28:54
 * @TODO 修改公司地址
 */
public class PersonalModifyAddressActivity extends HBaseActivity implements OnClickListener{
	private HAutoCompleteTextView mEditAddress;
	private String address="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("公司地址");
		getRightTxt().setVisibility(View.VISIBLE);
		getRightTxt().setText("保存");
		getRightTxt().setTextColor(getResources().getColor(R.color.txt_sumbit_color));
		getRightTxt().setOnClickListener(this);
		setBaseContentView(R.layout.activity_personal_modify_address);
		
		mEditAddress=(HAutoCompleteTextView) findViewById(R.id.et_address);
		mEditAddress.setText(getIntent().getExtras().get("address").toString());
		address=mEditAddress.getText().toString();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_right:
			address=mEditAddress.getText().toString();
			if(address.equals("")){
				showMsg(this, "名字不能为空");
			}
			modityAddress();
			break;

		default:
			break;
		}
	}
	
	private void modityAddress() {
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String,String>>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				if(result.isSuccess()){
					mBaseApp.getUser().setCom_address(address);
					Intent data=new Intent();
					data.putExtra("address", address);
					setResult(RESULT_OK, data);
					finish();
				}
				showMsg(PersonalModifyAddressActivity.this, result.getMessage());
			}
		};
		PersonalAccess<Map<String, String>> access=new PersonalAccess<Map<String,String>>(this, requestCallback);
		access.modifyAddress(mBaseApp.getUserssid(), address);
	}
}
