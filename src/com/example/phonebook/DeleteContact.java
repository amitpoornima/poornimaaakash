package com.example.phonebook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteContact extends Activity
{
	EditText name,mobno;
	DBHelper db;
	Button dlt,back;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_contact);
		
		name=(EditText)findViewById(R.id.dltbyname);
		mobno=(EditText)findViewById(R.id.dltbymobno);
		
		
		db=new DBHelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_delete_contact, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.item1:
			String pname=name.getText().toString();
			int count= db.deleteRecordByName(pname);
			Toast.makeText(this, count+" record deleted", Toast.LENGTH_SHORT).show();
			name.setText(null);
			Toast.makeText(this, "Contact delete by name", Toast.LENGTH_SHORT).show();
			return true;
			
			case R.id.item2:
				String mobnum=mobno.getText().toString();
				int count1= db.deleteRecordByNum(mobnum);
				Toast.makeText(this, count1+"Contact deleted successfully!!", Toast.LENGTH_SHORT).show();
				mobno.setText(null);
			return true;
			
			default:
	            return super.onOptionsItemSelected(item);
	        
		}
		
	}

}
