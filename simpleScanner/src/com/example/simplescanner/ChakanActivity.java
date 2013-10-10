package com.example.simplescanner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChakanActivity extends Activity {
	private GridView grid;
	private List<String> ImageList;
	private String[] list;
	
	private String pathname=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chakan);
		ImageList = getInSDPhoto();
        list = ImageList.toArray(new String[ImageList.size()]);
        Button bn=(Button)findViewById(R.id.button_chakan);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				Intent intent = new Intent(ChakanActivity.this,MainActivity.class
						);
				// ����intent��Ӧ��Activity
				startActivity(intent);
			}
		});
        List<Map<String, Object>> listItems = 
				new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", list[i]);
			listItems.add(listItem);
		}
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
        		listItems
				// ʹ��/layout/cell.xml�ļ���Ϊ���沼��
				, R.layout.cell, new String[] { "image" },
				new int[] { R.id.image1 });
        grid=(GridView)findViewById(R.id.gridchakan);
        grid.setAdapter(simpleAdapter);
       //Ϊgrid�󶨼�������������༭ͼƬ��activity
       grid.setOnItemSelectedListener(new OnItemSelectedListener()
    	{
    		@Override
    		public void onItemSelected(AdapterView<?> parent, View view,
    				int position, long id)
    		{
    			// ��ʾ��ǰ��ѡ�е�ͼƬ
    		    File f = new File("/sdcard/myImage/");
    		    if(!f.exists()){f.mkdirs();}
    		    File[] files = f.listFiles();
    			File file = files[position];
    			pathname=file.getPath();
    			Bundle data=new Bundle();
        		data.putString("pathname",pathname);
        		Intent sintent=new Intent(ChakanActivity.this,ChaliActivity_chakan.class);
        		sintent.putExtras(data);
        		startActivity(sintent);
        		finish();
    			
    		}

    		@Override
    		public void onNothingSelected(AdapterView<?> parent)
    		{
    		}
    	});
    	// ����б�������ļ�����
    	grid.setOnItemClickListener(new OnItemClickListener()
    	{
    		@Override
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id)
    		{
    			// ��ʾ��������ͼƬ��ͼƬ
    			File f = new File("/sdcard/myImage/");
    			File[] files = f.listFiles();
    			
    			File file = files[position];
    			pathname=file.getPath();
    			Bundle data=new Bundle();
        		data.putString("pathname",pathname);
        		Intent sintent=new Intent(ChakanActivity.this,ChaliActivity_chakan.class);
        		sintent.putExtras(data);
        		startActivity(sintent);
        		finish();
    		}
    	}); 
    	
        
        
          
	}
	private List<String> getInSDPhoto() {
    	/**
		 *  �趨ͼƬ����·��
		 */
		List<String> it = new ArrayList<String>();
		
		/**
		 * �˴�С��ֱ�Ӱ�ͼƬpush����SD����·���£����������Ҫ�ŵ������ļ��£�
		 * �����ж���SD������ʱ��·��ָ�����Լ���·���¾Ϳ����ˣ����԰ɣ����
		 */
		File f = new File("/sdcard/myImage/");
		File[] files = f.listFiles();

		/**
		 *  �������ļ�����ArrayList��,����ط���Ļ����ļ�·��Ŷ
		 */
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (getAllImage(file.getPath()))
				it.add(file.getPath());
		}
		return it;
	}
    /**
	 * ��ȡ����ͼƬ�ļ���ʵ��
	 * @param fName
	 * @return
	 */
	private boolean getAllImage(String fName) {
		boolean re;

		/* ȡ����չ�� */
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		/* ����չ�������;���MimeType */
		if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			re = true;
		} else {
			re = false;
		}
		return re;
	}
	public List<String> getImageList() {
		return ImageList;
	}
	public void setImageList(List<String> imageList) {
		ImageList = imageList;
	}
	public String[] getList() {
		return list;
	}
	public void setList( final String[] list) {
		this.list = list;
	
	
	
      
	}   




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chakan, menu);
		return true;
	}

}
