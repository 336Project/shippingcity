package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;

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
 * @TODO 修改名字
 */
public class PersonalModifyMobileActivity extends HBaseActivity implements OnClickListener{
	private HAutoCompleteTextView mEditMobile;
	private TextView mTxtGetCode;
	private GetCodeTimer codeTimer=new GetCodeTimer(60*1000, 1000);
	private LinearLayout mLayoutSuccess;
	private LinearLayout mLayoutModify;
	private Button mBtnModify;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("手机号修改");
		setBaseContentView(R.layout.activity_personal_modify_mobile);
		
		mEditMobile=(HAutoCompleteTextView) findViewById(R.id.et_mobile);
		mEditMobile.setText(getIntent().getExtras().get("mobile").toString());
		mTxtGetCode=(TextView) findViewById(R.id.txt_get_code);
		mTxtGetCode.setOnClickListener(this);
		mLayoutModify=(LinearLayout) findViewById(R.id.layout_modify);
		mLayoutSuccess=(LinearLayout) findViewById(R.id.layout_modify_success);
		mLayoutModify.setVisibility(View.VISIBLE);
		mLayoutSuccess.setVisibility(View.GONE);
		mBtnModify=(Button) findViewById(R.id.btn_modify_mobile);
		mBtnModify.setOnClickListener(this);
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
		case R.id.txt_get_code:
			codeTimer.start();
			break;
		case R.id.btn_modify_mobile:
			mLayoutModify.setVisibility(View.GONE);
			mLayoutSuccess.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
