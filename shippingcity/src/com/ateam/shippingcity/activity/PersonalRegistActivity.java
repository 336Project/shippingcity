package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 上午11:54:56
 * @TODO 注册
 */
public class PersonalRegistActivity extends HBaseActivity implements OnClickListener{
	private TextView mTxtGetCode;
	private GetCodeTimer codeTimer=new GetCodeTimer(60*1000, 1000);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("注册");
		setBaseContentView(R.layout.activity_regist);
		init();
	}
	
	private void init() {
		mTxtGetCode=(TextView) findViewById(R.id.txt_get_code);
		mTxtGetCode.setOnClickListener(this);
		findViewById(R.id.txt_protocol).setOnClickListener(this);
		findViewById(R.id.btn_regist).setOnClickListener(this);
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
		case R.id.txt_get_code://获取验证码
			codeTimer.start();
			break;
		case R.id.txt_protocol://协议
			jump(this, PersonalRegistProtocolActivity.class);
			break;
		case R.id.btn_regist:
			jump(this, PersonalPerfectInfoActivity.class);
			break;
		default:
			break;
		}
	}
}
