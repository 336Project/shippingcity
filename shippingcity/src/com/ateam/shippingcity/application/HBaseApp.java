package com.ateam.shippingcity.application;

import java.io.File;

import com.ateam.shippingcity.constant.ConstantUtil;
import com.ateam.shippingcity.model.User;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;

import android.app.Application;
import android.graphics.Bitmap.Config;
import android.os.Environment;

public class HBaseApp extends Application {
	private String userssid;//登录令牌
	private User user;
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader();
	}

	private void initImageLoader() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
//		.showImageOnFail(R.drawable.ic_launcher) // 加载图片出现问题，会显示该图片
//		.showImageForEmptyUri(R.drawable.ic_launcher)//url为空的时候显示的图片
//		.showImageOnLoading(R.drawable.ic_launcher)//图片加载过程中显示的图片
		.bitmapConfig(Config.RGB_565)
		.cacheOnDisk(true)//开启硬盘缓存
		.cacheInMemory(true)//内存缓存
		.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY)
				.defaultDisplayImageOptions(options)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileCount(100)
				.diskCacheSize(10*1024*1024)//缓存容量
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCache(new UnlimitedDiscCache(new File(Environment.getExternalStorageDirectory() + ConstantUtil.IMAGE_CACHE)))
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
		L.writeLogs(false);//关闭日志
		ImageLoader.getInstance().init(config);
	}

	public String getUserssid() {
		
		return userssid;
	}

	public void setUserssid(String userssid) {
		this.userssid = userssid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
