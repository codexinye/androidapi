package com.xinye.support.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

/**
 * ���л���������
 * @author Administrator
 *
 */
public class EnvironmentUtils {
	/**
	 * �ж�SDCard�Ƿ��Ѿ�����
	 * @return true,SDCard�Ѿ�����;else,SDCardû�й���
	 */
	public static boolean isSDCardMounted(){
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	/**
	 * �ж��ֻ��Ƿ�������ͷ��������򷵻�true�����򷵻�false
	 * @param context �����Ķ���
	 * @return true:�ֻ�������ͷ;false���ֻ�û������ͷ
	 */
	public static boolean isHasCamera(Context context){
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			return true;
		}
		return false;
	}
}
