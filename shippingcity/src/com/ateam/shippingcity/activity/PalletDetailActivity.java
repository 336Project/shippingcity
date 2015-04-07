package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.constant.MyConstant;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.utils.SysUtil;
import com.ateam.shippingcity.widget.imageview.PictureDialogActivity;
import com.ateam.shippingcity.widget.weinxinImageShow.ImagePagerActivity;
import com.ateam.shippingcity.widget.weinxinImageShow.MyGridAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 货盘详情界面
 * @version 
 * @create_date 2015-3-28下午3:09:09
 */
public class PalletDetailActivity extends HBaseActivity implements OnClickListener{

	private TextView mTvTransportType;//运输方式
	private TextView mTvBeginPlace;//运输开始地
	private TextView mTvEndPlace;//运输终点
	private TextView mTvGoBeginTime;//走货开始时间
	private TextView mTvGoEndTime;//走货结束时间
	private TextView mTvBoxType;//装箱类型
	private TextView mTvNotice;//备注
	private TextView mTvOfferEndTime;//报价结束时间
	private LinearLayout mLinearAddPhoto;//添加显示图片布局
	private Button mBtnFocus;//关注按钮
	private Button mBtnMyOffer;//我的报价按钮
	private View mLineAddPhoto;//线
	private GridView mGvAddPhoto;//添加显示图片布局
	
	private PalletTransport mPallet;
	
	private String[] urls = {
            "http://img0.bdstatic.com/img/image/shouye/leimu/mingxing2.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=cdab1512d000baa1be2c44b3772bc82f/91529822720e0cf3855c96050b46f21fbf09aaa1.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=ccd33b46d53f8794d7ff4b26e2207fc9/0d338744ebf81a4c0f993437d62a6059242da6a1.jpg",
            "http://f.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=6b62f61bac6eddc422e7b7f309e0c7c0/6159252dd42a2834510deef55ab5c9ea14cebfa1.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D900%3Bq%3D90/sign=b8658f17f3d3572c62e290dcba28121a/5fdf8db1cb134954bb97309a574e9258d0094a47.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=e58fb67bc8ea15ce45eee301863b4bce/a5c27d1ed21b0ef4fd6140a0dcc451da80cb3e47.jpg"
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("货盘详情");
		setBaseContentView(R.layout.activity_pallet_detail);
		initView();
		initData();
		initGridView();
	}
	
	/**
	 * 控件实例化
	 */
	private void initView(){
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
		mTvTransportType=(TextView)findViewById(R.id.tv_transportType);
		mTvBeginPlace=(TextView)findViewById(R.id.tv_beginPlace);
		mTvEndPlace=(TextView)findViewById(R.id.tv_endPlace);
		mTvGoBeginTime=(TextView)findViewById(R.id.tv_goBeginTime);
		mTvGoEndTime=(TextView)findViewById(R.id.tv_goEndTime);
		mTvBoxType=(TextView)findViewById(R.id.tv_boxType);
		mLineAddPhoto=(View)findViewById(R.id.view_addPhoto);
		mLinearAddPhoto=(LinearLayout)findViewById(R.id.layout_addPhoto);
		mTvNotice=(TextView)findViewById(R.id.tv_notice);
		mTvOfferEndTime=(TextView)findViewById(R.id.tv_offerEndTime);
		mBtnFocus=(Button)findViewById(R.id.btn_focus);
		mBtnMyOffer=(Button)findViewById(R.id.btn_myOffer);
		mGvAddPhoto=(GridView)findViewById(R.id.gv_addPhoto);
		mBtnFocus.setOnClickListener(this);
		mBtnMyOffer.setOnClickListener(this);
		if(mPallet.ifbid!=null&&mPallet.ifbid.equals("1")){
			findViewById(R.id.tv_view).setVisibility(View.GONE);
			mBtnMyOffer.setText("我的报价");
		}else if(SysUtil.getRemainTime(mPallet.deadlinetime).equals("0小时")){
			mBtnFocus.setBackgroundDrawable(getResources().getDrawable(R.drawable.closing_quotes_submit_icon));
			mBtnFocus.setTextColor(getResources().getColor(R.color.white));
			mBtnFocus.setClickable(false);
			mBtnMyOffer.setBackgroundDrawable(getResources().getDrawable(R.drawable.closing_quotes_submit_icon));
			mBtnMyOffer.setTextColor(getResources().getColor(R.color.white));
			mBtnMyOffer.setClickable(false);
		}else{
			
		}
	}
	
	/**
	 * 配置横向滑动的gridview，并传值显示
	 */
	private void initGridView(){
		int size=0;
		if(urls.length>5){
			size=5;
		}else{
			size=urls.length;
		}
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
        		(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42*size, getResources().getDisplayMetrics()), 
                LinearLayout.LayoutParams.FILL_PARENT);  
        mGvAddPhoto.setLayoutParams(params);  
        mGvAddPhoto.setColumnWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, getResources().getDisplayMetrics()));  
        mGvAddPhoto.setHorizontalSpacing(1);  
        mGvAddPhoto.setStretchMode(GridView.NO_STRETCH);  
        mGvAddPhoto.setNumColumns(size); 
        String[] url=new String[5];
		if(urls.length>5){
			for (int i = 0; i < url.length; i++) {
				url[i]=urls[i];
			}
			mGvAddPhoto.setAdapter(new MyGridAdapter(url, this));
		}else{
			mGvAddPhoto.setAdapter(new MyGridAdapter(urls, this));
		}
		mGvAddPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SysUtil.showImage(PalletDetailActivity.this,position,urls);
			}
		});
	}
	
	/**
	 * 提交数据
	 */
	private void initData(){
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_focus:
			
			break;
		case R.id.btn_myOffer:
			
			break;

		default:
			break;
		}
	}
	
}
