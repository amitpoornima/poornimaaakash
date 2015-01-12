package com.example.phonebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContacts extends Activity
{
	EditText name,mobno,email,address;
	Button save,back;
	DBHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_contacts);
		name=(EditText)findViewById(R.id.pname);
		mobno=(EditText)findViewById(R.id.mno);
		email=(EditText)findViewById(R.id.email);
		address=(EditText)findViewById(R.id.address);
		save=(Button)findViewById(R.id.saveupdate);
		back=(Button)findViewById(R.id.backupdate);
		
		db=new DBHelper(this);
	}
	public void update(View v)
	{
		if(v.getId()==R.id.saveupdate)
		{
			String str1=name.getText().toString();
			String str2=mobno.getText().toString();
			String str3=email.getText().toString();
			String str4=address.getText().toString();
			
			db.updateContacts(str1,str2,str3,str4);
			name.setText(null);
			mobno.setText(null);
			email.setText(null);
			address.setText(null);
			Toast.makeText(this,"Record Updated!!",Toast.LENGTH_LONG).show();
			
		}
	}
}
