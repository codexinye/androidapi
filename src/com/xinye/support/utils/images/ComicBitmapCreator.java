package com.xinye.support.utils.images;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
/**
 * Á¬»·»­
 * @author xinye
 *
 */
public class ComicBitmapCreator implements BitmapCreator {

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

				r = algorithm(g, b, r, r);
				g = algorithm(b, g, r, r);
				b = algorithm(b, g, r, g);

				int gray = (r * 3 + g * 6 + b) / 10;
				pixels[i * width + j] = (pixel & 0xff000000) + (gray << 16)
						+ (gray << 8) + gray;
			}
		}

		return Bitmap.createBitmap(pixels, width, height, Config.ARGB_8888);
	}

	private int algorithm(int doubleValue, int negative, int positive, int multi) {
		int value = 2 * doubleValue - negative + positive;

		if (value < 0) {
			value = -value;
		}
		value = value * multi / 256;
		if (value > 255) {
			value = 255;
		}
		return value;
	}

}
