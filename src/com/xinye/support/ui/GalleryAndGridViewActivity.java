package com.xinye.support.ui;

import android.app.Activity;
import android.os.Bundle;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * Gallery + GridView �Զ�����
 * @author Administrator
 *
 */
public class GalleryAndGridViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_gallery_gridview);
		// ��ӹ����
		YouMiUtils.addAdViewByXML(this);
	}
}
