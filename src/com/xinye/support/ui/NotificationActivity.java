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
 * 状态栏通知
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
		// 初始化有米SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_notification);

		// 添加广告条
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
	 * 取消所有的通知
	 */
	private void testCancelAllNotification() {
		if(mNotificationManager != null){
			mNotificationManager.cancelAll();
		}
	}
	/**
	 * 取消测试1通知
	 */
	private void test01CancelNotification() {
		if(mNotificationManager != null){
			mNotificationManager.cancel(ID_TEST_01);
		}
	}

	/**
	 * 测试通知
	 */
	private void test01Notification() {
		Random random = new Random();
		int intNum = random.nextInt(100);
		// 2. 初始化Notification
		int icon = R.drawable.first;
		String tickerText = "测试的第一个Notification:" + intNum;
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		// 3. 定义通知的PendingIntent
		Context context = getApplicationContext();
		Intent intent = new Intent(getApplication(),NotificationActivity.class);
		String contentTitle = "content title:" + intNum;
		String contentText = "content text content text content text:" + intNum;
		PendingIntent pi = PendingIntent.getActivity(context, REQUEST_TEST_01,intent , 0);
		/**添加声音
		 * 说明：如果你希望提示音可以反复的播放，直到用户对通知做出了反应或者通知被取消了，可以把FLAG_INSISTENT赋值给flags属性。 
		 * 注意：如果defaults属性的值是DEFAULT_SOUND,那么无论设置什么声音都不会有效果的，仍只会播放默认的声音。 
		 */
		notification.defaults |= Notification.DEFAULT_SOUND;				// 默认声音
		// notification.sound = Uri.parse("file:///sdcard/0128/越单纯越幸福.mp3");	// 来自文件
		// notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");	// 来自媒体库
		notification.flags = Notification.FLAG_INSISTENT;
		/**
		 * 添加震动
		 * 说明：
		 * 		震动的时候必须添加权限<uses-permission android:name="android.permission.VIBRATE"/>，否则会报错
		 * 自定义的时候，long型的数组定义了交替振动的方式和振动的时间（毫秒）。
		 * 			第一个值是指振动前的准备（间歇）时间，
		 * 			第二个值是第一次振动的时间，
		 * 			第三个值又是间歇的时间，以此类推。振动的方式任你设定。但是不能够反复不停。 
		 * 注意：如果defaults属性的值是DEFAULT_VIBRATE,那么无论设置什么振动都不会有效果的，仍只会以默认的方式振动。 
		 * 
		 */
		// notification.defaults |= Notification.DEFAULT_VIBRATE;
		long[] vibrate = {0,100,200,300};
		notification.vibrate = vibrate;
		/**
		 * 添加闪烁
		 * 说明：可以自定义灯光的颜色和闪动的方式。
		 * 		ledARGB属性是定义颜色的，
		 * 		ledOffMS属性是定义灯光关闭的时间（毫秒），
		 * 		ledOnMs是灯光打开的时间（毫秒），
		 * 		还要给flags属性赋值为FLAG_SHOW_LIGHTS 
			notification.ledARGB = 0xff00ff00;
			notification.ledOnMS = 300;
			notification.ledOffMS = 1000;
			notification.flags |= Notification.FLAG_SHOW_LIGHTS;
			 上面的例子中，绿色的灯亮了300毫秒，暗了1秒，，，，如此循环。 
		 * 		
		 */
		
		// notification.defaults |= Notification.DEFAULT_LIGHTS;
		notification.ledARGB = 0xff00ff00;
		notification.ledOnMS = 300;
		notification.ledOffMS = 1000;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		/**
		 * 更多其它特性
		 * 你可以使用Notification和标志（flags）来为自己的通知做定制。下面是一些有用的特性： 
		 * FLAG_AUTO_CANCEL标志 ：使用这个标志可以让在通知被选择之后，通知提示会自动的消失。 
		 * FLAG_INSISTENT标志：使用这个标志，可以让提示音循环播放，知道用户响应。 
		 * FLAG_ONGOING_EVENT标志：使用这个标记，可以让该通知成为正在运行的应用的通知。
		 * 			 这说明应用还在运行-它的进程还跑在后台，即使是当应用在前台不可见（就像音乐播放和电话通话）。
		 * FLAG_NO_CLEAR标志：使用这个标志，说明通知必须被清除，通过"Clear notifications"按钮。
		 * 			如果你的应用还在运行，那么这个就非常有用了。
		 * number属性：这个属性的值，指出了当前通知的数量。这个数字是显示在状态通知的图标上的。
		 *			如果你想使用这个属性，那么当第一个通知被创建的时候，它的值要从1开始。而不是零。
		 * iconLevel：这个属性的值，指出了LevelListDrawable的当前水平。
		 *			你可以通过改变它的值与LevelListDrawable定义的drawable相关联，从而实现通知图标在状态栏上的动画。
		 *			查看LevelListDrawable可以获得更多信息。 
		 */
		notification.setLatestEventInfo(context, contentTitle, contentText, pi);
		// 4. 发送给NotificationManager
		mNotificationManager.notify(ID_TEST_01, notification);
	}
	/** 自定义布局
	 * 默认的，通知窗口里的通知会包括标题和消息文本两部分。
	 * 它们是通过setLatestEventInfo()定义了contentTitle和contentText来实现的。
	 * 然而，你也可以使用RemoteViews为通知界面定义一个布局。它看起来与默认的布局很像，但实际上是由XML创建的。 
	 * 为了自定义通知的布局，首先实例化RemoteViews创建一个布局文件，然后把RemoteViews传递给Notification的contentView属性。
	 * 通过下面的例子可以更好的理解：
	 * 1. 创建一个XML布局文件 custom_notification.xml: 
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
 		注意那两个TextView的style属性。在定制的通知界面中，为文本使用style文件进行定义是很重要的，
 			因为通知界面的背景色会因为不同的硬件，不同的os版本而改变。从android2.3(API 9)开始，
 			系统为默认的通知界面定义了文本的style属性。因此，你应该使用style属性，
 			以便于在android2.3或更高的版本上可以清晰地显示你的文本，而不被背景色干扰。 
		例如，在低于android2.3的版本中使用标准文本颜色，应该使用如下的文件res/values/styles.xml: 
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
 		然后，在高于android2.3的系统中使用系统默认的颜色，如下文件res/values-v9/styles.xml: 
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
			    <style name="NotificationText" parent="android:TextAppearance.StatusBar.EventContent" />
			    <style name="NotificationTitle" parent="android:TextAppearance.StatusBar.EventContent.Title" />
			</resources>
 		现在，当运行在2.3版本以上时，在你的定制界面中，文本都会是同一种颜色-系统为默认通知界面定义的颜色。
 		这很重要，能保证你的文本颜色是高亮的，即使背景色是意料之外的颜色，你的文本页也会作出适当的改变。 
		2. 现在，在应用的代码中，使用RemoveViews方法定义了图片和文本。然后把RemoveViews对象传给contentView属性。例子如下： 
			RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
			contentView.setImageViewResource(R.id.image, R.drawable.notification_image);
			contentView.setTextViewText(R.id.title, "Custom notification");
			contentView.setTextViewText(R.id.text, "This is a custom layout");
			notification.contentView = contentView;
 		如上所示，把应用的包名和布局文件的ID传给RemoteViews的构造器。
 			然后分别使用setImageViewResource()和setTextViewText()定义ImageView和TextView的内容。
 			最后，把RemoteViews对象传递给通知的contentView属性。 
		3.因为当你在使用定制界面时，不需要使用setLatestEventInfo(),你必须为通知定义一个intent，
			并赋值给contentIntent属性，如下代码： 
			Intent notificationIntent = new Intent(this, MyClass.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.contentIntent = contentIntent;
 		4.发送通知： 
			mNotificationManager.notify(CUSTOM_VIEW_ID, notification);
 		RemoteViews类还包括一些方法可以让你轻松地在通知界面的布局里添加Chronometer和ProgressBar。
 			如果要为你的通知界面做更多的定制，请参考RemoteViews。 
		特别注意：当创建一个自定义通知布局，你必须特别小心，以确保布局能地在不同的设备上适当地显示。
		而这个建议适用于所有视图布局创建，不单单是通知界面布局，在这种情况下（通知界面）尤其重要，因为是布局的空间是非常有限的。
		所以不要让你的自定义布局太复杂并确报在不同的配置上测试它。
	 */
}
