package com.ateam.shippingcity.utils;

import com.ateam.shippingcity.R;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PopupWindowUtil {
	private static PopupWindow pop_MyQuote;
	private static TextView tv_my_quote_pop_touch;

	public static void initPopup(Context mContext,int layoutId){
		View inflate = LayoutInflater.from(mContext).inflate(layoutId, null);
		inflate.findViewById(R.id.iv_close_myquote).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismissPopup();
			}
		});
		tv_my_quote_pop_touch = (TextView) inflate.findViewById(R.id.tv_my_quote_pop_touch);
		pop_MyQuote = new PopupWindow(inflate, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
//		pop_MyQuote.setBackgroundDrawable(ColorDrawable);
	}
	public static void showPopup(Activity mContext,int layoutId){
		View parent = LayoutInflater.from(mContext).inflate(layoutId, null);
		if(pop_MyQuote!=null){
			pop_MyQuote.showAtLocation(parent, Gravity.CENTER, 0, 0);
			if(tv_my_quote_pop_touch!=null){
				ObjectAnimator oa=ObjectAnimator.ofFloat(tv_my_quote_pop_touch, "alpha", 0.1f, 0.5f);
				oa.setDuration(500);
				oa.start();
			}
		}
	}
	public static void dismissPopup(){
		if(pop_MyQuote!=null&&pop_MyQuote.isShowing()){
			pop_MyQuote.dismiss();
		}
	}
	public static boolean getPopupIsShowing(){
		if(pop_MyQuote!=null) return pop_MyQuote.isShowing();
		else return false;
	}
}
