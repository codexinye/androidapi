package com.xinye.support.ui;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * Camera Ԥ����ͼ
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
			 // surface�ѱ����������ڰ�Ԥ�������λ��֪ͨ����ͷ
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		// ���Ԥ���޷����Ļ���ת��ע��˴����¼�
        // ȷ�������Ż�����ʱֹͣԤ��
		if(mHolder.getSurface() == null){
			return;
		}
		try{
			// ����ʱֹͣԤ�� 
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
		// �մ��롣ע����activity���ͷ�����ͷԤ������
	}
}
