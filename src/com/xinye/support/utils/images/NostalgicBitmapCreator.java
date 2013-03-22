package com.xinye.support.utils.images;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
/**
 * »³¾ÉÍ¼Æ¬¹¹½¨Æ÷
 * @author xinye
 *
 */
public class NostalgicBitmapCreator implements BitmapCreator {

	@Override
	public Bitmap createBitmap(Bitmap bitmap) {
		    int width = bitmap.getWidth();
		    int height = bitmap.getHeight();
		    Bitmap buffer = Bitmap.createBitmap(width , height , Config.ARGB_8888);
		    Canvas canvas = new Canvas(buffer);
		    Paint paint = new Paint();
		    ColorMatrix matrix = new ColorMatrix();
		    float[] array = new float[]{
		    		.393f,0.769f,0.189f,0,0,
		    		.349f,0.686f,0.168f,0,0,
		    		.272f,0.534f,0.131f,0,0,
		    		0,0,0,1,0
		    };
		    matrix.set(array);
		    paint.setColorFilter(new ColorMatrixColorFilter(matrix));
		    canvas.drawBitmap(bitmap , 0,0,paint);
		    return buffer;
		  }


}
