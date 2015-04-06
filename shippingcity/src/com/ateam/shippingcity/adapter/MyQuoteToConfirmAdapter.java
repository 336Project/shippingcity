package com.ateam.shippingcity.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.model.MyQuoteToConfirm;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.utils.MyToast;

/**
 * 我的报价-待正确报价适配器
 * @version 
 * @create_date 2015年3月28日12:11:39
 */
public class MyQuoteToConfirmAdapter extends HBaseAdapter<MyQuoteToConfirm>{

	private Context c;
	public MyQuoteToConfirmAdapter(Context c, List<MyQuoteToConfirm> datas) {
		super(c, datas);
		this.c=c;
	}
	@Override
	public void convert(ViewHolder holder, MyQuoteToConfirm bean) {
		// TODO Auto-generated method stub
//		((TextView)holder.getView(R.id.tv_boxType)).setText(bean.getBoxType().toString());
//		((TextView)holder.getView(R.id.tv_placeBegin)).setText(bean.getPlaceBegin().toString());
//		((TextView)holder.getView(R.id.tv_placeEnd)).setText(bean.getPlaceEnd().toString());
//		((TextView)holder.getView(R.id.tv_transportType)).setText(bean.getTransportType().toString());
		((TextView)holder.getView(R.id.tv_initiation)).setText(bean.getInitiation());
		((TextView)holder.getView(R.id.tv_destination)).setText(bean.getDestination());
		String shipping_type = bean.getShipping_type();
		String shipment_type = bean.getShipment_type();
		if(shipping_type.equals("1")){
			((TextView)holder.getView(R.id.tv_shipping)).setText("海运");
			if(shipment_type.equals("1")){
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_zhengxiang_great_icon);
			}else if(shipment_type.equals("2")){
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_of_pinxiang_great_icon);
			}
			else{
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_san_groceries_great_icon);
			}
		}
		else if(shipping_type.equals("2")){
			((TextView)holder.getView(R.id.tv_shipping)).setText("空运");
			((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_air_transport_great_icon);
		}
		else{
			((TextView)holder.getView(R.id.tv_shipping)).setText("陆运");
			if(shipment_type.equals("1")){
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_zhengxiang_great_icon);
			}else if(shipment_type.equals("2")){
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_of_pinxiang_great_icon);
			}
			else{
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_san_groceries_great_icon);
			}
		}
		((TextView)holder.getView(R.id.tv_startime)).setText(bean.getStartime());
		((TextView)holder.getView(R.id.tv_endtime)).setText(bean.getEndtime());
		List<String> boxtype = bean.getBoxtype();
		List<String> number = bean.getNumber();
		StringBuffer describe=new StringBuffer();
		for (int i = 0; i < boxtype.size(); i++) {
			if(!boxtype.get(i).equals("")){
				describe.append(boxtype.get(i)+"*");
			}
			if(!number.get(i).equals("")){
				describe.append(number.get(i));
			}
			if(i<boxtype.size()-1){
				describe.append("、");
			}
		}
		if(!bean.getPackages().equals("0")){
			describe.append("件数："+bean.getPackages());
		}
		((TextView)holder.getView(R.id.tv_palletDescribe)).setText(describe.toString());
		
//		((TextView)holder.getView(R.id.tv_transportTimeEnd)).setText(bean.getTransportTimeEnd().toString());
//		((TextView)holder.getView(R.id.tv_palletDescribe)).setText(bean.getPalletDescribe().toString());
	}
	@Override
	public int getResId() {
		return R.layout.item_my_quote_to_confirm;
	}
}
