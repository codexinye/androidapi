package com.xinye.support.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinye.support.R;
import com.xinye.support.ui.MainActivity;
/**
 * 主界面GridView的Adapter
 * @author Administrator
 *
 */
public class MainGridViewAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, Integer>> mGridViewItemList = null;
	private Context mContext = null;
//	private Typeface mTypeface = null;
	public MainGridViewAdapter(Context context,
			ArrayList<HashMap<String, Integer>> list) {
		this.mGridViewItemList = list;
		mContext = context;
//		mTypeface = Typeface.createFromAsset(mContext.getAssets(), "fanti.ttf");
	}

	@Override
	public int getCount() {
		if(mGridViewItemList != null){
			return mGridViewItemList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(mGridViewItemList != null){
			return mGridViewItemList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			convertView = inflater.inflate(R.layout.item_gridview_main, null);
			holder = new Holder();
			holder.title = (TextView) convertView.findViewById(R.id.itemGridViewMainTextView);
//			holder.title.setTypeface(mTypeface);
			holder.image = (ImageView) convertView.findViewById(R.id.itemGridViewMainImageView);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		holder.image.setImageResource(mGridViewItemList.get(position).get(MainActivity.KEY_ITEM_IMAGE));
		holder.title.setText(mGridViewItemList.get(position).get(MainActivity.KEY_ITEM_TITLE));
		return convertView;
	}
	class Holder{
		public TextView title;
		public ImageView image;
	}

}
