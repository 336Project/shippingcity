package com.ateam.shippingcity.utils;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;
import com.nineoldandroids.animation.ObjectAnimator;

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
	/**
	 * 模板
	 * @param mContext
	 * @param layoutId
	 */
	public static void initPopup(Context mContext,int layoutId){
		View inflate = LayoutInflater.from(mContext).inflate(layoutId, null);
		inflate.findViewById(R.id.iv_close_myquote).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismissPopup();
			}
		});
		tv_my_quote_pop_touch = (TextView) inflate.findViewById(R.id.tv_my_quote_pop_touch);
		pop_MyQuote = new PopupWindow(inflate, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
	}
	/**
	 * 普通报价
	 * @param mContext
	 * @param layoutId
	 * @param tv_List
	 * @param content_List
	 */
	public static void initPopup(Context mContext,int layoutId,ArrayList<Integer> tv_List,ArrayList<String> content_List){
		View inflate = LayoutInflater.from(mContext).inflate(layoutId, null);
		inflate.findViewById(R.id.iv_close_myquote).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismissPopup();
			}
		});
		for (int i = 0; i < tv_List.size(); i++) {
			TextView tv_item = (TextView) inflate.findViewById(tv_List.get(i));
			tv_item.setText(content_List.get(i));
		}
		tv_my_quote_pop_touch = (TextView) inflate.findViewById(R.id.tv_my_quote_pop_touch);
		pop_MyQuote = new PopupWindow(inflate, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
	}
	/**
	 * 详情页
	 * @param mContext
	 * @param layoutId
	 * @param tv_List
	 * @param content_List
	 * @param type
	 * @param num
	 * @param price
	 */
	public static void initPopup(Context mContext,int layoutId,ArrayList<Integer> tv_List,ArrayList<String> content_List,List<String> type,List<String> num,List<String> price ){
		View inflate = LayoutInflater.from(mContext).inflate(layoutId, null);
		inflate.findViewById(R.id.iv_close_myquote).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismissPopup();
			}
		});
		for (int i = 0; i < tv_List.size(); i++) {
			
			if(i==tv_List.size()-1){
				LinearLayout ll_quotation = (LinearLayout) inflate.findViewById(tv_List.get(i));
				if(type.size()>0&&num.size()>0&&price.size()>0){
					for (int j = 0; j < price.size(); j++) {
						TextView textView = new TextView(mContext);
						textView.setText(num.get(j)+"*"+type.get(j)+","+"$"+price.get(j));
						ll_quotation.addView(textView);
					}
				}
			}
			else{
				TextView tv_item = (TextView) inflate.findViewById(tv_List.get(i));
				tv_item.setText(content_List.get(i));
			}
		}
		tv_my_quote_pop_touch = (TextView) inflate.findViewById(R.id.tv_my_quote_pop_touch);
		pop_MyQuote = new PopupWindow(inflate, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
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
