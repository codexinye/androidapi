package com.xinye.support.ui;

import java.util.Random;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * ״̬��֪ͨ
 * @author Administrator
 *
 */
public class NotificationActivity extends Activity implements OnClickListener{
	private Button mNotificationTest01Button = null;
	private Button mNotificationTest01CancelButton = null;
	private Button mNotificationCancelAllButton = null;
	private static final int REQUEST_TEST_01 = 0X01;
	private static final int ID_TEST_01 = 0x01;
	private NotificationManager mNotificationManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_notification);

		// ��ӹ����
		YouMiUtils.addAdViewByXML(this);
		
		init();
	}

	private void init() {
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		mNotificationTest01Button = (Button) findViewById(R.id.notificationTest1Button);
		mNotificationTest01Button.setOnClickListener(NotificationActivity.this);
		
		mNotificationTest01CancelButton = (Button) findViewById(R.id.notificationTest1cancelButton);
		mNotificationTest01CancelButton.setOnClickListener(NotificationActivity.this);
		
		mNotificationCancelAllButton = (Button) findViewById(R.id.notificationCancelAllButton);
		mNotificationCancelAllButton.setOnClickListener(NotificationActivity.this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.notificationTest1Button:{
				test01Notification();
			} break;
			case R.id.notificationTest1cancelButton:{
				test01CancelNotification();
			} break;
			case R.id.notificationCancelAllButton:{
				testCancelAllNotification();
			} break;
		}
	}
	/**
	 * ȡ�����е�֪ͨ
	 */
	private void testCancelAllNotification() {
		if(mNotificationManager != null){
			mNotificationManager.cancelAll();
		}
	}
	/**
	 * ȡ������1֪ͨ
	 */
	private void test01CancelNotification() {
		if(mNotificationManager != null){
			mNotificationManager.cancel(ID_TEST_01);
		}
	}

	/**
	 * ����֪ͨ
	 */
	private void test01Notification() {
		Random random = new Random();
		int intNum = random.nextInt(100);
		// 2. ��ʼ��Notification
		int icon = R.drawable.first;
		String tickerText = "���Եĵ�һ��Notification:" + intNum;
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		// 3. ����֪ͨ��PendingIntent
		Context context = getApplicationContext();
		Intent intent = new Intent(getApplication(),NotificationActivity.class);
		String contentTitle = "content title:" + intNum;
		String contentText = "content text content text content text:" + intNum;
		PendingIntent pi = PendingIntent.getActivity(context, REQUEST_TEST_01,intent , 0);
		/**�������
		 * ˵���������ϣ����ʾ�����Է����Ĳ��ţ�ֱ���û���֪ͨ�����˷�Ӧ����֪ͨ��ȡ���ˣ����԰�FLAG_INSISTENT��ֵ��flags���ԡ� 
		 * ע�⣺���defaults���Ե�ֵ��DEFAULT_SOUND,��ô��������ʲô������������Ч���ģ���ֻ�Ქ��Ĭ�ϵ������� 
		 */
		notification.defaults |= Notification.DEFAULT_SOUND;				// Ĭ������
		// notification.sound = Uri.parse("file:///sdcard/0128/Խ����Խ�Ҹ�.mp3");	// �����ļ�
		// notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");	// ����ý���
		notification.flags = Notification.FLAG_INSISTENT;
		/**
		 * �����
		 * ˵����
		 * 		�𶯵�ʱ��������Ȩ��<uses-permission android:name="android.permission.VIBRATE"/>������ᱨ��
		 * �Զ����ʱ��long�͵����鶨���˽����񶯵ķ�ʽ���񶯵�ʱ�䣨���룩��
		 * 			��һ��ֵ��ָ��ǰ��׼������Ъ��ʱ�䣬
		 * 			�ڶ���ֵ�ǵ�һ���񶯵�ʱ�䣬
		 * 			������ֵ���Ǽ�Ъ��ʱ�䣬�Դ����ơ��񶯵ķ�ʽ�����趨�����ǲ��ܹ�������ͣ�� 
		 * ע�⣺���defaults���Ե�ֵ��DEFAULT_VIBRATE,��ô��������ʲô�񶯶�������Ч���ģ���ֻ����Ĭ�ϵķ�ʽ�񶯡� 
		 * 
		 */
		// notification.defaults |= Notification.DEFAULT_VIBRATE;
		long[] vibrate = {0,100,200,300};
		notification.vibrate = vibrate;
		/**
		 * �����˸
		 * ˵���������Զ���ƹ����ɫ�������ķ�ʽ��
		 * 		ledARGB�����Ƕ�����ɫ�ģ�
		 * 		ledOffMS�����Ƕ���ƹ�رյ�ʱ�䣨���룩��
		 * 		ledOnMs�ǵƹ�򿪵�ʱ�䣨���룩��
		 * 		��Ҫ��flags���Ը�ֵΪFLAG_SHOW_LIGHTS 
			notification.ledARGB = 0xff00ff00;
			notification.ledOnMS = 300;
			notification.ledOffMS = 1000;
			notification.flags |= Notification.FLAG_SHOW_LIGHTS;
			 ����������У���ɫ�ĵ�����300���룬����1�룬���������ѭ���� 
		 * 		
		 */
		
		// notification.defaults |= Notification.DEFAULT_LIGHTS;
		notification.ledARGB = 0xff00ff00;
		notification.ledOnMS = 300;
		notification.ledOffMS = 1000;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		/**
		 * ������������
		 * �����ʹ��Notification�ͱ�־��flags����Ϊ�Լ���֪ͨ�����ơ�������һЩ���õ����ԣ� 
		 * FLAG_AUTO_CANCEL��־ ��ʹ�������־��������֪ͨ��ѡ��֮��֪ͨ��ʾ���Զ�����ʧ�� 
		 * FLAG_INSISTENT��־��ʹ�������־����������ʾ��ѭ�����ţ�֪���û���Ӧ�� 
		 * FLAG_ONGOING_EVENT��־��ʹ�������ǣ������ø�֪ͨ��Ϊ�������е�Ӧ�õ�֪ͨ��
		 * 			 ��˵��Ӧ�û�������-���Ľ��̻����ں�̨����ʹ�ǵ�Ӧ����ǰ̨���ɼ����������ֲ��ź͵绰ͨ������
		 * FLAG_NO_CLEAR��־��ʹ�������־��˵��֪ͨ���뱻�����ͨ��"Clear notifications"��ť��
		 * 			������Ӧ�û������У���ô����ͷǳ������ˡ�
		 * number���ԣ�������Ե�ֵ��ָ���˵�ǰ֪ͨ�������������������ʾ��״̬֪ͨ��ͼ���ϵġ�
		 *			�������ʹ��������ԣ���ô����һ��֪ͨ��������ʱ������ֵҪ��1��ʼ���������㡣
		 * iconLevel��������Ե�ֵ��ָ����LevelListDrawable�ĵ�ǰˮƽ��
		 *			�����ͨ���ı�����ֵ��LevelListDrawable�����drawable��������Ӷ�ʵ��֪ͨͼ����״̬���ϵĶ�����
		 *			�鿴LevelListDrawable���Ի�ø�����Ϣ�� 
		 */
		notification.setLatestEventInfo(context, contentTitle, contentText, pi);
		// 4. ���͸�NotificationManager
		mNotificationManager.notify(ID_TEST_01, notification);
	}
	/** �Զ��岼��
	 * Ĭ�ϵģ�֪ͨ�������֪ͨ������������Ϣ�ı������֡�
	 * ������ͨ��setLatestEventInfo()������contentTitle��contentText��ʵ�ֵġ�
	 * Ȼ������Ҳ����ʹ��RemoteViewsΪ֪ͨ���涨��һ�����֡�����������Ĭ�ϵĲ��ֺ��񣬵�ʵ��������XML�����ġ� 
	 * Ϊ���Զ���֪ͨ�Ĳ��֣�����ʵ����RemoteViews����һ�������ļ���Ȼ���RemoteViews���ݸ�Notification��contentView���ԡ�
	 * ͨ����������ӿ��Ը��õ���⣺
	 * 1. ����һ��XML�����ļ� custom_notification.xml: 
			<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    android:id="@+id/layout"
			    android:layout_width="fill_parent"
			    android:layout_height="fill_parent"
			    android:padding="10dp" >
			    <ImageView android:id="@+id/image"
			        android:layout_width="wrap_content"
			        android:layout_height="fill_parent"
			        android:layout_alignParentLeft="true"
			        android:layout_marginRight="10dp" />
			    <TextView android:id="@+id/title"
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:layout_toRightOf="@id/image"
			        style="@style/NotificationTitle" />
			    <TextView android:id="@+id/text"
			        android:layout_width="wrap_content"
			        android:layout_height="wrap_content"
			        android:layout_toRightOf="@id/image"
			        android:layout_below="@id/title"
			        style="@style/NotificationText" />
			</RelativeLayout>
 		ע��������TextView��style���ԡ��ڶ��Ƶ�֪ͨ�����У�Ϊ�ı�ʹ��style�ļ����ж����Ǻ���Ҫ�ģ�
 			��Ϊ֪ͨ����ı���ɫ����Ϊ��ͬ��Ӳ������ͬ��os�汾���ı䡣��android2.3(API 9)��ʼ��
 			ϵͳΪĬ�ϵ�֪ͨ���涨�����ı���style���ԡ���ˣ���Ӧ��ʹ��style���ԣ�
 			�Ա�����android2.3����ߵİ汾�Ͽ�����������ʾ����ı�������������ɫ���š� 
		���磬�ڵ���android2.3�İ汾��ʹ�ñ�׼�ı���ɫ��Ӧ��ʹ�����µ��ļ�res/values/styles.xml: 
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
			    <style name="NotificationText">
			      <item name="android:textColor">?android:attr/textColorPrimary</item>
			    </style>
			    <style name="NotificationTitle">
			      <item name="android:textColor">?android:attr/textColorPrimary</item>
			      <item name="android:textStyle">bold</item>
			    </style>
			    <!-- If you want a slightly different color for some text,
			         consider using ?android:attr/textColorSecondary -->
			</resources>
 		Ȼ���ڸ���android2.3��ϵͳ��ʹ��ϵͳĬ�ϵ���ɫ�������ļ�res/values-v9/styles.xml: 
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
			    <style name="NotificationText" parent="android:TextAppearance.StatusBar.EventContent" />
			    <style name="NotificationTitle" parent="android:TextAppearance.StatusBar.EventContent.Title" />
			</resources>
 		���ڣ���������2.3�汾����ʱ������Ķ��ƽ����У��ı�������ͬһ����ɫ-ϵͳΪĬ��֪ͨ���涨�����ɫ��
 		�����Ҫ���ܱ�֤����ı���ɫ�Ǹ����ģ���ʹ����ɫ������֮�����ɫ������ı�ҳҲ�������ʵ��ĸı䡣 
		2. ���ڣ���Ӧ�õĴ����У�ʹ��RemoveViews����������ͼƬ���ı���Ȼ���RemoveViews���󴫸�contentView���ԡ��������£� 
			RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
			contentView.setImageViewResource(R.id.image, R.drawable.notification_image);
			contentView.setTextViewText(R.id.title, "Custom notification");
			contentView.setTextViewText(R.id.text, "This is a custom layout");
			notification.contentView = contentView;
 		������ʾ����Ӧ�õİ����Ͳ����ļ���ID����RemoteViews�Ĺ�������
 			Ȼ��ֱ�ʹ��setImageViewResource()��setTextViewText()����ImageView��TextView�����ݡ�
 			��󣬰�RemoteViews���󴫵ݸ�֪ͨ��contentView���ԡ� 
		3.��Ϊ������ʹ�ö��ƽ���ʱ������Ҫʹ��setLatestEventInfo(),�����Ϊ֪ͨ����һ��intent��
			����ֵ��contentIntent���ԣ����´��룺 
			Intent notificationIntent = new Intent(this, MyClass.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.contentIntent = contentIntent;
 		4.����֪ͨ�� 
			mNotificationManager.notify(CUSTOM_VIEW_ID, notification);
 		RemoteViews�໹����һЩ���������������ɵ���֪ͨ����Ĳ��������Chronometer��ProgressBar��
 			���ҪΪ���֪ͨ����������Ķ��ƣ���ο�RemoteViews�� 
		�ر�ע�⣺������һ���Զ���֪ͨ���֣�������ر�С�ģ���ȷ�������ܵ��ڲ�ͬ���豸���ʵ�����ʾ��
		���������������������ͼ���ִ�������������֪ͨ���沼�֣�����������£�֪ͨ���棩������Ҫ����Ϊ�ǲ��ֵĿռ��Ƿǳ����޵ġ�
		���Բ�Ҫ������Զ��岼��̫���Ӳ�ȷ���ڲ�ͬ�������ϲ�������
	 */
}
