package com.ateam.shippingcity.activity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.constant.ConstantUtil;
import com.ateam.shippingcity.utils.FileUtil;

import android.content.Intent;
import android.content.res.AssetManager;
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
	public static final int SUCCESS=0;
	public static final int CANCEL=1;
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
		findViewById(R.id.share_wechat).setOnClickListener(this);
		findViewById(R.id.share_wechat_moments).setOnClickListener(this);
		findViewById(R.id.share_qzone).setOnClickListener(this);
		ShareSDK.initSDK(this);
		handler=new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case SUCCESS:
					showMsg(PersonalInviteFriendActivity.this, "分享成功");
					break;
				case CANCEL:
					showMsg(PersonalInviteFriendActivity.this, "取消分享");
					break;
				default:
					break;
				}
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
		case R.id.share_wechat:
			shareWechat();
			break;
		case R.id.share_wechat_moments:
			shareWechatMoments();
			break;
		case R.id.share_qzone:
			shareQZone();
			break;
		default:
			break;
		}
	}
	
	class ShareListener implements PlatformActionListener{

		@Override
		public void onError(Platform arg0, int arg1, Throwable arg2) {
			System.out.println("onError-----"+arg2.getMessage());
			handler.sendEmptyMessage(SUCCESS);
		}
		
		@Override
		public void onComplete(Platform arg0, int arg1, HashMap<String, Object> res) {
			handler.sendEmptyMessage(SUCCESS);
		}
		
		@Override
		public void onCancel(Platform arg0, int arg1) {
			handler.sendEmptyMessage(CANCEL);
		}
		
	}
	/**
	 * 
	 * 2015-4-7 下午8:46:48
	 * @TODO 新浪分享
	 */
	private void shareSinweibo(){
		/*ShareParams sp = new ShareParams();
		sp.setText("测试分享的文本，测试地址:http://www.shippingcity.com @划虎烂1990");
		//sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
		//sp.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
		Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
		// 设置分享事件回调
		weibo.setPlatformActionListener(new ShareListener());
		// 执行图文分享
		weibo.share(sp);*/
		showShare(SinaWeibo.NAME);
	}
	/**
	 * 
	 * 2015-4-7 下午9:15:19
	 * @TODO 分享微信好友
	 */
	private void shareWechat(){
		/*ShareParams sp = new ShareParams();
		sp.setTitle("测试标题");
		sp.setText("测试分享的文本，测试地址:http://mob.com @划虎烂1990");
		sp.setShareType(Platform.SHARE_TEXT);//分享文本
		//sp.setImageData(arg0);//分享图片
		Platform wechat=ShareSDK.getPlatform(Wechat.NAME);
		wechat.setPlatformActionListener(new ShareListener());
		wechat.share(sp);*/
		showShare(Wechat.NAME);
	}
	/**
	 * 
	 * 2015-4-7 下午9:15:19
	 * @TODO 分享微信朋友圈
	 */
	private void shareWechatMoments(){
		/*ShareParams sp = new ShareParams();
		sp.setTitle("测试标题");
		sp.setText("测试分享的文本，测试地址:http://www.shippingcity.com @划虎烂1990");
		sp.setShareType(Platform.SHARE_TEXT);//分享文本
		//sp.setImageData(arg0);//分享图片
		Platform moments=ShareSDK.getPlatform(WechatMoments.NAME);
		moments.setPlatformActionListener(new ShareListener());
		moments.share(sp);*/
		showShare(WechatMoments.NAME);
	}
	/**
	 * 
	 * 2015-4-7 下午9:48:57
	 * @TODO 分享qq空间
	 */
	private void shareQZone(){
		/*ShareParams sp = new ShareParams();
		sp.setTitle("测试分享的标题");
		sp.setTitleUrl("http://www.shippingcity.com"); // 标题的超链接
		sp.setText("测试分享的文本");
//		sp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
		sp.setSite("航运城");
		sp.setSiteUrl("http://www.shippingcity.com");
		Platform qzone = ShareSDK.getPlatform (QZone.NAME);
		qzone. setPlatformActionListener (new ShareListener()); // 设置分享事件回调
		// 执行图文分享
		qzone.share(sp);*/
		showShare(QZone.NAME);
	}
	
	private void showShare(String platform) {
		OnekeyShare oks = new OnekeyShare();
		oks.setTitle("航运城");
		oks.setTitleUrl("http://www.shippingcity.com");
		oks.setText("运价大搜索，尽在航运城 ，详见官网：http://www.shippingcity.com");
		oks.setImagePath(getFile().getPath());
		oks.setComment("航运城——运价大搜索，尽在航运城");
		oks.setUrl("http://www.shippingcity.com");
		oks.setSite("航运城");
		oks.setSiteUrl("http://www.shippingcity.com");
		oks.setSilent(false);
		oks.setShareFromQQAuthSupport(false);
		oks.setTheme(OnekeyShareTheme.CLASSIC);
		oks.setPlatform(platform);
		// 令编辑页面显示为Dialog模式
		oks.setDialogMode();

		// 在自动授权时可以禁用SSO方式
		oks.disableSSOWhenAuthorize();
		// 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
		//oks.setCallback(new ShareListener());
		oks.setEditPageBackground(getLayoutContent());
		oks.show(this);
	}
	/**
	 * 
	 * 2015-4-10 下午11:27:35
	 * @return
	 * @TODO 获取分享图片
	 */
	private File getFile(){
		File file=new File(FileUtil.getInstance().getFilePath(ConstantUtil.IMAGE_DIR, "logo.png"));
		if(!file.exists()){
			try {
				AssetManager manager=getAssets();
				file=FileUtil.getInstance().write2SDFromInput(ConstantUtil.IMAGE_DIR, "logo.png", manager.open("logo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
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
