package com.ateam.shippingcity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.activity.PalletAndQuoteCommonActivity;
import com.ateam.shippingcity.activity.PersonalCenterActivity;
import com.ateam.shippingcity.utils.AppManager;
import com.ateam.shippingcity.widget.banner.AutoScrollViewPager;
import com.ateam.shippingcity.widget.viewpagerindicator.CirclePageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午1:59:21
 * @TODO 首页
 */
public class HomeActivity extends HBaseActivity implements OnClickListener{
	private long currTime=0;
	private CirclePageIndicator mIndicator;
	private AutoScrollViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("航运城");
		getRightIcon().setVisibility(View.INVISIBLE);
		getLeftIcon().setOnClickListener(this);
		setBaseContentView(R.layout.activity_home);
		initView();
	}
	
	/**
	 * 初始化
	 */
	private void initView() {
		mIndicator=(CirclePageIndicator) findViewById(R.id.indicator);
		mViewPager=(AutoScrollViewPager) findViewById(R.id.auto_view_page);
		mViewPager.setAdapter(new BannerPageAdapter());
		mIndicator.setSnap(true);
		mIndicator.setViewPager(mViewPager);
		mViewPager.startAutoScroll(3000);
		
		findViewById(R.id.layout_quote).setOnClickListener(this);
		findViewById(R.id.layout_pallet).setOnClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
		if(System.currentTimeMillis()-currTime>2000){
			currTime=System.currentTimeMillis();
			showMsg(HomeActivity.this, "再按一次退出程序");
		}else{
			AppManager.getInstance().ExitApp();
		}
	}
	/**
	 * 
	 * @author 李晓伟
	 * @Create_date 2015-3-27 上午9:28:37
	 * @Version 
	 * @TODO
	 */
	class BannerPageAdapter extends PagerAdapter{
		
		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((ImageView)object);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view=new ImageView(HomeActivity.this);
			view.setBackgroundResource(R.drawable.ic_launcher);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			container.addView(view,params);
			return view;
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_quote://我的报价
			Intent intent=new Intent(this, PalletAndQuoteCommonActivity.class);
			intent.putExtra(PalletAndQuoteCommonActivity.KEY_TYPE, PalletAndQuoteCommonActivity.TYPE_QUOTE);
			startActivity(intent);
			break;
		case R.id.layout_pallet://货盘区
			intent=new Intent(this, PalletAndQuoteCommonActivity.class);
			intent.putExtra(PalletAndQuoteCommonActivity.KEY_TYPE, PalletAndQuoteCommonActivity.TYPE_PALLET);
			startActivity(intent);
			break;
		case R.id.layout_call://一键客服
			
			break;
		case R.id.iv_left_icon:
			startActivity(new Intent(this, PersonalCenterActivity.class));
			break;

		default:
			break;
		}
	}
}
