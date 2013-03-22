package com.xinye.support.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * Fragment
 * @author Administrator
 *
 */
public class MyFragmentActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_my_fragment);
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
	}
}
