package com.xinye.support.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.xinye.support.R;
import com.xinye.support.utils.YouMiUtils;
/**
 * ͼ�Ļ���
 * @author Administrator
 *
 */
public class EditTextActivity extends Activity implements OnClickListener{
	private static final int REQUEST_GET_IMAGE = 0x01;
	private EditText mEditText = null;
	private Button mButton = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������SDK
		YouMiUtils.initSDK(this);
		
		setContentView(R.layout.activity_edittext);
		// ��ӹ����
		YouMiUtils.addAdViewByXML(this);
				
		mEditText = (EditText) findViewById(R.id.edittextEditText);
		mButton = (Button) findViewById(R.id.edittextButton);
		mButton.setOnClickListener(EditTextActivity.this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		//intent.setType("image/*");// ͼƬ
		//intent.setType("audio/*"); // ��Ƶ
		//intent.setType("video/*"); // ��Ƶ (mp4,3gp)
		//intent.setType("video/*;image/*"); // ͬʱѡ����Ƶ��ͼƬ
		startActivityForResult(intent, REQUEST_GET_IMAGE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK){
			if(requestCode == REQUEST_GET_IMAGE){
				Uri uri = data.getData();
				/*ContentResolver resolver = getContentResolver();
				Cursor cursor = resolver.query(uri, null, null, null, null);
				cursor.moveToFirst();
				 // String imgNo = cursor.getString(0); // ͼƬ���
			    String imgPath = cursor.getString(1); // ͼƬ�ļ�·��
			    String imgSize = cursor.getString(2); // ͼƬ��С
			    String imgName = cursor.getString(3); // ͼƬ�ļ���*/
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
					Matrix matrix = new Matrix();
					matrix.setScale((float)300 / bitmap.getWidth(), (float)400 / bitmap.getHeight());
					Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
					//Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, 300, 400);
					//Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first);
					ImageSpan is = new ImageSpan(b);
					SpannableString ss = new SpannableString("image");
					ss.setSpan(is, 0, "image".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					mEditText.append(ss);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
