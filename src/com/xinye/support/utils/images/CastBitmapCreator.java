package com.xinye.support.utils.images;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * ж§дь
 * 
 * @author xinye
 * 
 */
public class CastBitmapCreator implements BitmapCreator {

	@Override
	public Bitmap createBitmap(Bitmap bitmap) {
		if (bitmap == null)
			return null;

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int[] pixels = new int[width * height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int pixel = pixels[i * width + j];
				int r = (pixel & 0x00ff0000) >> 16;
				int g = (pixel & 0x0000ff00) >> 8;
				int b = (pixel & 0x000000ff);

				r = r * 128 / (g + b + 1);
				r = colorSafe(r);

				g = g * 128 / (r + b + 1);
				g = colorSafe(g);

				b = b * 128 / (g + r + 1);
				b = colorSafe(b);
				pixels[i * width + j] = (pixel & 0xff000000) + (r << 16)
						+ (g << 8) + b;
			}
		}

		return Bitmap.createBitmap(pixels, width, height, Config.ARGB_8888);
	}

	private int colorSafe(int value) {

		if (value < 0) {
			value = 0;
		}

		if (value > 255) {
			value = 255;
		}
		return value;
	}

}
