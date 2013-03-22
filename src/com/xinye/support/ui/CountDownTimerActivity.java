package com.xinye.support.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.TextView;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * Count Down Timer
 * @author Administrator
 *
 */
public class CountDownTimerActivity extends Activity {
	private CountDownTimer mCountDownTimer = null;
	private TextView mTextView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���׹��(���һ�������ڷ�����ʱ����Ϊfalse)
		YouMiUtils.initSDK(this);
		
		this.mCountDownTimer = new MyCountDownTimer(60 * 1000, 1 * 1000);
		mTextView = new TextView(CountDownTimerActivity.this);
		mTextView.setTextSize(50);
		mTextView.setTextColor(Color.argb(0xFF, 0x33, 0x66, 0x99));
		mTextView.setGravity(Gravity.CENTER);
		mTextView.setText(String.format(getResources().getString(R.string.count_down_timer_text), 60));
		mTextView.setBackgroundResource(R.drawable.background);
		this.setContentView(mTextView);
		this.mCountDownTimer.start();
		// ����������
		YouMiUtils.addAdViewByFloating(this);
	}
	private class MyCountDownTimer extends CountDownTimer{
		/**
		 * ��������Ϊ��ʱ��,�ͼ�ʱ��ʱ����(ms)
		 * @param millisInFuture
		 * @param countDownInterval
		 */
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {	// ����ʱ����
			mTextView.setText(String.format(getResources().getString(R.string.count_down_timer_text), millisUntilFinished / 1000));
		}

		@Override
		public void onFinish() {	// ��ʱ���ʱ����
			mTextView.setText(R.string.count_down_timer_finish);
		}
		
	}
	@Override
	protected void onStop() {
		if(mCountDownTimer != null){
			mCountDownTimer.cancel();
			mCountDownTimer = null;
		}
		super.onStop();
	}
}
