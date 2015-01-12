package com.example.phonebook;

import com.example.phonebook.Contacts;
import com.example.phonebook.DBHelper;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

public class AddNewContact extends Activity 
{

	EditText name,mobileno1,mobileno2,emailaddress,address;
	Button savebtn,discardbtn;
	ImageView iv;
	DBHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_contact);
		name=(EditText)findViewById(R.id.addname);
		mobileno1=(EditText)findViewById(R.id.mobno1);
		mobileno2=(EditText)findViewById(R.id.mobno2);
		emailaddress=(EditText)findViewById(R.id.emailaddress);
		address=(EditText)findViewById(R.id.address);
		iv=(ImageView)findViewById(R.id.img);
		
		
		savebtn=(Button)findViewById(R.id.savecontact);
		discardbtn=(Button)findViewById(R.id.discardcontact);
		
		/*savebtn.setOnClickListener(this);
		discardbtn.setOnClickListener(this);*/
		db=new DBHelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_new_contact, menu);
		return true;
	}
	
	public void openGallery(View v)
	{
		Intent i=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivity(i);
	}	
	public void save(View v)
	{
		String str1=name.getText().toString();
		String str2=mobileno1.getText().toString();
		String str3=mobileno2.getText().toString();
		String str4=emailaddress.getText().toString();
		String str5=address.getText().toString();
		
		Contacts contact=new Contacts(str1, str2, str3,str4,str5);
		db.saveRecord(contact);
		
		name.setText(null);
		mobileno1.setText(null);
		mobileno2.setText(null);
		emailaddress.setText(null);
		address.setText(null);
		Toast.makeText(this, "Contact saved successfully!!",Toast.LENGTH_LONG).show();
		Log.i(DBHelper.TAG,"Record Inserted Successfully!!");
		
	}
	public void discard(View v)
	{
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}
