package com.example.phonebook;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PersonDetails extends Activity
{
	TextView details;
	String callto;
	DBHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_details);
		details=(TextView)findViewById(R.id.namedetail);
		//**********************detailsdisplay**************************//
		db=new DBHelper(this);
		Intent intent=getIntent();
		String str=intent.getStringExtra("msg");
		ArrayList<StringBuffer> getDetails=db.viewDetails(str);
		System.out.println(str);
		
		String compdetail=getDetails.get(0).toString();
		
		details.setText(compdetail);

		
	}
	
}
