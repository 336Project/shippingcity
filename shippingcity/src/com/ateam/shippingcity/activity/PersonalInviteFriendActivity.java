package com.ateam.shippingcity.activity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.sina.weibo.SinaWeibo.ShareParams;

import com.ateam.shippingcity.R;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午11:42:25
 * @TODO 邀请好友
 */
public class PersonalInviteFriendActivity extends HBaseActivity implements OnClickListener{
	private EditText mEditPhoneNumber;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("邀请好友");
		setBaseContentView(R.layout.activity_personal_invite_friend);
		init();
	}

	private void init() {
		findViewById(R.id.iv_pick_phone_number).setOnClickListener(this);
		mEditPhoneNumber=(EditText) findViewById(R.id.et_phone_number);
		findViewById(R.id.share_sinaweibo).setOnClickListener(this);
		ShareSDK.initSDK(this);
		handler=new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				showMsg(PersonalInviteFriendActivity.this, "分享成功!");
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_pick_phone_number:
			pickPhoneNumber();
			break;
		case R.id.share_sinaweibo:
			shareSinweibo();
			break;

		default:
			break;
		}
	}
	
	private void shareSinweibo(){
		
		ShareParams sp = new ShareParams();
		sp.setText("测试分享的文本");
		//sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
		Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
		// 设置分享事件回调
		weibo.setPlatformActionListener(new PlatformActionListener() {
			
			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				System.out.println("onError-----"+arg2.getMessage());
				handler.sendEmptyMessage(0);
			}
			
			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> res) {
				System.out.println("onComplete");
				handler.sendEmptyMessage(0);
				/*String id,name,description,profile_image_url;
			    id=res.get("id").toString();//ID
			    name=res.get("name").toString();//用户名
			    description=res.get("description").toString();//描述
			    profile_image_url=res.get("profile_image_url").toString();//头像链接
			    String str="ID: "+id+";\n"+
			               "用户名： "+name+";\n"+
			             "描述："+description+";\n"+
			             "用户头像地址："+profile_image_url;
			    System.out.println("用户资料: "+str);*/
			}
			
			@Override
			public void onCancel(Platform arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println("onCancel");
			}
		});
		// 执行图文分享
		weibo.share(sp);
	}
	/**
	 * 打开通讯录，获取电话号码
	 */
	private void pickPhoneNumber() {
		Intent intent=new Intent(Intent.ACTION_PICK);
		intent.setData(ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, 1000);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_OK){
			if(requestCode==1000){//获取联系人号码
				if(data!=null){
					Uri uri=data.getData();
					CursorLoader loader=new CursorLoader(this, uri, null, null, null, null);
					Cursor cursor=loader.loadInBackground();
					if (cursor.moveToFirst()) {  
						String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));  
						Cursor phones = getContentResolver().query(  
	                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  
	                            null,  
	                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID  
	                                    + "=" + contactId, null, null);
						String number="";
						if(phones.moveToFirst()){
							number=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						}
						phones.close();
						mEditPhoneNumber.setText(number);
					}
					cursor.close();
				}
			}
		}
	}
}
