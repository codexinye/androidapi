package com.xinye.support.utils.images;

import android.graphics.Bitmap;

/**
 * bitmap 构建器
 * @author xinye
 *
 */
public interface BitmapCreator {
	/**
	 * 根据给定的Bitmap构建出一个新的Bitmap并返回
	 * @param bitmap 给定的Bitmap
	 * @return 返回构建完成的Bitmap
	 */
	public Bitmap createBitmap(Bitmap bitmap);
}
