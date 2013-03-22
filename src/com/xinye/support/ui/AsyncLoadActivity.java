package com.xinye.support.ui;

import java.util.ArrayList;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xinye.support.R;
import com.xinye.support.utils.AsyncImageLoader;
/**
 * “Ï≤Ωº”‘ÿÕº∆¨
 * @author Administrator
 *
 */
public class AsyncLoadActivity extends ListActivity{
	private ArrayList<String> mImageUrlList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mImageUrlList = new ArrayList<String>();
		for(int i = 1;i < 11;i++){
        	mImageUrlList.add("http://www.xahxu.net/Upfiles/Bimg/101010/1_101010230714_" + i + ".jpg");
        }
		ImageAdapter adapter = new ImageAdapter();
		getListView().setAdapter(adapter);
		getListView().setFastScrollEnabled(true);

	}
	class ImageAdapter extends BaseAdapter{
		AsyncImageLoader asyncImageLoader = null;
		public ImageAdapter(){
			asyncImageLoader = new AsyncImageLoader();
		}
		@Override
		public int getCount() {
			if(mImageUrlList != null){
				return mImageUrlList.size();
			}else{
				return 0;
			}
		}
		@Override
		public Object getItem(int position) {
			if(mImageUrlList != null){
				return mImageUrlList.get(position);
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
				convertView = getLayoutInflater().inflate(R.layout.asyncload_item, null);
				holder = new Holder();
				holder.imageview = (ImageView) convertView.findViewById(R.id.asyncloadImageView);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			holder.imageview.setTag(mImageUrlList.get(position));
			asyncImageLoader.loadDrawable(
					AsyncLoadActivity.this,
					mImageUrlList.get(position),
					new AsyncImageLoader.ImageCallback() {
				@Override
				public void imageLoaded(Drawable imageDrawable, String imageUrl) {
					ImageView imageView = (ImageView) getListView().findViewWithTag(imageUrl);
					if(imageDrawable != null && imageView != null){
						imageView.setImageDrawable(imageDrawable);
					}/*else if(imageView != null){
						imageView.setImageResource(R.drawable.first);
					}*/
				}
			});
			
			return convertView;
		}
		class Holder {
			ImageView imageview;
		}
	}
}
