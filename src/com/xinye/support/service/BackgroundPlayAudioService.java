package com.xinye.support.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
/**
 * 后台播放音频服务
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
	 * 释放资源
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
		/**记住,音频焦点APIs在API级别8(Android 2.2)及以上才有效
		 * focusChange 参数告诉你音频焦点是如何改变的，并且可以使用以下的值之一（他们都是在AudioManager中定义常量的）： 
		 * AUDIOFOCUS_GAIN: 你已经得到了音频焦点。
		 * AUDIOFOCUS_LOSS: 你已经失去了音频焦点很长时间了。你必须停止所有的音频播放。
		 * 		因为你应该不希望长时间等待焦点返回，这将是你尽可能清除你的资源的一个好地方。例如，你应该释放MediaPlayer。 
		 * AUDIOFOCUS_LOSS_TRANSIENT:你暂时失去了音频焦点，但很快会重新得到焦点。
		 * 		你必须停止所有的音频播放，但是你可以保持你的资源，因为你可能很快会重新获得焦点。
		 * AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: 你暂时失去了音频焦点，但你可以小声地继续播放音频（低音量）而不是完全扼杀音频。
		 */
	}
}
