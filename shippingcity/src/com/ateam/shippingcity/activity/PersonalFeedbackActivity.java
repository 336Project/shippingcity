package com.ateam.shippingcity.activity;

import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午11:39:37
 * @TODO 意见反馈
 */
public class PersonalFeedbackActivity extends HBaseActivity implements OnClickListener{
	private PersonalAccess<Map<String, String>> access;
	private EditText mEditContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("意见反馈");
		getRightTxt().setVisibility(View.VISIBLE);
		getRightTxt().setText("提交");
		getRightTxt().setOnClickListener(this);
		getRightTxt().setTextColor(getResources().getColor(R.color.txt_sumbit_color));
		setBaseContentView(R.layout.activity_personal_feedback);
		init();
	}

	private void init() {
		mEditContent=(EditText) findViewById(R.id.et_feedback_content);
		HRequestCallback<Respond<Map<String, String>>> requestCallback=new HRequestCallback<Respond<Map<String,String>>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Respond<Map<String, String>> parseJson(String jsonStr) {
				return (Respond<Map<String, String>>) JSONParse.jsonToBean(jsonStr, Respond.class);
			}
			
			@Override
			public void onSuccess(Respond<Map<String, String>> result) {
				if(result.getStatusCode().equals("200")){
					showMsg(PersonalFeedbackActivity.this, "提交成功");
					finish();
				}else{
					showMsg(PersonalFeedbackActivity.this, result.getMessage());
				}
			}
		};
		access=new PersonalAccess<Map<String,String>>(this, requestCallback);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_right://提交
			sumbit();
			break;

		default:
			break;
		}
	}
	
	private void sumbit() {
		if(TextUtils.isEmpty(mEditContent.getText().toString())){
			showMsg(this, "内容不能为空");
			return;
		}
		access.feedback(mBaseApp.getUserssid(), mEditContent.getText().toString());
	}
}
