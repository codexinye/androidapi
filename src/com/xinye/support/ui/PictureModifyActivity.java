package com.xinye.support.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import net.youmi.android.AdView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.Time;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
import com.xinye.support.utils.images.BitmapCreator;
import com.xinye.support.utils.images.CastBitmapCreator;
import com.xinye.support.utils.images.ComicBitmapCreator;
import com.xinye.support.utils.images.EdgeBitmapCreator;
import com.xinye.support.utils.images.GrayBitmapCreator;
import com.xinye.support.utils.images.IceBitmapCreator;
import com.xinye.support.utils.images.NostalgicBitmapCreator;
import com.xinye.support.utils.images.PencliBitmapCreator;
import com.xinye.support.utils.images.ReliefBitmapCreator;
/**
 * 图片修改
 * @author Administrator
 * 
 */
public class PictureModifyActivity extends Activity {
	private BitmapCreator mBitmapCreator = null;
	private int mDrawables[] = {
			R.drawable.pre0,R.drawable.pre1,
			R.drawable.pre2,R.drawable.pre3,
			R.drawable.pre4,R.drawable.pre5,
			R.drawable.pre6,R.drawable.pre7,
			R.drawable.pre8,R.drawable.pre9,
			R.drawable.pre10
			};
	private Timer mTimer = null;
	private Handler mHandler = new Handler();
	private ImageView mImageView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		LinearLayout layout=new LinearLayout(this); 
 		layout.setOrientation(LinearLayout.VERTICAL); 
 		layout.setBackgroundResource(R.drawable.background);
 
 
 		//初始化广告视图，可以使用其他的构造函数设置广告视图的背景色、透明度及字体颜色
 		AdView adView = new AdView(this); 
 		LinearLayout.LayoutParams params01 = new LinearLayout.LayoutParams(
 				LinearLayout.LayoutParams.MATCH_PARENT, 
 				LinearLayout.LayoutParams.WRAP_CONTENT);		
 		layout.addView(adView, params01);

		mImageView = new ImageView(this);
		LinearLayout.LayoutParams params02 = new LinearLayout.LayoutParams(
 				LinearLayout.LayoutParams.MATCH_PARENT, 
 				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.addView(mImageView, params02);
		
		setContentView(layout);
	}
	@Override
	protected void onStart() {
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						Random random = new Random();
						int index = random.nextInt(9);
						int index02 = random.nextInt(11);
						Bitmap bm = BitmapFactory.decodeResource(getResources(), mDrawables[index02]);
						Bitmap bitmap = null;
						switch(index){
							case 0:{
								bitmap = createPencli(bm);
							}break;
							case 1:{
								bitmap = createGray(bm);
							}break;
							case 2:{
								bitmap = createNostalgic(bm);
							}break;
							case 3:{
								bitmap = createIce(bm);
							}break;
							case 4:{
								bitmap = createEclosion(bm);
							}break;
							case 5:{
								bitmap = createComic(bm);
							}break;
							case 6:{
								bitmap = createEdge(bm);
							}break;
							case 7:{
								bitmap = createCast(bm);
							}break;
							case 8:{
								bitmap = createRelief(bm);
							}break;
						}
						mImageView.setImageBitmap(bitmap);
					}
				});
			}
		}, 0, 2000);
		super.onStart();
	}
	@Override
	protected void onStop() {
		if(mTimer != null){
			mTimer.cancel();
			mTimer = null;
		}
		super.onStop();
	}
	private Bitmap createRelief(Bitmap bm) {
		mBitmapCreator = new ReliefBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "relief_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createCast(Bitmap bm) {
		mBitmapCreator = new CastBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "cast_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createEdge(Bitmap bm) {
		mBitmapCreator = new EdgeBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "edge_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createComic(Bitmap bm) {
		mBitmapCreator = new ComicBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "comic_" + new Time() + ".jpg");
		return bitmap;
	}
	private Bitmap createEclosion(Bitmap bm) {
		mBitmapCreator = new IceBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "eclosion_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createIce(Bitmap bm) {
		mBitmapCreator = new IceBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "ice_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createNostalgic(Bitmap bm) {
		mBitmapCreator = new NostalgicBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "nostalgic_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createGray(Bitmap bm) {
		mBitmapCreator = new GrayBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "gray_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private Bitmap createPencli(Bitmap bm) {
		mBitmapCreator = new PencliBitmapCreator();
		Bitmap bitmap = mBitmapCreator.createBitmap(bm);
		saveBitmap(bitmap, "pencli_" + UUID.randomUUID() + ".jpg");
		return bitmap;
	}
	private void saveBitmap(Bitmap bitmap,String name){
		File file = new File(Environment.getExternalStorageDirectory(), name); 
		try {
			if(file != null && !file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}