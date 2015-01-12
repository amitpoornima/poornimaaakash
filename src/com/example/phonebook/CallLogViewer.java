package com.example.phonebook;

import java.nio.Buffer;
import java.util.ArrayList;

import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CallLogViewer extends Activity implements OnClickListener
{
	ListView list;
	Button btn;
	ArrayList<StringBuffer> emplist;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_log_viewer);
		list=(ListView)findViewById(R.id.empList);
		btn=(Button)findViewById(R.id.backToMain);
		btn.setOnClickListener(this);
		Log.i("logviewer", "onCreate Of logviewer is completed");
		//ArrayAdapter<StringBuffer> adapter=new ArrayAdapter<StringBuffer>(this,R.layout.emplist,R.id.calllog_detail,emplist);
		//list.setAdapter(adapter);
		registerForContextMenu(list);
		
	}
	 @Override 
	 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	 {
	    super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Do you want to delete this calllog content");
		menu.add(0,v.getId(),0,"Delete");
		menu.add(0,v.getId(),0,"Cancel");
	 }
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		
		if(item.getTitle()=="Delete")
		{
			Intent intent=new Intent(this,DeleteContact.class);
			startActivity(intent);
		}
		else if(item.getTitle()=="Cancel")
		{
			
		}
		return true;
	
	}
	@Override
	protected void onStart()
	{
		super.onStart();
		Log.i("logviewer", "obtaining provider...");
		
		//step-1: get the reference of ContentResolver class
		
		ContentResolver resolver=getContentResolver();
		Log.i("logviewer", "resolver obtained executing query...");
		
		
		//step-2: get the reference of Cursor class
		
		Cursor cursor=resolver.query(CallLog.Calls.CONTENT_URI,null,null,null,null);
		Log.i("logviewer", "cursor obtained.");
		
		
		//step-3: get the counting of records in cursor 
		
		int records=cursor.getCount();
		
		String callArray[]=new String[records];
		
		//step-4: getting index no of column
		
		int numberIndex=cursor.getColumnIndex(CallLog.Calls.NUMBER);
		int nameIndex=cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
		int typeIndex=cursor.getColumnIndex(CallLog.Calls.TYPE);
		int callDuration=cursor.getColumnIndex(CallLog.Calls.DURATION);
		int index=0;
		
		while(cursor.moveToNext())
		{
			String number=cursor.getString(numberIndex);
			String name=cursor.getString(nameIndex);
			String duration=cursor.getString(callDuration);
			int t=cursor.getInt(typeIndex);
			String type;
			if(t==CallLog.Calls.INCOMING_TYPE)
				type="Incoming Call";
			
			else if(t==CallLog.Calls.OUTGOING_TYPE)
				type="Outgoing Call";
			
			else
				type="Missed Call";
			
			
			if(name==null)
				callArray[index] = number + " | " + type+" | Sec "+duration;
			else
				callArray[index] = name + " | " + type+" | Sec "+duration;
			
			Log.i("logviewer", "value[" + index + "] :" + callArray[index]);
			index++;
		}
		
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, callArray);
		list.setAdapter(listAdapter);
		Log.i("logviewer", "list adapter created and added to the list...");
	}
	@Override
	public void onClick(View v) 
	{
		finish();
		Log.i("logviewer", "view activity completed.");
		
	}

}
