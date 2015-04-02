package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午3:28:54
 * @TODO 修改名字
 */
public class PersonalModifyNameActivity extends HBaseActivity implements OnClickListener{
	private HAutoCompleteTextView mEditName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("名字");
		getRightTxt().setText("保存");
		getRightTxt().setTextColor(getResources().getColor(R.color.txt_sumbit_color));
		getRightTxt().setOnClickListener(this);
		setBaseContentView(R.layout.activity_personal_modify_name);
		
		mEditName=(HAutoCompleteTextView) findViewById(R.id.et_name);
		mEditName.setText(getIntent().getExtras().get("name").toString());
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_right://保存
			if(mEditName.getText().toString().equals("")){
				showMsg(this, "名字不能为空");
			}
			modityName();
			break;

		default:
			break;
		}
	}
	
	private void modityName(){
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String,String>>>() {
			
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				return null;
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				if(result.isSuccess()){
					mBaseApp.getUser().setTruename(mEditName.getText().toString());
					finish();
				}
				showMsg(PersonalModifyNameActivity.this, result.getMessage());
			}
		};
		PersonalAccess<Map<String, String>> access=new PersonalAccess<Map<String,String>>(this, requestCallback);
		access.modifyUsername(mBaseApp.getUserssid(), mEditName.getText().toString());
	}
}
