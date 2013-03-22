package com.xinye.support.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.TextView;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * 手势
 * @author Administrator
 *
 */
public class GestureDetectorActivity extends Activity {
	private TextView mTextView = null;
	private MyGestureDetectorListener mGestureDetectorListener;
	private GestureDetector mGestureDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_gesture_detector);

		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		mTextView = (TextView) findViewById(R.id.redirectTextView);
		mGestureDetectorListener = new MyGestureDetectorListener();
		mGestureDetector = new GestureDetector(mGestureDetectorListener);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	class MyGestureDetectorListener extends SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			float beforeX = e1.getX();
			float beforeY = e1.getY();
			float afterX = e2.getX();
			float afterY = e2.getY();
			String str = "";
			if(beforeX - afterX > 30){			// 左
				str += "左";
			}else if(beforeX - afterX < -30){	// 右
				str += "右";
			}
			if(beforeY - afterY > 30){			// 上
				str += " 上";
			}else if(beforeY - afterY < -30){	// 下
				str += " 下";
			}
			mTextView.setText(str);
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}
