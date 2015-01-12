package com.example.phonebook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendEmail extends Activity 
{
	EditText inputmailadd,cc,subject,mailmsg;
	Button sendmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		inputmailadd=(EditText)findViewById(R.id.inputmailaddress);
		cc=(EditText)findViewById(R.id.ccmail);
		subject=(EditText)findViewById(R.id.mailsubject);
		mailmsg=(EditText)findViewById(R.id.mailmsg);
		sendmail=(Button)findViewById(R.id.sendmailbtn);
		
		Intent intent=getIntent();
		String str=intent.getStringExtra("inputmailadd");
		inputmailadd.setText(str);
		
		sendmail.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				String to=inputmailadd.getText().toString();
				String ccto=cc.getText().toString();
				String subject1=subject.getText().toString();
				String msg=mailmsg.getText().toString();
				
				Intent email=new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
				email.putExtra(Intent.EXTRA_CC,new String[]{ccto});
				email.putExtra(Intent.EXTRA_SUBJECT,subject1);
				email.putExtra(Intent.EXTRA_TEXT,msg);
				
				//need thisto prompt email client only
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email,"Choose an email client"));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_send_email, menu);
		return true;
	}

}
