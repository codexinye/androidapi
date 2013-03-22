package com.xinye.support.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * gallery
 * @author Administrator
 *
 */
public class GalleryTestActivity extends Activity {
	private Gallery mGallery = null;
	private ImageView mImageView = null;
	private ArrayList<Integer> mList = null;
	private Integer[] mImageIds = {
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_gallery);

		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
				
		mList = new ArrayList<Integer>();
		for(Integer imageId : mImageIds){
			mList.add(imageId);
		}
		mGallery = (Gallery) findViewById(R.id.galleryGallery);
		mImageView = (ImageView) findViewById(R.id.galleryImageView);
		mImageView.setImageResource(mImageIds[0]);
		mGallery.setAdapter(new ImageAdapter());
		mGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mImageView.setImageResource(mList.get(position));
			}
		});
	}
	class ImageAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			if(mList != null){
				return mList.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if(mList != null){
				return mList.get(position);
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
				convertView = getLayoutInflater().inflate(R.layout.item_gallery, null);
				holder = new Holder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.galleryItemImageView);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			if(mList != null){
				holder.imageView.setImageResource(mList.get(position));				
			}
			return convertView;
		}
		class Holder{
			ImageView imageView;
		}
	}
}
