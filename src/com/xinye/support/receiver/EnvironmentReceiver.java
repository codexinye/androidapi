package com.xinye.support.receiver;

import com.xinye.support.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
/**
 * ������鹤����
 * @author Administrator
 *
 */
public class EnvironmentReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("EnvironmentReceiver", "action :" + intent.getAction());
		if(Intent.ACTION_MEDIA_EJECT.equals(intent.getAction())){		// SDCardж��
			Toast.makeText(context, R.string.sdcard_eject, Toast.LENGTH_LONG).show();
		}else if(Intent.ACTION_MEDIA_MOUNTED.equals(intent.getAction())){// SDCard���� 
			Toast.makeText(context, R.string.sdcard_mounted, Toast.LENGTH_LONG).show();
		}
	}

}
