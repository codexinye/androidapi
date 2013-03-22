package com.xinye.support.utils.images;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * ¸¡µñ
 * 
 * @author xinye
 * 
 */
public class ReliefBitmapCreator implements BitmapCreator {

	@Override
	public Bitmap createBitmap(Bitmap bitmap) {
		if (bitmap == null)
			return null;
		// bitmap = toGray(bitmap);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int[] pixels = new int[width * height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = height - 1; i > 0; i--) {
			for (int j = width - 1; j > 0; j--) {
				int pixel = pixels[i * width + j];
				int leftUpPixel = pixels[(i - 1) * width + j - 1];
				int r = (pixel & 0x00ff0000) >> 16;
				int g = (pixel & 0x0000ff00) >> 8;
				int b = (pixel & 0x000000ff);

				int leftUpR = (leftUpPixel & 0x00ff0000) >> 16;
				int leftUpG = (leftUpPixel & 0x0000ff00) >> 8;
				int leftUpB = (leftUpPixel & 0x000000ff);

				r = r - leftUpR;
				g = g - leftUpG;
				b = b - leftUpB;

				int maxDiff = r;
				if (Math.abs(maxDiff) < Math.abs(g))
					;
				{
					maxDiff = g;
				}

				if (Math.abs(maxDiff) < Math.abs(b)) {
					maxDiff = b;
				}

				int gray = maxDiff + 128;
				if (gray > 255)
					gray = 255;
				if (gray < 0)
					gray = 0;

				pixels[i * width + j] = (pixel & 0xff000000) + (gray << 16)
						+ (gray << 8) + gray;
			}
		}

		return Bitmap.createBitmap(pixels, width, height, Config.ARGB_8888);
	}

}
