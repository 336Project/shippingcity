package com.ateam.shippingcity.widget.imageview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ateam.shippingcity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
/**
 * 
 * @author 李晓伟
 * @Create_date 2014-9-16 上午10:03:18
 * @TODO 图片展示页
 */
public class PictureDialogActivity extends Activity implements OnClickListener{
	private ImageView mIvMyPicture;//要进行显示图片的控件
	private Bitmap bitmap;//获取要查看的图片对象
	
	private DisplayImageOptions options;//图片处理加载中的显示处理对象
	private ImageLoader imageLoader;//图片加载对象
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_picture_dialog);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		initView();
		setListener();
		Uri uri=null;
		Bundle  bundle=getIntent().getExtras();
		if(bundle!=null){
			uri=(Uri) bundle.get("uri");
		}
		if(uri!=null){
			imageLoader.displayImage(uri.toString(), mIvMyPicture, options);
		}else{
			Intent intent=getIntent();
			imageLoader.displayImage(intent.getStringExtra("url"), mIvMyPicture, options);
		}
	}

	public void initView(){
		mIvMyPicture=(ImageView)findViewById(R.id.iv_myPicture);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(null)//加载过程中显示的图片
		.showImageForEmptyUri(getResources().getDrawable(R.drawable.no_pic))//加载内容为空显示的图片
		.showImageOnFail(getResources().getDrawable(R.drawable.no_pic))//加载失败显示的图片
		.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer()).build();
	}
	
	public void setListener(){
		//mIvMyPicture.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_myPicture:
			finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(bitmap!=null&&!bitmap.isRecycled()){
			bitmap.recycle();
			bitmap=null;
		}
	}
	
}
