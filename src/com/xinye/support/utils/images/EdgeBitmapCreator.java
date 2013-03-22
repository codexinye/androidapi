package com.xinye.support.utils.images;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
/**
 * ±ﬂ‘µ∏ﬂ¡¡
 * @author xinye
 *
 */
public class EdgeBitmapCreator implements BitmapCreator {

	@Override
	public Bitmap createBitmap(Bitmap bitmap) {
		if (bitmap == null)
			return null;

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int[] pixels = new int[width * height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

		int rectTop = 0;
		int rectBottom = height - 1;
		int rectLeft = 0;
		int rectRight = width - 1;

		for (int i = rectTop; i < rectBottom; i++) {
			for (int j = rectLeft; j < rectRight; j++) {
				int pixel = pixels[i * width + j];
				int leftPixel = pixels[i * width + j + 1];
				int bottomPixel = pixels[i * width + j + width];
				int r = (pixel & 0x00ff0000) >> 16;
				int g = (pixel & 0x0000ff00) >> 8;
				int b = (pixel & 0x000000ff);

				int leftR = (leftPixel & 0x00ff0000) >> 16;
				int leftG = (leftPixel & 0x0000ff00) >> 8;
				int leftB = (leftPixel & 0x000000ff);

				int bottomR = (bottomPixel & 0x00ff0000) >> 16;
				int bottomG = (bottomPixel & 0x0000ff00) >> 8;
				int bottomB = (bottomPixel & 0x000000ff);
				r = algorithm(r, leftR, bottomR);
				g = algorithm(g, leftG, bottomG);
				b = algorithm(b, leftB, bottomB);
				pixels[i * width + j] = (pixel & 0xff000000) + (r << 16)
						+ (g << 8) + b;
			}
		}

		return Bitmap.createBitmap(pixels, width, height, Config.ARGB_8888);
	}

	private int algorithm(int value, int leftValue, int bottomValue) {

		int pixel = (int) (Math.pow(value - bottomValue, 2) + Math.pow(value
				- leftValue, 2));
		pixel = (int) (Math.sqrt(pixel) * 2);
		if (pixel < 0) {
			pixel = 0;
		}

		if (pixel > 255) {
			pixel = 255;
		}
		return pixel;
	}

}
