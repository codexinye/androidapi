package com.xinye.support.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * YouKu Menu
 * @author xinye
 *
 */
public class YouKuMenuActivity extends Activity implements OnClickListener{
	
	private Button mChannelButton = null;
	private Button mCloseButton = null;
	private Button mDownloadButton = null;
	private Button mHomeButton = null;
	private Button mSearchButton = null;
	private Button mUploadButton = null;
	private Button mFavoriteButton = null;
	private Button mMoreButton = null;
	private Button mMenuButton = null;
	private LinearLayout mMenuLinearLayout = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_youku);
		
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		init();
	}
	private void init() {
		mCloseButton = (Button) findViewById(R.id.closeButton);
		mChannelButton = (Button) findViewById(R.id.channelButton);
		mFavoriteButton = (Button) findViewById(R.id.favoriteButton);
		mHomeButton = (Button) findViewById(R.id.homeButton);
		mMenuButton = (Button) findViewById(R.id.menuButton);
		mDownloadButton = (Button) findViewById(R.id.downloadButton);
		mUploadButton = (Button) findViewById(R.id.uploadButton);
		mSearchButton = (Button) findViewById(R.id.searchButton);
		mMoreButton = (Button) findViewById(R.id.moreButton);
		mMenuLinearLayout = (LinearLayout) findViewById(R.id.menuListLinearLayout);

		mCloseButton.setOnClickListener(YouKuMenuActivity.this);
		mChannelButton.setOnClickListener(YouKuMenuActivity.this);
		mFavoriteButton.setOnClickListener(YouKuMenuActivity.this);
		mHomeButton.setOnClickListener(YouKuMenuActivity.this);
		mMenuButton.setOnClickListener(YouKuMenuActivity.this);
		mDownloadButton.setOnClickListener(YouKuMenuActivity.this);
		mUploadButton.setOnClickListener(YouKuMenuActivity.this);
		mSearchButton.setOnClickListener(YouKuMenuActivity.this);
		mMoreButton.setOnClickListener(YouKuMenuActivity.this);
		
	}
	private Animation menuButtonIn(){
		TranslateAnimation translate = new TranslateAnimation(
					TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 
					TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 
					TranslateAnimation.RELATIVE_TO_SELF, 1.0f, 
					TranslateAnimation.RELATIVE_TO_SELF, 0.0f);
		translate.setDuration(500);
		translate.setStartOffset(0);
		translate.setFillAfter(true);
		translate.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				mMenuLinearLayout.startAnimation(menuListOut());	
			}
			@Override
			public void onAnimationRepeat(Animation animation) {	
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				mMenuButton.setVisibility(View.VISIBLE);
			}
		});
		return translate;
	}
	private Animation menuButtonOut(){
		TranslateAnimation translate = new TranslateAnimation(
			TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 
			TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 
			TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 
			TranslateAnimation.RELATIVE_TO_SELF, 1.0f);
			translate.setDuration(500);
			translate.setStartOffset(0);
			translate.setFillAfter(true);
			translate.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
					mMenuLinearLayout.startAnimation(menuListIn());
				}
				@Override
				public void onAnimationRepeat(Animation animation) {	
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					mMenuButton.setVisibility(View.GONE);
				}
			});
			return translate;
	}
	private Animation menuListIn(){
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation translate = new TranslateAnimation(
				TranslateAnimation.RELATIVE_TO_SELF, 0, 
				TranslateAnimation.RELATIVE_TO_SELF, 0, 
				TranslateAnimation.RELATIVE_TO_SELF, 1.0f, 
				TranslateAnimation.RELATIVE_TO_SELF, 0.5f);
		
		translate.setDuration(1000);
		
		/*ScaleAnimation scale = new ScaleAnimation(1, 0,1,0,
				ScaleAnimation.RELATIVE_TO_SELF,0.5f , 
				ScaleAnimation.RELATIVE_TO_SELF, 0.0f);
		scale.setDuration(1000);*/
		
//		set.addAnimation(translate);
//		set.addAnimation(scale);
		set.addAnimation(AnimationUtils.loadAnimation(this, R.anim.menu_list_in));
		set.setFillAfter(true);
		/*set.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {	
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				mMenuLinearLayout.setVisibility(View.VISIBLE);
			}
		});*/
		return set;
	}
	private Animation menuListOut(){
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation translate = new TranslateAnimation(
					TranslateAnimation.RELATIVE_TO_SELF, 0, 
					TranslateAnimation.RELATIVE_TO_SELF, 0, 
					TranslateAnimation.RELATIVE_TO_SELF, 0.5f, 
					TranslateAnimation.RELATIVE_TO_SELF, 1.0f);

		translate.setDuration(1000);
		
		/*ScaleAnimation scale = new ScaleAnimation(0, 1,0,1, 
				ScaleAnimation.RELATIVE_TO_SELF,0.5f , 
				ScaleAnimation.RELATIVE_TO_SELF, 0.0f);
		scale.setDuration(1000);
		*/
//		set.addAnimation(translate);
//		set.addAnimation(scale);
		set.setFillAfter(true);
		set.addAnimation(AnimationUtils.loadAnimation(this, R.anim.menu_list_out));
		/*set.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {	
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				mMenuLinearLayout.setVisibility(View.GONE);
			}
		});*/
		return set;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU){
			
			if(mMenuButton.getVisibility() == View.VISIBLE){
				mMenuButton.startAnimation(menuButtonOut());
			}else{
				mMenuButton.startAnimation(menuButtonIn());
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
			case R.id.closeButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.channelButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.downloadButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.uploadButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.favoriteButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.homeButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.moreButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.searchButton:{
				mMenuButton.startAnimation(menuButtonIn());
			}break;
			case R.id.menuButton:{
				mMenuButton.startAnimation(menuButtonOut());
			}break;
		}
	}
}
