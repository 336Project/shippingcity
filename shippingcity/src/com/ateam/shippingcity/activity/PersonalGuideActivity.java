package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.ElasticScrollView;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午11:52:30
 * @TODO 使用指南
 */
public class PersonalGuideActivity extends HBaseActivity implements OnClickListener{
	private ElasticScrollView scrollView;
	private TextView mTxtTitle1;
	private TextView mTxtTitle2;
	private TextView mTxtTitle3;
	private TextView mTxtTitle4;
	private TextView mTxtTitle5;
	private TextView mTxtTitle6;
	
	private int[] location = new int[2];
	private ImageView mGoToTop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("使用指南");
		setBaseContentView(R.layout.activity_personal_guide);
		scrollView=(ElasticScrollView) findViewById(R.id.scroll_view);
		findViewById(R.id.txt_guide_rob_pallets).setOnClickListener(this);
		findViewById(R.id.txt_my_quote).setOnClickListener(this);
		findViewById(R.id.txt_cancel_transaction).setOnClickListener(this);
		findViewById(R.id.txt_guide_complain).setOnClickListener(this);
		findViewById(R.id.txt_guide_penalty).setOnClickListener(this);
		findViewById(R.id.txt_guide_info_modify).setOnClickListener(this);
		
		mTxtTitle1=(TextView) findViewById(R.id.txt_title1);
		mTxtTitle2=(TextView) findViewById(R.id.txt_title2);
		mTxtTitle3=(TextView) findViewById(R.id.txt_title3);
		mTxtTitle4=(TextView) findViewById(R.id.txt_title4);
		mTxtTitle5=(TextView) findViewById(R.id.txt_title5);
		mTxtTitle6=(TextView) findViewById(R.id.txt_title6);
		
		mGoToTop=(ImageView) findViewById(R.id.img_back_to_top);
		mGoToTop.setOnClickListener(this);
		final int y=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 300, getResources().getDisplayMetrics());
		scrollView.setOnScrollListener(new ElasticScrollView.OnScrollListener() {
			
			@Override
			public void onScroll(int scrollY) {
				if(scrollY>y){
					mGoToTop.setVisibility(View.VISIBLE);
				}else{
					mGoToTop.setVisibility(View.INVISIBLE);
				}
			}
		});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_guide_rob_pallets://抢货盘
			scrollTo(mTxtTitle1);
			break;
		case R.id.txt_my_quote://我的报价
			scrollTo(mTxtTitle2);
			break;
		case R.id.txt_cancel_transaction://取消交易
			scrollTo(mTxtTitle3);
			break;
		case R.id.txt_guide_complain://违规投诉
			scrollTo(mTxtTitle4);
			break;
		case R.id.txt_guide_penalty://违规处罚
			scrollTo(mTxtTitle5);
			break;
		case R.id.txt_guide_info_modify://信息修改
			scrollTo(mTxtTitle6);
			//scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			break;
		case R.id.img_back_to_top://回到顶部
			scrollView.fullScroll(ScrollView.FOCUS_UP);
			break;
		default:
			break;
		}
	}
	/**
	 * 滚动到指定位置
	 */
	private void scrollTo(final TextView view){
		scrollView.post(new Runnable() {
			
			@Override
			public void run() {
				view.getLocationOnScreen(location);
				@SuppressWarnings("deprecation")
				int offset = location[1] - scrollView.getMeasuredHeight()+getWindowManager().getDefaultDisplay().getHeight()/2;
				if (offset < 0) {
					offset = 0;
				}
				scrollView.smoothScrollTo(0, offset);
			}
		});
	}
}
