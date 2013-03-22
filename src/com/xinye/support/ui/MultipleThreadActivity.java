package com.xinye.support.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xinye.support.R;
import com.xinye.support.service.MultipleThreadService;
import com.xinye.support.utils.YouMiUtils;
/**
 * 多线程下载/断点续传
 * @author Administrator
 *
 */
public class MultipleThreadActivity extends Activity implements OnClickListener{
	private EditText mDownloadUrlEditText = null;
	private EditText mDownloadThreadEditText = null;
	private Button mDownloadButton = null;
	private LinearLayout mProgressBarLinearLayout = null;
	public static final String DOWNLOAD_URL = "download_url";
	public static final String DOWNLOAD_THREAD = "download_thread";
	public static final String ACTION_CREATE_PROGRESS = "create_progress";
	public static final String ACTION_UPDATE_PROGRESS = "update_progress";
	private static final int HANDLE_CREATE_PROGRESS = 0x01;
	private static final int HANDLE_UPDATE_PROGRESS = 0x02;
	public static final String KEY_THREAD_ID = "key_thread_id";
	public static final String KEY_START_PROGRESS = "key_start_progress";
	public static final String KEY_MAX_PROGRESS = "key_max_progress";
	public static final String KEY_PROGRESS = "key_progress";
	
	private ProgressBarBroadcastReceiver mProgressBarBroadcastReceiver = null;
	private String mUrl = null;
	/**
	 * 处理子线程发送的消息
	 */
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
				case HANDLE_CREATE_PROGRESS:{
					Bundle data = msg.getData();
					createProgressBar(data.getInt(KEY_THREAD_ID), 
							data.getInt(KEY_START_PROGRESS), 
							data.getInt(KEY_MAX_PROGRESS));
				}break;
				case HANDLE_UPDATE_PROGRESS:{
					Bundle data = msg.getData();
					updateProgressBar(data.getInt(KEY_THREAD_ID), data.getInt(KEY_PROGRESS));
				}break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_download);

		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		init();
	}
	@Override
	protected void onStart() {
		registerProgressBarBroadcastReceiver();
		super.onStart();
	}
	@Override
	protected void onStop() {
		unregisterProgressBarBroadcastReceiver();
		super.onStop();
	}
	private void init() {
		mDownloadUrlEditText = (EditText) findViewById(R.id.downloadUrlEditText);
		mDownloadThreadEditText = (EditText) findViewById(R.id.downloadThreadNumEditText);
		mDownloadUrlEditText.addTextChangedListener(mTextWatch);
		mDownloadButton = (Button) findViewById(R.id.downloadButton);
		mProgressBarLinearLayout = (LinearLayout) findViewById(R.id.downloadProgressBarLinearLayout);
		
		mDownloadButton.setOnClickListener(MultipleThreadActivity.this);
	}
	private TextWatcher mTextWatch = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		@Override
		public void afterTextChanged(Editable s) {
			if(mDownloadButton != null || !mDownloadUrlEditText.getText().toString().equals(mUrl)){
				mDownloadButton.setEnabled(true);
			}
		}
	};
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.downloadButton:{
				Intent intent = new Intent(MultipleThreadActivity.this,MultipleThreadService.class);
				intent.putExtra(DOWNLOAD_URL, mDownloadUrlEditText.getText().toString());
				intent.putExtra(DOWNLOAD_THREAD, mDownloadThreadEditText.getText().toString());
				MultipleThreadActivity.this.startService(intent);
				mDownloadButton.setEnabled(false);
				mUrl = mDownloadUrlEditText.getText().toString();
			}break;
		}
	}
	/**
	 * 根据ProgressBar 的ID、开始进度、最大进度创建ProgressBar
	 * @param progressId ProgressBar的ID
	 * @param startProgress ProgressBar的开始Progress
	 * @param maxProgress ProgressBar的最大Progress
	 */
	private void createProgressBar(int progressId,int startProgress,int maxProgress) {
		
		ProgressBar progressBar = new ProgressBar(MultipleThreadActivity.this);

		
		Class<ProgressBar> clazz = ProgressBar.class;
		try {
			Field field = clazz.getDeclaredField("mOnlyIndeterminate");
			if(!Modifier.isPublic(field.getModifiers()) 
					|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())){
				field.setAccessible(true);
			}
			field.setBoolean(progressBar, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		progressBar.setIndeterminate(false);
		progressBar.setProgressDrawable(getResources()
				.getDrawable(android.R.drawable.progress_horizontal));
		progressBar.setIndeterminateDrawable(getResources()
				.getDrawable(android.R.drawable.progress_indeterminate_horizontal));
		progressBar.setMinimumHeight(20);
		
		progressBar.setId(progressId);
		progressBar.setProgress(startProgress);
		progressBar.setMax(maxProgress);
		LinearLayout.LayoutParams params 
			= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,5);
		params.setMargins(5, 5, 5, 5);
		progressBar.setLayoutParams(params);
		mProgressBarLinearLayout.addView(progressBar,progressId);
	}
	/**
	 * 根据ProgressBar的Id和指定的进度，更新ProgressBar的进度
	 * @param progressId ProgressBar的Id
	 * @param progress ProgressBar的进度
	 */
	private void updateProgressBar(int progressId,int progress){
		Toast.makeText(this, ">>>>>>>>>>>>>>> update progress", 1).show();
		ProgressBar progressBar = (ProgressBar) mProgressBarLinearLayout.findViewById(progressId);
		if(progressBar != null){
			if(progressBar.getProgress() + progress <= progressBar.getMax()){
				progressBar.setProgress(progress);				
			}else{
				progressBar.setProgress(progressBar.getMax());
			}
		}
	}
	/**
	 * 注册广播
	 */
	private void registerProgressBarBroadcastReceiver(){
		if(mProgressBarBroadcastReceiver != null){
			unregisterProgressBarBroadcastReceiver();
		}
		mProgressBarBroadcastReceiver = new ProgressBarBroadcastReceiver();
		MultipleThreadActivity.this.registerReceiver(mProgressBarBroadcastReceiver, 
				new IntentFilter(ACTION_CREATE_PROGRESS));
	}
	/**
	 * 注销广播
	 */
	private void unregisterProgressBarBroadcastReceiver(){
		if(mProgressBarBroadcastReceiver != null){
			MultipleThreadActivity.this.unregisterReceiver(mProgressBarBroadcastReceiver);
			mProgressBarBroadcastReceiver = null;
		}		
	}
	private class ProgressBarBroadcastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(ACTION_CREATE_PROGRESS.equals(action)){			// 创建ProgressBar
				Message msg = new Message();
				msg.what = HANDLE_CREATE_PROGRESS;
				Bundle data = new Bundle();
				data.putInt(KEY_THREAD_ID, intent.getIntExtra(KEY_THREAD_ID, 0));
				data.putInt(KEY_START_PROGRESS, intent.getIntExtra(KEY_START_PROGRESS, 0));
				data.putInt(KEY_MAX_PROGRESS, intent.getIntExtra(KEY_MAX_PROGRESS, 0));
				msg.setData(data);
				mHandler.sendMessage(msg);
			}else if(ACTION_UPDATE_PROGRESS.equals(action)){	// 更新ProgressBar
				Message msg = new Message();
				msg.what = HANDLE_UPDATE_PROGRESS;
				Bundle data = new Bundle();
				data.putInt(KEY_THREAD_ID, intent.getIntExtra(KEY_THREAD_ID, 0));
				data.putInt(KEY_PROGRESS, intent.getIntExtra(KEY_PROGRESS, 0));
				msg.setData(data);
				mHandler.sendMessage(msg);
			}
		}
		
	}
}
