package com.ateam.shippingcity.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.model.MyQuoteToConfirm;
import com.ateam.shippingcity.model.MyQuoteToHistory;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.utils.MyToast;

/**
 * 我的报价-待正确报价适配器
 * @version 
 * @create_date 2015年3月28日12:11:39
 */
public class MyQuoteToHistoryAdapter extends HBaseAdapter<MyQuoteToHistory>{

	private Context c;
	public MyQuoteToHistoryAdapter(Context c, List<MyQuoteToHistory> datas) {
		super(c, datas);
		this.c=c;
	}

	@Override
	public void convert(ViewHolder holder, MyQuoteToHistory bean) {
		// TODO Auto-generated method stub
//		((TextView)holder.getView(R.id.tv_boxType)).setText(bean.getBoxType().toString());
//		((TextView)holder.getView(R.id.tv_placeBegin)).setText(bean.getPlaceBegin().toString());
//		((TextView)holder.getView(R.id.tv_placeEnd)).setText(bean.getPlaceEnd().toString());
//		((TextView)holder.getView(R.id.tv_transportType)).setText(bean.getTransportType().toString());
//		((TextView)holder.getView(R.id.tv_transportTimeBegin)).setText(bean.getTransportTimeBegin().toString());
//		((TextView)holder.getView(R.id.tv_transportTimeEnd)).setText(bean.getTransportTimeEnd().toString());
//		((TextView)holder.getView(R.id.tv_palletDescribe)).setText(bean.getPalletDescribe().toString());
//		((ImageView)holder.getView(R.id.iv_winType)).setImageResource(R.drawable.ic_launcher);
	}

	@Override
	public int getResId() {
		return R.layout.item_my_quote_to_history;
	}

}
