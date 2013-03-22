package com.xinye.support.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinye.support.utils.YouMiUtils;
/**
 * 读取Manifest.xml文件中的数据
 * @author Administrator
 *
 */
public class ManifestActivity extends Activity {
	private TextView mTextView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		mTextView = new TextView(this);
		mTextView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		mTextView.setTextSize(25);
		mTextView.setBackgroundColor(Color.WHITE);
		mTextView.setTextColor(Color.BLUE);
		
		setText();
		setContentView(mTextView);
		// 添加悬浮广告条
		YouMiUtils.addAdViewByFloating(this);
	}

	private void setText() {
		ActivityInfo info;
		ApplicationInfo appInfo; 
		try {
			info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
			String name = info.metaData.getString("name");
			String school = info.metaData.getString("school");
			
			appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
			String phone = appInfo.metaData.getString("phone");
			String address = appInfo.metaData.getString("address");
			
			mTextView.setText("name:" + name + 
					"\nschool:" + school + 
					"\nphone:" + phone + 
					"\naddress:" + address);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
