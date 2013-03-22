package com.xinye.support.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

/**
 * 运行环境工具类
 * @author Administrator
 *
 */
public class EnvironmentUtils {
	/**
	 * 判断SDCard是否已经挂载
	 * @return true,SDCard已经挂载;else,SDCard没有挂载
	 */
	public static boolean isSDCardMounted(){
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	/**
	 * 判断手机是否有摄像头，如果有则返回true，否则返回false
	 * @param context 上下文对象
	 * @return true:手机有摄像头;false：手机没有摄像头
	 */
	public static boolean isHasCamera(Context context){
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			return true;
		}
		return false;
	}
}
