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
 * YouMi ������
 * @author Administrator
 *
 */
public class YouMiUtils {
	/**
	 * ͨ��Context�����ʼ��YouMi SDK
	 * @param context
	 */
	public static void initSDK(Context context){
		// ���׹��(���һ�������ڷ�����ʱ����Ϊfalse)
		// ����ʱ���һ�������޸�Ϊfalse
		AdManager.init(context,context.getResources().getString(R.string.youmi_id), 
				context.getResources().getString(R.string.youmi_key), 30,  false);
	}
	/**
	 * ���ӹ����Ŀͨ��XML�ļ�
	 * @param activity Activity
	 */
	public static void addAdViewByXML(Activity activity){
		LinearLayout adViewLayout = (LinearLayout) activity.findViewById(R.id.adViewLayout);
		adViewLayout.addView(new AdView(activity), 
		        new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
		        		LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	/**
	 * ���һ��������浽ָ����activity
	 * @param activity Ҫ��ӹ���activity
	 */
	public static void addAdViewByFloating(Activity activity){
		//��ʼ�������ͼ
 		AdView adView = new AdView(activity);
 		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
 		//���ù����ֵ�λ��(��������Ļ���½�)		 
 		params.gravity=Gravity.BOTTOM|Gravity.RIGHT; 
 		//�������ͼ����Activity��
 		activity.addContentView(adView, params);
	}
}
