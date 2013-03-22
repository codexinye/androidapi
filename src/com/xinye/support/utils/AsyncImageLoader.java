package com.xinye.support.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
/**
 * ͼƬ�첽������
 * @author
 *
 */
public class AsyncImageLoader {
	private static final String TAG = "AsyncImageLoader";

	private HashMap<String, SoftReference<Drawable>> imageCache;
	private BlockingQueue<Runnable> queue;
	private ThreadPoolExecutor executor;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
		// �̳߳أ����50����ÿ��ִ�У�1���������߳̽����ĳ�ʱʱ�䣺180��
		queue = new LinkedBlockingQueue<Runnable>();
		executor = new ThreadPoolExecutor(1, 50, 180, TimeUnit.SECONDS, queue);
	}

	public Drawable loadDrawable(final Context context, final String imageUrl,
			final ImageCallback imageCallback) {
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			if (softReference != null) {
				Drawable drawable = softReference.get();
				if (drawable != null) {
					return drawable;
				} else { // �Ѿ�������
					softReference = null;
				}
			}
		}
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
			}
		};

		// ���̳߳���������ͼƬ������
		executor.execute(new Runnable() {
			@Override
			public void run() {
				Drawable drawable = loadImageFromUrl(context, imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		});

		return null;
	}

	// ����ͼƬ�����ص�����cacheĿ¼���棬��imagUrl��ͼƬ�ļ������档�����ͬ���ļ���cacheĿ¼�ʹӱ��ؼ���
	public static Drawable loadImageFromUrl(Context context, String imageUrl) {
		Drawable drawable = null;
		if (imageUrl == null)
			return null;
//		String imagePath = "";
		String fileName = "";

		// ��ȡurl��ͼƬ���ļ������׺
		if (imageUrl != null && imageUrl.length() != 0) {
			fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
		}

		// ͼƬ���ֻ����صĴ��·��,ע�⣺fileNameΪ�յ����
		//imagePath = context.getCacheDir() + "/" + fileName;
		File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "imagescache");
		if(!dir.exists()){
			dir.mkdirs();
		}
		//File file = new File(context.getCacheDir(), fileName);// �����ļ�
		File file = new File(dir,fileName);
		Log.i("wangheng", "�ļ���" + file.getPath() + "  ----- url:" + imageUrl);
		// Log.i(TAG,"file.toString()=" + file.toString());
		if (!file.exists() && !file.isDirectory()) {
			FileOutputStream fos = null;
			InputStream is = null;
			HttpURLConnection conn = null;
			try {
				file.createNewFile();
				// ����������ͨ���ļ������жϣ��Ƿ񱾵��д�ͼƬ
				fos = new FileOutputStream(file);
				conn = (HttpURLConnection) new URL(imageUrl).openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				Log.i("wangheng", "code!!!:" + code);
				if(code == HttpURLConnection.HTTP_OK){
					is = conn.getInputStream();
					int len = -1;
					byte[] buffer = new byte[1024 * 20];
					while ((len = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
					}
					Log.i("wangheng", "stream length:" + is.available());
					drawable = Drawable.createFromPath(file.toString());
				}
				// drawable = Drawable.createFromStream(
				// new URL(imageUrl).openStream(), file.toString() ); //
				// (InputStream) new URL(imageUrl).getContent();
				// Log.i(TAG, "file.exists()���ļ����ڣ���������:" + drawable.toString());
			} catch (Exception e) {
				Log.e(TAG, e.toString() + "ͼƬ���ؼ�����ʱ�����쳣��");
			}finally{
				if(conn != null){
					conn.disconnect();
				}
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			drawable = Drawable.createFromPath(file.toString());
			// Log.i("test", "file.exists()�ļ����ڣ����ػ�ȡ");
		}
		return drawable;
	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

}
