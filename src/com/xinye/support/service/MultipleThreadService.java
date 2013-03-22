package com.xinye.support.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.xinye.support.ui.MultipleThreadActivity;
/**
 * ���߳����ط���
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
	 * �����߳�
	 * @param threadId �߳�ID
	 * @param startProgress ��ʼ����
	 * @param maxProgress ������
	 */
	private void createThread(int threadId,int startProgress,int maxProgress){
		Intent intent = new Intent(MultipleThreadActivity.ACTION_CREATE_PROGRESS);
		intent.putExtra(MultipleThreadActivity.KEY_THREAD_ID, threadId);
		intent.putExtra(MultipleThreadActivity.KEY_START_PROGRESS, startProgress);
		intent.putExtra(MultipleThreadActivity.KEY_MAX_PROGRESS, maxProgress);
		getApplicationContext().sendBroadcast(intent);
	}
	/**
	 * ���½���
	 * @param threadId �߳�ID
	 * @param progress ��ǰ����
	 */
	/*private void updateThread(int threadId,int progress){
		Intent intent = new Intent(MultipleThreadActivity.ACTION_UPDATE_PROGRESS);
		intent.putExtra(MultipleThreadActivity.KEY_THREAD_ID, threadId);
		intent.putExtra(MultipleThreadActivity.KEY_PROGRESS, progress);
		getApplicationContext().sendBroadcast(intent);
	} */
}
