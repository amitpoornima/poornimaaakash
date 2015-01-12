package com.example.phonebook;

import java.util.List;

import com.example.phonebook.AddNewContact;
import com.example.phonebook.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
		
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.item1:
			Intent intent=new Intent(this,AddNewContact.class);
			startActivity(intent);
			Toast.makeText(this, "Save Contact here", Toast.LENGTH_SHORT).show();
			return true;
			
			case R.id.item2:
			Intent intent2=new Intent(this,UpdateContacts.class);
			startActivity(intent2);
			Toast.makeText(this,"Contact updatation here",Toast.LENGTH_SHORT).show();
			return true;
			
			default:
	            return super.onOptionsItemSelected(item);
	        
		}
		
	}
	public void allContacts(View v)
	{
		if(v.getId()==R.id.allcontacts)
		{
			Intent intent=new Intent(this,ShowContacts.class);
			startActivity(intent);
		}
	}
	public void searchContact(View v)
	{
		if(v.getId()==R.id.searchcontact)
		{
			Intent intent=new Intent(this,SearchContact.class);
			startActivity(intent);
		}
	}
	public void callLog(View v)
	{
		Intent intent=new Intent(this,CallLogViewer.class);
		startActivity(intent);
	}

}
