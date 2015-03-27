package com.ateam.shippingcity.fragment;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.banner.AutoScrollViewPager;
import com.ateam.shippingcity.widget.viewpagerindicator.CirclePageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-3-27 上午9:29:17
 * @Version 
 * @TODO 首页
 */
public class HomeFragment extends Fragment{
	private CirclePageIndicator mIndicator;
	private AutoScrollViewPager mViewPager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_home, null);
		initView(view);
		return view;
	}
	/**
	 * 初始化
	 */
	private void initView(View view) {
		mIndicator=(CirclePageIndicator) view.findViewById(R.id.indicator);
		mViewPager=(AutoScrollViewPager) view.findViewById(R.id.auto_view_page);
		mViewPager.setAdapter(new BannerPageAdapter());
		mIndicator.setSnap(true);
		mIndicator.setViewPager(mViewPager);
		mViewPager.startAutoScroll(3000);
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
			ImageView view=new ImageView(getActivity());
			view.setBackgroundResource(R.drawable.ic_launcher);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			container.addView(view,params);
			return view;
		}
	}
}
