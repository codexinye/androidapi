package com.xinye.support.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.xinye.support.R;
import com.xinye.support.adapter.MainGridViewAdapter;
import com.xinye.support.utils.YouMiUtils;
/**
 * 主界面
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	public static final String KEY_ITEM_TITLE = "title";				// 标题的KEY
	public static final String KEY_ITEM_IMAGE = "image";				// 图片的KEY
	private static final int[] mGridViewItemTitleId = {					// 条目标题
		R.string.item_title_viewpager_main,
		R.string.item_title_gestureDetector_main,
		R.string.item_title_fragment_main,
		R.string.item_title_edittext_main,
		R.string.item_title_manifest_main,
		R.string.item_title_multiplemedia_mian,
		R.string.item_title_notification_main,
		R.string.item_title_drag_main,
		R.string.item_title_popupwindow_main,
		R.string.item_title_asyncload_main,
		R.string.item_title_gallery_main,
		R.string.item_title_download_main,
		R.string.item_title_tabhost_main,
		R.string.item_title_picturemodify_main,
		R.string.item_title_youku_main,
		R.string.item_title_activitygroup_main,
		R.string.item_title_countdowntimer_main,
		R.string.item_title_gallay_gridview_main,
		R.string.item_title_singlechoice_main
		};
	private static final int[] mGridViewItemImageId = {					// 条目图片
		R.drawable.icon_000,
		R.drawable.icon_001,
		R.drawable.icon_002,
		R.drawable.icon_003,
		R.drawable.icon_004,
		R.drawable.icon_005,
		R.drawable.icon_006,
		R.drawable.icon_007,
		R.drawable.icon_008,
		R.drawable.icon_009,
		R.drawable.icon_010,
		R.drawable.icon_011,
		R.drawable.icon_012,
		R.drawable.icon_013,
		R.drawable.icon_014,
		R.drawable.icon_015,
		R.drawable.icon_016,
		R.drawable.icon_017,
		R.drawable.icon_018
		};
	private ArrayList<HashMap<String, Integer>> mGridViewItemList = null;// 条目的数据
	private GridView mGridView = null;
	private MainGridViewAdapter mAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_main);
		// 添加广告条
		YouMiUtils.addAdViewByXML(this);
		
		initData();
		mGridView = (GridView) findViewById(R.id.mainGridView);
		mAdapter = new MainGridViewAdapter(MainActivity.this,mGridViewItemList);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new MyOnItemClickListener());
	}
	/*
	 * 初始化数据
	 */
	private void initData() {
		/*ArrayList<Integer> drawables = new ArrayList<Integer>();
		Class<R.drawable> clazz = com.xinye.support.R.drawable.class;
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			if(field.getName().startsWith("icon_")){
				try {
					drawables.add(field.getInt(clazz));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Log.i("wangheng", drawables + "\n length:" + drawables.size());*/
		
		if(mGridViewItemImageId.length != mGridViewItemTitleId.length){
			return;
		}
		mGridViewItemList = new ArrayList<HashMap<String, Integer>>();
		int len = mGridViewItemImageId.length;
		HashMap<String, Integer> map = null;
		for(int i = 0;i < len;i++){
			map = new HashMap<String, Integer>();
			map.put(KEY_ITEM_TITLE, mGridViewItemTitleId[i]);
			map.put(KEY_ITEM_IMAGE, mGridViewItemImageId[i]);
			mGridViewItemList.add(map);
		}
	}
	private class MyOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			if(position == 0){
				MainActivity.this.startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
			}else if(position == 1){
				MainActivity.this.startActivity(new Intent(MainActivity.this,GestureDetectorActivity.class));
			}else if(position == 2){
				MainActivity.this.startActivity(new Intent(MainActivity.this,MyFragmentActivity.class));
			}else if(position == 3){
				MainActivity.this.startActivity(new Intent(MainActivity.this,EditTextActivity.class));
			}else if(position == 4){
				MainActivity.this.startActivity(new Intent(MainActivity.this,ManifestActivity.class));
			}else if(position == 5){
				MainActivity.this.startActivity(new Intent(MainActivity.this,MultipleMediaActivity.class));
			}else if(position == 6){
				MainActivity.this.startActivity(new Intent(MainActivity.this,NotificationActivity.class));
			}else if(position == 7){
				MainActivity.this.startActivity(new Intent(MainActivity.this,DragActivity.class));
			}else if(position == 8){
				MainActivity.this.startActivity(new Intent(MainActivity.this,PopupWindowActivity.class));
			}else if(position == 9){
				MainActivity.this.startActivity(new Intent(MainActivity.this,AsyncLoadActivity.class));
			}else if(position == 10){
				MainActivity.this.startActivity(new Intent(MainActivity.this,GalleryTestActivity.class));
			}else if(position == 11){
				MainActivity.this.startActivity(new Intent(MainActivity.this,MultipleThreadActivity.class));
			}else if(position == 12){
				MainActivity.this.startActivity(new Intent(MainActivity.this,MyTabHostActivity.class));
			}else if(position == 13){
				MainActivity.this.startActivity(new Intent(MainActivity.this,PictureModifyActivity.class));
			}else if(position == 14){
				MainActivity.this.startActivity(new Intent(MainActivity.this,YouKuMenuActivity.class));
			}else if(position == 15){
				MainActivity.this.startActivity(new Intent(MainActivity.this,MyActivityGroupActivity.class));
			}else if(position == 16){
				MainActivity.this.startActivity(new Intent(MainActivity.this,CountDownTimerActivity.class));
			}else if(position == 17){
				MainActivity.this.startActivity(new Intent(MainActivity.this,GalleryAndGridViewActivity.class));
			}else if(position == 18){
				MainActivity.this.startActivity(new Intent(MainActivity.this,SingleChoiceListViewActivity.class));
			}
		}
	}
}
