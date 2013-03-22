package com.xinye.support.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xinye.support.R;
import com.xinye.support.service.BackgroundPlayAudioService;
import com.xinye.support.utils.EnvironmentUtils;
import com.xinye.support.utils.YouMiUtils;

/**
 * 多媒体
 * 
 * @author Administrator
 * 
 */
public class MultipleMediaActivity extends Activity implements OnClickListener {

	private static final int REQUEST_CODE_AUDIO = 0x01; // 选择本地音频文件

	private Button mPlayRawAudioButton = null;
	private Button mPlayFileAudioButton = null;
	private Button mPlayNetworkAudioButton = null;
	private Button mStartBackgroundAudioButton = null;
	private Button mStopBackgroundAudioButton = null;
	private Button mStartPreviewPictureButton = null;
	private Button mStartCapturePictureButton = null;

	@SuppressWarnings("unused")
	private Button mStartCaptureVideoButton = null;
	@SuppressWarnings("unused")
	private Button mStopCaptureVideoButton = null;
	private Button mStartPreviewVideoButton = null;
	
	private FrameLayout mFrameLayout = null;

	private MediaPlayer mMediaPlayer = null;
	private Camera mCamera = null;
	private CameraPreview mCameraPreview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_multiplemedia);
		
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		init();
	}

	private void init() {
		mPlayRawAudioButton = (Button) findViewById(R.id.playRawAudioButton);
		mPlayRawAudioButton.setOnClickListener(MultipleMediaActivity.this);

		mPlayFileAudioButton = (Button) findViewById(R.id.playFileAudioButton);
		mPlayFileAudioButton.setOnClickListener(MultipleMediaActivity.this);

		mPlayNetworkAudioButton = (Button) findViewById(R.id.playNetworkAudioButton);
		mPlayNetworkAudioButton.setOnClickListener(MultipleMediaActivity.this);

		mStartBackgroundAudioButton = (Button) findViewById(R.id.playStartBackgroundAudioButton);
		mStartBackgroundAudioButton.setOnClickListener(this);

		mStopBackgroundAudioButton = (Button) findViewById(R.id.playStopBackgroundAudioButton);
		mStopBackgroundAudioButton.setOnClickListener(this);

		mStartPreviewPictureButton = (Button) findViewById(R.id.startPreviewPictureButton);
		mStartPreviewPictureButton.setOnClickListener(this);

		mStartPreviewVideoButton = (Button) findViewById(R.id.startPreviewVideoButton);
		mStartPreviewVideoButton.setOnClickListener(this);

		mFrameLayout = (FrameLayout) findViewById(R.id.camera_preview);
		mStartCapturePictureButton = (Button) findViewById(R.id.startCapturePicutreButton);
		
		mStartCaptureVideoButton = (Button) findViewById(R.id.startCaptureVideoButton);
		mStopCaptureVideoButton = (Button) findViewById(R.id.stopCaptureVideoButton);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.playRawAudioButton: {
				playRawAudio();
			}break;
			case R.id.playFileAudioButton: {
				playFileAudio();
			}break;
			case R.id.playNetworkAudioButton: {
				playNetworkAudio();
			}break;
			case R.id.playStartBackgroundAudioButton: {
				playStartBackgroundAudio();
			}break;
			case R.id.playStopBackgroundAudioButton: {
				playStopBackgourndAudio();
			}break;
			case R.id.startPreviewPictureButton: {
				startPreviewPicture();
			}break;
			case R.id.startCapturePicutreButton: {
				startCapturePicture();
			}break;
			case R.id.startPreviewVideoButton:{
				startPreviewVideo();
			}break;
		}
	}

	private void startPreviewVideo() {
		if(EnvironmentUtils.isHasCamera(MultipleMediaActivity.this)){
			mCamera = Camera.open();
		}
	}

	private void startCapturePicture() {
		mCamera.takePicture(null, null, new PictureCallback() {
			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
				if (pictureFile == null) {
					return;
				}
				try {
					FileOutputStream fos = new FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();
				} catch (FileNotFoundException e) {
					Log.d("support", "File not found: " + e.getMessage());
				} catch (IOException e) {
					Log.d("support", "Error accessing file: " + e.getMessage());
				}
			}
		});
	}
	
/*	private static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}*/

	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

	private static File getOutputMediaFile(int type) {
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyCameraApp");
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}
		return mediaFile;
	}

	private void startPreviewPicture() {
		try {
			if (EnvironmentUtils.isHasCamera(MultipleMediaActivity.this)) {
				mCamera = Camera.open();
				mCameraPreview = new CameraPreview(MultipleMediaActivity.this,
						mCamera);
				mFrameLayout.addView(mCameraPreview);
				mStartCapturePictureButton.setOnClickListener(MultipleMediaActivity.this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止后台音频播放
	 */
	private void playStopBackgourndAudio() {
		MultipleMediaActivity.this.stopService(new Intent(
				MultipleMediaActivity.this, BackgroundPlayAudioService.class));
	}

	/**
	 * 开始后台音频播放
	 */
	private void playStartBackgroundAudio() {
		MultipleMediaActivity.this.startService(new Intent(
				MultipleMediaActivity.this, BackgroundPlayAudioService.class));
	}

	/**
	 * 播放本地文件
	 */
	private void playFileAudio() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/*");
		MultipleMediaActivity.this.startActivityForResult(intent,
				REQUEST_CODE_AUDIO);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_CODE_AUDIO) {
				try {
					releaseMediaPlayer();
					Uri uri = data.getData();
					mMediaPlayer = new MediaPlayer();
					mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
					mMediaPlayer.setDataSource(MultipleMediaActivity.this, uri);
					mMediaPlayer.prepare();
					mMediaPlayer.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 播放网络音频
	 */
	private void playNetworkAudio() {
		try {
			releaseMediaPlayer();
			Uri url = Uri.parse("http://cacom.ca/music/Japanese/JS02.mp3");
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setDataSource(MultipleMediaActivity.this, url);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
			/*
			 * mMediaPlayer.prepareAsync();
			 * mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			 * 
			 * @Override public void onPrepared(MediaPlayer mp) { } });
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 播放/res/raw下的音频
	 */
	private void playRawAudio() {
		releaseMediaPlayer();
		mMediaPlayer = MediaPlayer.create(MultipleMediaActivity.this,
				R.raw.ydcyxf);
		mMediaPlayer.start();
	}

	/*
	 * private void lock(){ mMediaPlayer.setWakeMode(getApplicationContext(),
	 * PowerManager.PARTIAL_WAKE_LOCK); WifiLock wifiLock =
	 * ((WifiManager)getSystemService(Context.WIFI_SERVICE))
	 * .createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock");
	 * wifiLock.acquire(); wifiLock.release(); }
	 */
	/**
	 * 释放播放器资源
	 */
	private void releaseMediaPlayer() {
		if (mMediaPlayer != null) {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
			}
			mMediaPlayer.release();
		}
		mMediaPlayer = null;
	}

	@Override
	protected void onStop() {
		releaseMediaPlayer();
		if(mCamera != null){
			mCamera.release();
		}
		super.onStop();
	}
}
