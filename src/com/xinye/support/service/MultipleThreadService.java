package com.xinye.support.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.xinye.support.ui.MultipleThreadActivity;
/**
 * 多线程下载服务
 * @author Administrator
 *
 */
public class MultipleThreadService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		if(intent != null){
			String threadNumberString = intent.getStringExtra(MultipleThreadActivity.DOWNLOAD_THREAD);
			int threadNumber = Integer.parseInt(threadNumberString);
			for(int i = 0;i < threadNumber;i++){
				createThread(i, 60, 200);
			}
			Log.i("download", "intent thread url :" + intent.getStringExtra(MultipleThreadActivity.DOWNLOAD_URL));
		}
		super.onStart(intent, startId);
	}
	/**
	 * 创建线程
	 * @param threadId 线程ID
	 * @param startProgress 开始进度
	 * @param maxProgress 最大进度
	 */
	private void createThread(int threadId,int startProgress,int maxProgress){
		Intent intent = new Intent(MultipleThreadActivity.ACTION_CREATE_PROGRESS);
		intent.putExtra(MultipleThreadActivity.KEY_THREAD_ID, threadId);
		intent.putExtra(MultipleThreadActivity.KEY_START_PROGRESS, startProgress);
		intent.putExtra(MultipleThreadActivity.KEY_MAX_PROGRESS, maxProgress);
		getApplicationContext().sendBroadcast(intent);
	}
	/**
	 * 更新进度
	 * @param threadId 线程ID
	 * @param progress 当前进度
	 */
	/*private void updateThread(int threadId,int progress){
		Intent intent = new Intent(MultipleThreadActivity.ACTION_UPDATE_PROGRESS);
		intent.putExtra(MultipleThreadActivity.KEY_THREAD_ID, threadId);
		intent.putExtra(MultipleThreadActivity.KEY_PROGRESS, progress);
		getApplicationContext().sendBroadcast(intent);
	} */
}
