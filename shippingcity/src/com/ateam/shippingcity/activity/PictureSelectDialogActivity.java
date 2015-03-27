package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午5:57:14
 * @TODO 图片选择弹窗
 */
public class PictureSelectDialogActivity extends Activity implements OnClickListener{
	public static final int REQUEST_CODE_PICK_IMAGE=1001;
	public static final int REQUEST_CODE_CAMERA=1002;
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
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_CAMERA);
			break;
		case R.id.txt_select_picture://选择照片
//			Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
//			startActivityForResult(intent,REQUEST_CODE_PICK_IMAGE);
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);  
			//intent.addCategory(Intent.CATEGORY_OPENABLE);  
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"), REQUEST_CODE_PICK_IMAGE);
			break;
		case R.id.txt_cancel://取消
			setResult(RESULT_CANCELED);
			finish();
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri uri=null;
		if (resultCode == RESULT_OK){
			if(requestCode==REQUEST_CODE_PICK_IMAGE){
				uri=data.getData();
			}else if(requestCode==REQUEST_CODE_CAMERA){
				Bundle bundle = data.getExtras();  
                uri = (Uri) bundle.get(MediaStore.EXTRA_OUTPUT);
			}
		}
		if(uri!=null){
			Intent intent=new Intent();
			intent.setData(uri);
			setResult(RESULT_OK, data);
			finish();
		}else{
			Toast.makeText(this, "获取图片失败，请选择一张图片", Toast.LENGTH_SHORT).show();
		}
	}
}
