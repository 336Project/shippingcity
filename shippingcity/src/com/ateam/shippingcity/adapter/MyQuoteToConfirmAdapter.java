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
		((TextView)holder.getView(R.id.tv_initiation)).setText(bean.getInitiation());
		((TextView)holder.getView(R.id.tv_destination)).setText(bean.getDestination());
		String shipping_type = bean.getShipping_type();
		String shipment_type = bean.getShipment_type();
		if(shipping_type.equals("1")){
			((TextView)holder.getView(R.id.tv_shipping)).setText("海运");
			if(shipment_type.equals("1")){
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_zhengxiang_great_icon);
			}else if(shipment_type.equals("2")){
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_san_groceries_great_icon);
			}
			else{
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_of_pinxiang_great_icon);
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
				((ImageView)holder.getView(R.id.iv_shipment)).setImageResource(R.drawable.list_san_groceries_great_icon);
			}
		}
		((TextView)holder.getView(R.id.tv_startime)).setText(bean.getStartime());
		((TextView)holder.getView(R.id.tv_endtime)).setText(bean.getEndtime());
		List<String> boxtype = bean.getBoxtype();
		List<String> number = bean.getNumber();
		StringBuffer description=new StringBuffer();
		if(shipping_type.equals("1")&&shipment_type.equals("1")){
			if(boxtype.size()>0){
				for (int i = 0; i < boxtype.size(); i++) {
					description.append(boxtype.get(i)+"*");
					description.append(number.get(i));
					if(i<boxtype.size()-1){
						description.append("、");
					}
				}
			}
		}
		else{
			description.append("件数:"+bean.getPackages()+";");
			description.append("毛重:"+bean.getWeight()+"kg;");
			description.append("体积:"+bean.getVolume()+"立方;");
			List<String> size = bean.getSize();
			if(size.size()>0){
				description.append("单件尺寸:");
				for (int i = 0; i < size.size(); i++) {
					String string = size.get(i);
					description.append(string);
					if(i<size.size()-1){
						description.append("x");
					}
				}
			}
		}
		((TextView)holder.getView(R.id.tv_palletDescribe)).setText(description.toString());
	}
	@Override
	public int getResId() {
		return R.layout.item_my_quote_to_confirm;
	}
}
