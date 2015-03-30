package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
	private FrameLayout mLayoutAddPhoto;//添加显示图片布局
	private Button mBtnFocus;//关注按钮
	private Button mBtnMyOffer;//我的报价按钮
	private View mLineAddPhoto;//线

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("货盘详情");
		setBaseContentView(R.layout.activity_pallet_detail);
		initView();
		initData();
	}
	
	/**
	 * 控件实例化
	 */
	private void initView(){
		mTvTransportType=(TextView)findViewById(R.id.tv_transportType);
		mTvBeginPlace=(TextView)findViewById(R.id.tv_beginPlace);
		mTvEndPlace=(TextView)findViewById(R.id.tv_endPlace);
		mTvGoBeginTime=(TextView)findViewById(R.id.tv_goBeginTime);
		mTvGoEndTime=(TextView)findViewById(R.id.tv_goEndTime);
		mTvBoxType=(TextView)findViewById(R.id.tv_boxType);
		mLineAddPhoto=(View)findViewById(R.id.view_addPhoto);
		mLinearAddPhoto=(LinearLayout)findViewById(R.id.layout_addPhoto);
		mLayoutAddPhoto=(FrameLayout)findViewById(R.id.frame_addPhoto);
		mTvNotice=(TextView)findViewById(R.id.tv_notice);
		mTvOfferEndTime=(TextView)findViewById(R.id.tv_offerEndTime);
		mBtnFocus=(Button)findViewById(R.id.btn_focus);
		mBtnMyOffer=(Button)findViewById(R.id.btn_myOffer);
	}
	
	/**
	 * 重新提交数据
	 */
	private void initData(){
		mBtnFocus.setOnClickListener(this);
		mBtnMyOffer.setOnClickListener(this);
		mLayoutAddPhoto.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.layout_addPhoto:
			
			break;
		case R.id.btn_focus:
			
			break;
		case R.id.btn_myOffer:
	
			break;

		default:
			break;
		}
	}

}
