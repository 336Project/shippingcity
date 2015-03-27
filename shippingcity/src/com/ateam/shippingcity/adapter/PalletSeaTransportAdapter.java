package com.ateam.shippingcity.adapter;

import java.util.List;

import android.content.Context;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.model.PalletSeaTransport;

public class PalletSeaTransportAdapter extends HBaseAdapter<PalletSeaTransport>{

	public PalletSeaTransportAdapter(Context c, List<PalletSeaTransport> datas) {
		super(c, datas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder holder, PalletSeaTransport bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getResId() {
		// TODO Auto-generated method stub
		return R.layout.item_pallet_sea_transport;
	}

}
