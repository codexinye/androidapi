package com.xinye.support.ui;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * Camera 预览视图
 * @author Administrator
 *
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
	private Camera mCamera = null;
	private SurfaceHolder mHolder = null;
	public CameraPreview(Context context) {
		super(context);
	}
	public CameraPreview(Context context,Camera camera) {
		super(context);
		this.mCamera = camera;
		mHolder = getHolder();
		mHolder.addCallback(CameraPreview.this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try{
			 // surface已被创建，现在把预览画面的位置通知摄像头
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		// 如果预览无法更改或旋转，注意此处的事件
        // 确保在缩放或重排时停止预览
		if(mHolder.getSurface() == null){
			return;
		}
		try{
			// 更改时停止预览 
			mCamera.stopPreview();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 空代码。注意在activity中释放摄像头预览对象
	}
}
