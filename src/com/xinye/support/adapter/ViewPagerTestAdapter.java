package com.xinye.support.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinye.support.R;
import com.xinye.support.ui.ViewPagerActivity;

public class ViewPagerTestAdapter extends PagerAdapter {
	private ArrayList<HashMap<String,Integer>> mList = null;
	private ArrayList<View> mViews = null;
	public ViewPagerTestAdapter(Context context,
			ArrayList<HashMap<String,Integer>> list,
			ArrayList<View> views){
		this.mList = list;
		this.mViews = views;
	}
	@Override
	public int getCount() {
		if(mViews != null){
			return mViews.size();
		}
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0 == arg1;
	}
	@Override  
    public int getItemPosition(Object object) {  
        return super.getItemPosition(object);  
    }  

    @Override  
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(mViews.get(arg1));  
    }  

    @Override  
    public Object instantiateItem(View arg0, int arg1) {  
        // TODO Auto-generated method stub  
        ((ViewPager) arg0).addView(mViews.get(arg1));
        ((TextView)(mViews.get(arg1)
        		.findViewById(R.id.itemViewPagerTextView)))
        		.setText(mList.get(arg1).get(ViewPagerActivity.KEY_TITLE));
        ((ImageView)(mViews.get(arg1)
        		.findViewById(R.id.itemViewPagerImageView)))
        		.setImageResource(mList.get(arg1).get(ViewPagerActivity.KEY_IMAGE));
        return mViews.get(arg1);  
    }  

    @Override  
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }  

    @Override  
    public Parcelable saveState() { 
        return null;  
    }  

    @Override  
    public void startUpdate(View arg0) {  
    }  

    @Override  
    public void finishUpdate(View arg0) {  
        // TODO Auto-generated method stub  

    }  
}
