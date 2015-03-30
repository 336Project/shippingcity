package com.ateam.shippingcity.adapter;

import java.util.List;
import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.TextViewPair;

import android.content.Context;
/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午4:01:33
 * @TODO 我的积分适配器
 */
public class PersonalMyIntegralAdapter extends HBaseAdapter<Map<String, String>> {

	public PersonalMyIntegralAdapter(Context c, List<Map<String, String>> datas) {
		super(c, datas);
	}

	@Override
	public void convert(ViewHolder holder, Map<String, String> bean) {
		TextViewPair pair=holder.getView(R.id.txt_integral);
		pair.setNameText(bean.get("title"));
		pair.setValueText(bean.get("integral"));
	}

	@Override
	public int getResId() {
		return R.layout.adapter_personal_my_integral;
	}

}
