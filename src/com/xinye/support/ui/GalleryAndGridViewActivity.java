package com.xinye.support.ui;

import android.app.Activity;
import android.os.Bundle;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * Gallery + GridView 自动滑动
 * @author Administrator
 *
 */
public class GalleryAndGridViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_gallery_gridview);
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
	}
}
