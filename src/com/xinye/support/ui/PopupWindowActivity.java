package com.xinye.support.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * PopupWindow
 * @author Administrator
 *
 */
public class PopupWindowActivity extends Activity{
	private LinearLayout mRootView = null; 
	private PopupWindow mPopupWindow = null;
	private View mPopupWindowView = null;
	private ListView mListView = null;
	private ArrayList<String> mDataList = null; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_popupwindow);
		
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		mRootView = (LinearLayout) findViewById(R.id.popupwindowRoot);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			showPopupWindow(event);
		}
		return super.onTouchEvent(event);
	}
	private void showPopupWindow(MotionEvent event) {
		if(mPopupWindow == null){
			 mPopupWindowView = getLayoutInflater().inflate(R.layout.popupwindown_layout, null);
			 mListView = (ListView) mPopupWindowView.findViewById(R.id.popupwindowListView);
			 mDataList = new ArrayList<String>();
			 mDataList.add("新浪微博 - www.weibo.com");
			 mDataList.add("百度首页 - www.baidu.com");
			 mDataList.add("腾讯QQ - www.qq.com");
			 mDataList.add("google - www.google.com");
			 mDataList.add("微软首页 - www.microsoft.com");
			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					 PopupWindowActivity.this, 
					 android.R.layout.simple_list_item_1,
					 mDataList);
			 mListView.setAdapter(adapter);
			 mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					if(mPopupWindow != null){
						mPopupWindow.dismiss();
					}
					Toast.makeText(PopupWindowActivity.this, mDataList.get(position), Toast.LENGTH_LONG).show();
				}
				 
			 });
			 mPopupWindow = new PopupWindow(mPopupWindowView, 
					 ViewGroup.LayoutParams.WRAP_CONTENT, 
					 ViewGroup.LayoutParams.WRAP_CONTENT, true);
		}
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.showAtLocation(mRootView, Gravity.TOP, (int)event.getX(),(int) event.getY());
	}
}
