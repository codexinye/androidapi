package com.xinye.support.ui;

import net.youmi.android.AdView;
import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * TabHost测试
 * @author Administrator
 *
 */
public class MyTabHostActivity extends TabActivity {
	private TabHost mTabHost = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		this.mTabHost = getTabHost();
		View v = LayoutInflater.from(this).inflate(R.layout.activity_mytabhost,mTabHost.getTabContentView(),true);
		mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
		mTabHost.addTab(mTabHost.newTabSpec("111")
				.setIndicator("111-1", getResources().getDrawable(R.drawable.first))
				.setContent(R.id.widget_layout_red));
		mTabHost.addTab(mTabHost.newTabSpec("222")
				.setIndicator("222-2", getResources().getDrawable(R.drawable.second))
				.setContent(R.id.widget_layout_green));
		mTabHost.addTab(mTabHost.newTabSpec("333")
				.setIndicator("333-3", getResources().getDrawable(R.drawable.three))
				.setContent(R.id.widget_layout_Blue));
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				String text = "";
				if("111".equals(tabId)){
					text = "touch the first tab!";
				}else if("222".equals(tabId)){
					text = "touch the second tab!";
				}else if("333".equals(tabId)){
					text = "touch the third tab!";
				}else{
					text = "unknown error occure!";
				}
				Toast.makeText(MyTabHostActivity.this, text, Toast.LENGTH_SHORT).show();
			}
		});

		LinearLayout adViewLayout = (LinearLayout) v.findViewById(R.id.adViewLayout);
		adViewLayout.addView(new AdView(this), 
		        new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

	}
}
