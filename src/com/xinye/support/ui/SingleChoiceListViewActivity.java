package com.xinye.support.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * ListView��Ŀ��ѡ(��ListView����Ŀ���Ǻܶࡢ��Ч��Ҫ���Ǻܸߵ�ʱ���Ƽ�ʹ�ã�����������������������Ƽ�ʹ��)
 * @author xinye
 *
 */
public class SingleChoiceListViewActivity extends Activity {
	private ListView mListView = null;
	private LayoutInflater mLayoutInflater = null;
	private int mCurrentPosition = 0;
	private Integer[] mDrawableIds = {
			R.drawable.icon_112,R.drawable.icon_111,R.drawable.icon_110,R.drawable.icon_109,
			R.drawable.icon_108,R.drawable.icon_107,R.drawable.icon_106,R.drawable.icon_105,
			R.drawable.icon_104,R.drawable.icon_103,R.drawable.icon_102,R.drawable.icon_101,
			R.drawable.icon_100,R.drawable.icon_099,R.drawable.icon_098,R.drawable.icon_097,
			R.drawable.icon_096,R.drawable.icon_095,R.drawable.icon_094,R.drawable.icon_093,
			R.drawable.icon_092,R.drawable.icon_091,R.drawable.icon_090,R.drawable.icon_089,
			R.drawable.icon_088,R.drawable.icon_087,R.drawable.icon_086,R.drawable.icon_085,
			R.drawable.icon_084,R.drawable.icon_083,R.drawable.icon_082,R.drawable.icon_081,
	};
	private String[] mTitles = new String[mDrawableIds.length];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_singlechoicelistview);
		
		// ��ӹ����
		YouMiUtils.addAdViewByXML(this);
		
		mLayoutInflater = getLayoutInflater();
		mListView = (ListView) findViewById(R.id.lvSingleChoiceListView);
		int len = mTitles.length;
		for(int i = 0;i < len;i++){
			mTitles[i] = String.format(getResources().getString(R.string.singlechoice_item), Math.pow(i, i) + "");
		}
		mListView.setAdapter(new MyAdapter());
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPosition = position;
			}
		});
		
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			if(mDrawableIds != null){
				return mDrawableIds.length;
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if(mDrawableIds != null){
				return mDrawableIds[position];
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			/*Holder holder = null;
			if(convertView == null){
				convertView = mLayoutInflater.inflate(R.layout.item_single_choice_listview, null,false);
				holder = new Holder();
				holder.iv = (ImageView) convertView.findViewById(R.id.itemSingleChoiceImageView);
				holder.tv = (TextView) convertView.findViewById(R.id.itemSingleChoiceTextView);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			holder.iv.setImageResource(mDrawableIds[position]);
			holder.tv.setText(mTitles[position]);
			if(position == mCurrentPosition){
				holder.tv.setTextColor(Color.RED);
				convertView.setBackgroundColor(Color.BLUE);
			}*/
			convertView = mLayoutInflater.inflate(R.layout.item_single_choice_listview, null,false);
			ImageView iv = (ImageView) convertView.findViewById(R.id.itemSingleChoiceImageView);
			TextView tv = (TextView) convertView.findViewById(R.id.itemSingleChoiceTextView);
			
			iv.setImageResource(mDrawableIds[position]);
			tv.setText(mTitles[position]);
			if(position == mCurrentPosition){
				tv.setTextColor(Color.RED);
				convertView.setBackgroundColor(Color.BLUE);
			}
			return convertView;
		}
	}
	class Holder{
		public ImageView iv;
		public TextView tv ;
	}
}
