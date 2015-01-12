package com.example.phonebook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends Activity
{
	EditText inputnumber,msgbody;
	Button sendmsg;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		inputnumber=(EditText)findViewById(R.id.sendto);
		msgbody=(EditText)findViewById(R.id.msgbody);
		sendmsg=(Button)findViewById(R.id.sendmsgbtn);
		
		Intent intent=getIntent();
		String str=intent.getStringExtra("inputnumber");
		inputnumber.setText(str);
		
		sendmsg.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				String phoneno=inputnumber.getText().toString();
				String sms=msgbody.getText().toString();
				try
				{
					SmsManager sm=SmsManager.getDefault();
					sm.sendTextMessage(phoneno,null,sms,null,null);
					Toast.makeText(getApplicationContext(),"SMS Sent",Toast.LENGTH_LONG).show();
				}
				catch (Exception e) 
				{
					Toast.makeText(getApplicationContext(),"SMS Sending failed!!",Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_message, menu);
		return true;
	}

}
