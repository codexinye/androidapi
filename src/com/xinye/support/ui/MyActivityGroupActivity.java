package com.xinye.support.ui;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * ActivityGroup
 * @author Administrator
 *
 */
public class MyActivityGroupActivity extends ActivityGroup implements OnClickListener{
	private LinearLayout mContainerView = null;
	private ImageView mImageView01 = null;
	private ImageView mImageView02 = null;
	private ImageView mImageView03 = null;
	private LocalActivityManager mLocalActivityManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_activity_group);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		mContainerView = (LinearLayout) findViewById(R.id.containerBody);
		mImageView01 = (ImageView) findViewById(R.id.btnModule1);
		mImageView02 = (ImageView) findViewById(R.id.btnModule2);
		mImageView03 = (ImageView) findViewById(R.id.btnModule3);

		mLocalActivityManager = getLocalActivityManager();
		
		mImageView01.setOnClickListener(MyActivityGroupActivity.this);
		mImageView02.setOnClickListener(MyActivityGroupActivity.this);
		mImageView03.setOnClickListener(MyActivityGroupActivity.this);
		
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btnModule1:{
				mContainerView.removeAllViews();
				Intent intent = new Intent(MyActivityGroupActivity.this,AsyncLoadActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				Window window = mLocalActivityManager.startActivity("module1", intent);
				mContainerView.addView(window.getDecorView());
			} break;
			case R.id.btnModule2:{
				mContainerView.removeAllViews();
				Intent intent = new Intent(MyActivityGroupActivity.this,GalleryTestActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Window window = mLocalActivityManager.startActivity("module2", intent);
				mContainerView.addView(window.getDecorView());
			} break;
			case R.id.btnModule3:{
				mContainerView.removeAllViews();
				Intent intent = new Intent(MyActivityGroupActivity.this,PictureModifyActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Window window = mLocalActivityManager.startActivity("module3", intent);
				mContainerView.addView(window.getDecorView());
			} break;
		}
	}
}
