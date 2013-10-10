package com.example.simplescanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;



import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity_heibai extends Activity {
	private String name;
	private String fileName;
	private Bitmap scbitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_activity_heibai);
		Button qidong = (Button) findViewById(R.id.qidongxiangji_heibai);
        Button xiayibu=(Button) findViewById(R.id.xiayibu_heibai);
        xiayibu.setOnClickListener(new SClickListener2());
       qidong.setOnClickListener(new OnClickListener3());
       Button bn=(Button)findViewById(R.id.button_camera_heibai);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				Intent intent = new Intent(CameraActivity_heibai.this,MainActivity.class
						);
				// ����intent��Ӧ��Activity
				startActivity(intent);
			}
		});}
	 class OnClickListener3 implements View.OnClickListener{
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(intent, 1);
         }
	}
	 class SClickListener2 implements View.OnClickListener{
         @Override
         public void onClick(View v) {
         	String pathname=fileName;
         	if(pathname==null){
         		Toast toast=Toast.makeText(CameraActivity_heibai.this,"��������",Toast.LENGTH_SHORT);
         		toast.show();
         		}
         	else
         	{
         		Bundle data=new Bundle();
         		data.putString("pathname",pathname);
         		Intent sintent=new Intent(CameraActivity_heibai.this,ChaliActivity_heibai.class);
         		sintent.putExtras(data);
         		startActivity(sintent);
         		finish();
         	}
         }
        }
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        if (resultCode == Activity.RESULT_OK) {

	        	String sdStatus = Environment.getExternalStorageState();
	            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
	                Log.v("TestFile",
	                        "SD card is not avaiable/writeable right now.");
	                return;
	            }
	             name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
	            Bundle bundle = data.getExtras();
	            Bitmap bitmap = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ
	           // scbitmap = sharpenEffect(bitmap);
				
	            FileOutputStream b = null;
	            File file = new File("/sdcard/myImage/");
	            file.mkdirs();// �����ļ���
	            fileName = "/sdcard/myImage/"+name;

	            try {
	                b = new FileOutputStream(fileName);
	                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    b.flush();
	                    b.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }

	            ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// ��ͼƬ��ʾ��ImageView��
	        }
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_activity_heibai, menu);
		return true;
	}

}
