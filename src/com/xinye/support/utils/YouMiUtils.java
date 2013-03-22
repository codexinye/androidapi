package com.xinye.support.utils;

import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xinye.support.R;

/**
 * YouMi 帮助类
 * @author Administrator
 *
 */
public class YouMiUtils {
	/**
	 * 通过Context对象初始化YouMi SDK
	 * @param context
	 */
	public static void initSDK(Context context){
		// 有米广告(最后一个参数在发布的时候设为false)
		// 发布时最后一个参数修改为false
		AdManager.init(context,context.getResources().getString(R.string.youmi_id), 
				context.getResources().getString(R.string.youmi_key), 30,  false);
	}
	/**
	 * 增加广告条目通过XML文件
	 * @param activity Activity
	 */
	public static void addAdViewByXML(Activity activity){
		LinearLayout adViewLayout = (LinearLayout) activity.findViewById(R.id.adViewLayout);
		adViewLayout.addView(new AdView(activity), 
		        new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
		        		LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	/**
	 * 添加一个悬浮广告到指定的activity
	 * @param activity 要添加广告的activity
	 */
	public static void addAdViewByFloating(Activity activity){
		//初始化广告视图
 		AdView adView = new AdView(activity);
 		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
 		//设置广告出现的位置(悬浮于屏幕右下角)		 
 		params.gravity=Gravity.BOTTOM|Gravity.RIGHT; 
 		//将广告视图加入Activity中
 		activity.addContentView(adView, params);
	}
}
