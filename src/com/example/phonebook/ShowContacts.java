package com.example.phonebook;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowContacts extends Activity implements OnItemClickListener
{
	ListView v;
	DBHelper db;
	String contact,mailadd;
	ArrayList<StringBuffer> list;
	int i;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_contacts);
		v=(ListView)findViewById(R.id.listView1);
		db=new DBHelper(this);
		
		 list=db.viewRecord();
		//String []contact=(String[])list.toArray();
		ArrayAdapter<StringBuffer> adapter=new ArrayAdapter<StringBuffer>(this,R.layout.list,R.id.contact_name,list);
		v.setAdapter(adapter);
		v.setOnItemClickListener(this);
		registerForContextMenu(v);
	}
	 @Override 
	 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	 {
	    super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select your action");
		menu.add(0,v.getId(),0,"Call");
		menu.add(0,v.getId(),0,"SMS");
		menu.add(0,v.getId(),0,"Email");
		menu.add(0,v.getId(),0,"Cancel");
		menu.add(0,v.getId(),0,"Delete");
	}
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    i=info.position;
	    System.out.println("selected position is:"+i);
	    contact=DBHelper.contactList.get(i);
	    mailadd=DBHelper.mailaddList.get(i);
	   // Toast.makeText(this, "id is:"+contact, Toast.LENGTH_LONG).show();  
		if(item.getTitle()=="Call")
		{
			  Toast.makeText(this, "id is:"+contact, Toast.LENGTH_LONG).show();  
			Intent intent = new Intent(Intent.ACTION_CALL);
			 intent.setData(Uri.parse("tel:"+contact));
	         startActivity(intent);
		}
		if(item.getTitle()=="SMS")
		{
			Intent intent=new Intent(this,Message.class);
			intent.putExtra("inputnumber", contact);
			startActivity(intent);
		}
		if(item.getTitle()=="Email")
		{
			 Toast.makeText(this, "id is:"+mailadd, Toast.LENGTH_LONG).show();  
			Intent intent=new Intent(this,SendEmail.class);
			intent.putExtra("inputmailadd",mailadd);
			startActivity(intent);
		}
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
	public void backToMain(View v)
	{
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
	//	Toast.makeText(this,fileList()[arg2],Toast.LENGTH_LONG).show();
			Intent intent3=new Intent(this,PersonDetails.class);
			intent3.putExtra("msg",list.get(arg2).toString());
			startActivity(intent3);
	}
		
}
