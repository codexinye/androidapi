package com.xinye.support.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinye.support.R;
import com.xinye.support.adapter.ViewPagerTestAdapter;
import com.xinye.support.utils.YouMiUtils;
/**
 * ViewPaper测试
 * @author Administrator
 *
 */
public class ViewPagerActivity extends Activity {
	public static final String KEY_TITLE= "title";
	public static final String KEY_IMAGE = "image";
	private ViewPager mViewPager = null;
	private LinearLayout mLinearLayout = null;
	private static final int[] mTitles = {
		R.string.item_01_viewpager_title,
		R.string.item_02_viewpager_title,
		R.string.item_03_viewpager_title,
		R.string.item_04_viewpager_title,
		R.string.item_05_viewpager_title,
		R.string.item_06_viewpager_title,
		R.string.item_07_viewpager_title,
		R.string.item_08_viewpager_title,
		R.string.item_09_viewpager_title,
		R.string.item_00_viewpager_title
	};
	private static final int[] mImages = {
		R.drawable.pre0,
		R.drawable.pre1,
		R.drawable.pre2,
		R.drawable.pre3,
		R.drawable.pre4,
		R.drawable.pre5,
		R.drawable.pre6,
		R.drawable.pre7,
		R.drawable.pre8,
		R.drawable.pre9
	};
	private ArrayList<View> mViews = null;
	private ArrayList<HashMap<String,Integer>> mDatas = null;
	private ImageView[]  mIndicators = null;
	LayoutInflater mInflater = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_viewpager);
		
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		mInflater = getLayoutInflater();
		mViewPager = (ViewPager) findViewById(R.id.viewpagerViewPager);
		mLinearLayout = (LinearLayout) findViewById(R.id.viewpagerLinearLayout);
		initData();
	}
	private void initData() {
		int len = mTitles.length;
		mViews = new ArrayList<View>();
		mDatas = new ArrayList<HashMap<String,Integer>>();
		mIndicators = new ImageView[len];
		HashMap<String,Integer> map = null;
		ImageView imageView;
		for(int i =0 ;i < len;i++){
			mViews.add(mInflater.inflate(R.layout.item_viewpager_viewpager, null));
			map = new HashMap<String, Integer>();
			map.put(KEY_TITLE, mTitles[i]);
			map.put(KEY_IMAGE, mImages[i]);
			mDatas.add(map);
			
			imageView = new ImageView(ViewPagerActivity.this);  
            imageView.setLayoutParams(new LayoutParams(20,20));  
            imageView.setPadding(20, 0, 20, 0);  
            mIndicators[i] = imageView;  
            
            if (i == 0) {  
                //默认选中第一张图片
            	mIndicators[i].setBackgroundResource(R.drawable.page_indicator_focused);  
            } else {  
            	mIndicators[i].setBackgroundResource(R.drawable.page_indicator);
            }
            mLinearLayout.addView(mIndicators[i]);
		}
		mViewPager.setAdapter(new ViewPagerTestAdapter(ViewPagerActivity.this, mDatas, mViews));
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				int len = mViews.size();
				for(int i = 0;i < len;i++){
					if(arg0 == i){
						mIndicators[i].setBackgroundResource(R.drawable.page_indicator_focused);
					}else{
						mIndicators[i].setBackgroundResource(R.drawable.page_indicator);
					}
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
}
