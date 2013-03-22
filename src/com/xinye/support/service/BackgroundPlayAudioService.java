package com.xinye.support.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
/**
 * ��̨������Ƶ����
 * @author Administrator
 *
 */
public class BackgroundPlayAudioService extends Service 
	implements MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener,AudioManager.OnAudioFocusChangeListener{
	private MediaPlayer mMediaPlayer = null;
	/*private static final int RESUEST_CODE_NOTIFICATION = 0x01;*/
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		startMediaPlayer();
		super.onStart(intent, startId);
	}
	private void startMediaPlayer() {
		try {
			releaseMediaPlayer();
			Uri uri = Uri.parse("http://cacom.ca/music/Japanese/JS02.mp3");
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setDataSource(getApplicationContext(), uri);
			mMediaPlayer.setOnPreparedListener(BackgroundPlayAudioService.this);
			mMediaPlayer.prepareAsync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onDestroy() {
		releaseMediaPlayer();
		super.onDestroy();
	}
	/**
	 * �ͷ���Դ
	 */
	private void releaseMediaPlayer(){
		if(mMediaPlayer != null){
			if(mMediaPlayer.isPlaying()){
				mMediaPlayer.stop();
			}
			mMediaPlayer.release();
		}
		mMediaPlayer = null;
	}
	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		
		return false;
	}
	/*private void statusBarNotification(){
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 
				RESUEST_CODE_NOTIFICATION, 
				new Intent(getApplicationContext(),MultipleMediaActivity.class), 
				PendingIntent.FLAG_UPDATE_CURRENT);
		Notification notification = new Notification();
		notification.icon = R.drawable.first;
		notification.tickerText = "Media Player";
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.setLatestEventInfo(getApplicationContext(), "Title", "contentText", pendingIntent);
		startForeground(1, notification);
		//stopForeground(true);
	}*/
	/*private void requestAudioFocus(){
		AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
		int result = audioManager.requestAudioFocus(BackgroundPlayAudioService.this, 
				AudioManager.STREAM_MUSIC, 
				AudioManager.AUDIOFOCUS_GAIN);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
			
		}
	}*/
	@Override
	public void onAudioFocusChange(int focusChange) {
		/**��ס,��Ƶ����APIs��API����8(Android 2.2)�����ϲ���Ч
		 * focusChange ������������Ƶ��������θı�ģ����ҿ���ʹ�����µ�ֵ֮һ�����Ƕ�����AudioManager�ж��峣���ģ��� 
		 * AUDIOFOCUS_GAIN: ���Ѿ��õ�����Ƶ���㡣
		 * AUDIOFOCUS_LOSS: ���Ѿ�ʧȥ����Ƶ����ܳ�ʱ���ˡ������ֹͣ���е���Ƶ���š�
		 * 		��Ϊ��Ӧ�ò�ϣ����ʱ��ȴ����㷵�أ��⽫���㾡������������Դ��һ���õط������磬��Ӧ���ͷ�MediaPlayer�� 
		 * AUDIOFOCUS_LOSS_TRANSIENT:����ʱʧȥ����Ƶ���㣬���ܿ�����µõ����㡣
		 * 		�����ֹͣ���е���Ƶ���ţ���������Ա��������Դ����Ϊ����ܺܿ�����»�ý��㡣
		 * AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: ����ʱʧȥ����Ƶ���㣬�������С���ؼ���������Ƶ������������������ȫ��ɱ��Ƶ��
		 */
	}
}
