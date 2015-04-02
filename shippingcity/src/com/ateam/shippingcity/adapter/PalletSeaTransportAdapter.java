package com.ateam.shippingcity.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.PalletSeaWholeOfferActivity;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.utils.MyToast;

/**
 * 海运列表适配器
 * @version 
 * @create_date 2015-3-28上午9:52:09
 */
public class PalletSeaTransportAdapter extends HBaseAdapter<PalletTransport>{

	private Context c;
	public PalletSeaTransportAdapter(Context c, List<PalletTransport> datas) {
		super(c, datas);
		this.c=c;
	}

	@Override
	public void convert(ViewHolder holder, PalletTransport bean) {
		// TODO Auto-generated method stub
		((TextView)holder.getView(R.id.tv_remainTime)).setText(bean.getRemainTime().toString());
		((TextView)holder.getView(R.id.tv_boxType)).setText(bean.getBoxType().toString());
		((TextView)holder.getView(R.id.tv_placeBegin)).setText(bean.getPlaceBegin().toString());
		((TextView)holder.getView(R.id.tv_placeEnd)).setText(bean.getPlaceEnd().toString());
		((TextView)holder.getView(R.id.tv_transportType)).setText(bean.getTransportType().toString());
		((TextView)holder.getView(R.id.tv_transportTimeBegin)).setText(bean.getTransportTimeBegin().toString());
		((TextView)holder.getView(R.id.tv_transportTimeEnd)).setText(bean.getTransportTimeEnd().toString());
		((TextView)holder.getView(R.id.tv_palletDescribe)).setText(bean.getPalletDescribe().toString());
		((ImageView)holder.getView(R.id.iv_offer)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(c,PalletSeaWholeOfferActivity.class);
				c.startActivity(intent);
				MyToast.showShort(c, "您已点击报价");
			}
		});
	}

	@Override
	public int getResId() {
		// TODO Auto-generated method stub
		return R.layout.item_pallet_sea_transport;
	}

}
