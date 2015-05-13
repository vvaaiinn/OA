package com.oa;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

public class MySimpleAdapter extends SimpleAdapter {
	
	private List<? extends Map<String, ?>> list;  
	
	public MySimpleAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {		
		super(context, data, resource, from, to);
		this.list = data;
		// TODO 自动生成的构造函数存根
	}
	
	@Override  
	public int getCount() {  
	// TODO Auto-generated method stub  
	return list.size();  
	}  
	  
	@Override  
	public Object getItem(int position) {  
	// TODO Auto-generated method stub  
	return list.get(position);  
	}  
	  
	@Override  
	public long getItemId(int position) {  
	// TODO Auto-generated method stub  
	return position;  
	}  
	public void setItem()
	{
		for(int i = 0 ;i < list.size();i++)
		{
			
		}
	}	

}
