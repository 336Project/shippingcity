package com.ateam.shippingcity;


import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.utils.AppManager;
import com.ateam.shippingcity.widget.banner.AutoScrollViewPager;
import com.ateam.shippingcity.widget.viewpagerindicator.CirclePageIndicator;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends HBaseActivity {
	private long currTime=0;
	private CirclePageIndicator mIndicator;
	private AutoScrollViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("航运城");
		getRightIcon().setVisibility(View.INVISIBLE);
		setBaseContentView(R.layout.activity_main);
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
	}
	
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
			ImageView view=new ImageView(MainActivity.this);
			view.setBackgroundResource(R.drawable.ic_launcher);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			container.addView(view,params);
			return view;
		}
	}
	@Override
	public void onBackPressed() {
		if(System.currentTimeMillis()-currTime>2000){
			currTime=System.currentTimeMillis();
			showMsg(MainActivity.this, "再按一次退出程序");
		}else{
			AppManager.getInstance().ExitApp();
		}
	}
	
}
