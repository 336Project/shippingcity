package com.ateam.shippingcity.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.PalletAirOfferActivity;
import com.ateam.shippingcity.activity.PalletLordOfferActivity;
import com.ateam.shippingcity.activity.PalletSeaDiffOfferActivity;
import com.ateam.shippingcity.activity.PalletSeaSpellOfferActivity;
import com.ateam.shippingcity.activity.PalletSeaWholeOfferActivity;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.utils.SysUtil;

/**
 * 海运列表适配器
 * @version 
 * @create_date 2015-3-28上午9:52:09
 */
public class PalletTransportAdapter extends HBaseAdapter<PalletTransport>{

	private Context c;
	public PalletTransportAdapter(Context c, List<PalletTransport> datas) {
		super(c, datas);
		this.c=c;
	}

	@Override
	public void convert(ViewHolder holder, final PalletTransport bean) {
		// TODO Auto-generated method stub
		((TextView)holder.getView(R.id.tv_remainTime)).setText(SysUtil.getRemainTime(bean.deadlinetime));
		TextView mTvBoxType = ((TextView)holder.getView(R.id.tv_boxType));
		if(bean.shipment_type.equals("1")){
			mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_zhengxiang_great_icon));
			StringBuffer Describe=new StringBuffer();
			if(bean.boxtype.size()==bean.number.size()){
				for (int i = 0; i < bean.boxtype.size(); i++) {
					Describe.append(bean.boxtype.get(i)+"*"+bean.number.get(i));
					if((bean.number.size()-i)>=2){
						Describe.append("、");
					}
				}
			}
			((TextView)holder.getView(R.id.tv_palletDescribe)).setText(Describe.toString());
		}else if(bean.shipment_type.equals("2")){
			mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_of_pinxiang_great_icon));
			StringBuffer Describe=new StringBuffer();
			Describe.append("件数:"+bean.packages+";"+"毛重:"+bean.weight+";"+"体积:"+bean.volume+";");
			if(bean.size.size()==3){
				Describe.append("长:"+bean.size.get(0)+";"+"宽:"+bean.size.get(1)+";"+"高:"+bean.size.get(2)+";");
			}
			((TextView)holder.getView(R.id.tv_palletDescribe)).setText(Describe);
		}else{
			mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_san_groceries_great_icon));
			StringBuffer Describe=new StringBuffer();
			Describe.append("件数:"+bean.packages+";"+"毛重:"+bean.weight+";"+"体积:"+bean.volume+";");
			if(bean.size.size()==3){
				Describe.append("长:"+bean.size.get(0)+";"+"宽:"+bean.size.get(1)+";"+"高:"+bean.size.get(2)+";");
			}
			((TextView)holder.getView(R.id.tv_palletDescribe)).setText(Describe);
		}
		((TextView)holder.getView(R.id.tv_placeBegin)).setText(bean.initiation);
		((TextView)holder.getView(R.id.tv_placeEnd)).setText(bean.destination);
		if(bean.shipping_type.toString().equals("1")){
			((TextView)holder.getView(R.id.tv_transportType)).setText("海运");
		}else if(bean.shipping_type.toString().equals("2")){
			((TextView)holder.getView(R.id.tv_transportType)).setText("空运");
		}else{
			((TextView)holder.getView(R.id.tv_transportType)).setText("陆运");
		}
		((TextView)holder.getView(R.id.tv_transportTimeBegin)).setText(bean.startime);
		((TextView)holder.getView(R.id.tv_transportTimeEnd)).setText(bean.endtime);
		((ImageView)holder.getView(R.id.iv_offer)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				toOfferPage(bean);
			}
		});
	}

	@Override
	public int getResId() {
		// TODO Auto-generated method stub
		return R.layout.item_pallet_sea_transport;
	}
	
	/**
	 * 点击报价后，跳转到相应的界面
	 * @param bean
	 */
	private void toOfferPage(PalletTransport bean){
		Intent intent =new Intent();
		if(bean.shipping_type.toString().equals("1")){
			if(bean.shipment_type.equals("1")){
				intent.setClass(c, PalletSeaWholeOfferActivity.class);
			}else if(bean.shipment_type.equals("2")){
				intent.setClass(c, PalletSeaSpellOfferActivity.class);
			}else{
				intent.setClass(c, PalletSeaDiffOfferActivity.class);
			}
		}else if(bean.shipping_type.toString().equals("2")){
			intent.setClass(c, PalletAirOfferActivity.class);
		}else{
			if(bean.shipment_type.equals("1")){
				intent.setClass(c, PalletLordOfferActivity.class);
			}else if(bean.shipment_type.equals("2")){
				intent.setClass(c, PalletAirOfferActivity.class);
			}
		}
		intent.putExtra("palletTransport", bean);
		c.startActivity(intent);
	}

}
