package com.ateam.shippingcity.utils;

import android.content.Context;

public class SceenUtils {
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	public static float px2dipFloat(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (float) (pxValue / scale + 0.5f);
	}
}
