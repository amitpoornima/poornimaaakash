package com.example.phonebook;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SearchContact extends Activity implements OnItemClickListener
{
	private ListView listview;
	EditText e1;
	ArrayAdapter<StringBuffer> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact);
		e1=(EditText)findViewById(R.id.searchtext);
		listview=(ListView)findViewById(R.id.list_view);
		DBHelper db=new DBHelper(this);
		listview.setOnItemClickListener(this);
		
		ArrayList<StringBuffer> names=db.showNames();
		
		adapter=new ArrayAdapter<StringBuffer>(this, R.layout.list,R.id.contact_name,names);
		  listview.setAdapter(adapter);
		
		//****Enable search filter*****//
		
		e1.addTextChangedListener(new TextWatcher()
		{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) 
			{
				SearchContact.this.adapter.getFilter().filter(s);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		Intent intent=new Intent(this,PersonDetails.class);
		startActivity(intent);
		
	}
}
