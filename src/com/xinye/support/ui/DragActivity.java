package com.xinye.support.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * 重点说明的是：
 * 	像ImageView、TextView这些控件要想实现拖动的话，
 * 	需要加上属性android:clickable="true"才可以
 * 
 * @author Administrator
 *
 */
public class DragActivity extends Activity implements OnTouchListener{
	private ImageView mDragImageView = null;
	private Button mDragButton = null;
	private int mScreenWidth = 0;
	private int mScreenHeight = 0;
	private int mLastX = 0;
	private int mLastY = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_drag);
		
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		init();
	}
	private void init() {
		mDragImageView = (ImageView) findViewById(R.id.dragImageView);
		mDragButton = (Button) findViewById(R.id.dragButton);
		
		mDragButton.setOnTouchListener(DragActivity.this);
		mDragImageView.setOnTouchListener(DragActivity.this);
		
		DisplayMetrics display = getResources().getDisplayMetrics();
		mScreenWidth = display.widthPixels;
		mScreenHeight = display.heightPixels - 50;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch(action){
			case MotionEvent.ACTION_DOWN:{
				mLastX = (int) event.getRawX();
				mLastY = (int) event.getRawY();
			}break;
			case MotionEvent.ACTION_MOVE:{
				int dx = (int) (event.getRawX() - mLastX);
				int dy = (int) (event.getRawY() - mLastY);
				
				int left = v.getLeft() + dx;
				int right = v.getRight() + dx;
				int top = v.getTop() + dy;
				int bottom = v.getBottom() + dy;
				
				if(left < 0){
					left = 0;
					right = left + v.getWidth();
				}
				if(right > mScreenWidth){
					right = mScreenWidth;
					left = right - v.getWidth();
				}
				if(top < 0){
					top = 0;
					bottom = top + v.getHeight();
				}
				if(bottom > mScreenHeight){
					bottom = mScreenHeight;
					top = bottom - v.getHeight();
				}
				
				v.layout(left, top, right, bottom);
				
				mLastX = (int) event.getRawX();
				mLastY = (int) event.getRawY();
			}break;
		}
		return false;
	}
}