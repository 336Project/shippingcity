package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午5:57:14
 * @TODO 图片选择弹窗
 */
public class PictureSelectDialogActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_select_dialog);
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.BOTTOM);
		findViewById(R.id.txt_select_picture).setOnClickListener(this);
		findViewById(R.id.txt_take_piuture).setOnClickListener(this);
		findViewById(R.id.txt_cancel).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_take_piuture://拍照
			
			break;
		case R.id.txt_select_picture://选择照片
			
			break;
		case R.id.txt_cancel://取消
			setResult(RESULT_CANCELED);
			finish();
			break;

		default:
			break;
		}
	}
}
