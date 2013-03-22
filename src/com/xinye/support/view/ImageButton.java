package com.xinye.support.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.Button;
/**
 * ×Ô¶¨ÒåButton
 * @author Administrator
 *
 */
public class ImageButton extends Button {
	public ImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if(focused){
			this.setTextColor(Color.argb(0xFF, 0x00, 0x99, 0xFF));
		}else{
			this.setTextColor(Color.WHITE);
		}
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}

}
