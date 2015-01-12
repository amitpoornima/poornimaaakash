package com.example.phonebook;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper
{

	private static String db_Name="phonebook.db";
	private static String Table_Name="contacts";
	private static int db_Version=1;
	private static String column1="personname";
	private static String column2="mobileno1";
	private static String column3="mobileno2";
	private static String column4="emailaddress";
	private static String column5="address";

	public static String TAG="MyPhoneBook";
	private String createTable="create table contacts(_id integer primary key autoincrement,personname text,mobileno1 text,mobileno2 text,emailaddress text,address text)";
	SQLiteDatabase db;
	public static ArrayList<String> contactList,mailaddList;
	public DBHelper(Context context)
	{
		super(context,db_Name,null, db_Version);
		System.out.println("Database Created");
		Log.i(TAG,"DataBase Created");
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(createTable);
		Log.i(TAG,"Table Created Successfully!!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}
	
		public void saveRecord(Contacts s)
		{
			db=getWritableDatabase();
			ContentValues cv=new ContentValues();
			cv.put(column1, s.getPersonname());
			cv.put(column2, s.getMobileno1());
			cv.put(column3, s.getMobileno2());
			cv.put(column4, s.getEmailaddress());
			cv.put(column5, s.getHomeaddress());
			db.insert(Table_Name, null, cv);
			db.close();
		}
		public ArrayList<StringBuffer> viewRecord()
		{
			ArrayList<StringBuffer> list=new ArrayList<StringBuffer>();
			db=getWritableDatabase();
			contactList=new ArrayList<String>();
			mailaddList=new ArrayList<String>();
			Cursor cursor=db.rawQuery("select * from contacts order by personname",null);
			if(cursor!=null)
			{
				while(cursor.moveToNext())
				{
					String name=cursor.getString(1);
					String mobno1=cursor.getString(2);
					String mobno2=cursor.getString(3);
					String email=cursor.getString(4);
					String address=cursor.getString(5);
					
					StringBuffer sb=new StringBuffer();
					sb.append(name);
					list.add(sb);
					
					contactList.add(mobno1);
					mailaddList.add(email);
				}
			}
			db.close();
			return list;
		}
		public void updateContacts(String s1,String s2,String s3,String s4)
		{
			db=getWritableDatabase();
			ContentValues cv=new ContentValues();
			cv.put(column2,s2);
			cv.put(column4, s3);
			cv.put(column5, s4);
			db.update(Table_Name,cv,"personname=?",new String[]{s1});
			Log.i(TAG, "Record Updated");
		
		}
		
		public ArrayList<StringBuffer> showNames()
		{
			ArrayList<StringBuffer> list=new ArrayList<StringBuffer>();
			db=getWritableDatabase();
			Cursor cursor=db.rawQuery("select * from contacts",null);
			if(cursor!=null)
			{
				while(cursor.moveToNext())
				{
					String name=cursor.getString(1);
				
					StringBuffer sb=new StringBuffer();
					sb.append(name);
					list.add(sb);
				}
			}	
			Log.i(TAG, "Record Searched");
			
			db.close();
			return list;
		}
		public int deleteRecordByName(String name)
		{
			db=getWritableDatabase();
			String value[]={name};
			int i=db.delete(Table_Name, "personname=?",value);
			db.close();
			return i;	
		}
		public int deleteRecordByNum(String mobnum)
		{
			db=getWritableDatabase();
			String value[]={mobnum};
			int i=db.delete(Table_Name, "mobileno1=?",value);
			db.close();
			return i;
		}

		//to view contact details
		
		public ArrayList<StringBuffer> viewDetails(String str)
		{
			db=getWritableDatabase();
			ArrayList<StringBuffer>details=new ArrayList<StringBuffer>();
			Cursor cursor=db.rawQuery("select * from contacts where personname=?",new String[]{str});
			if(cursor!=null)
			{
				while(cursor.moveToNext())
				{
					String name=cursor.getString(1);
					String mobno1=cursor.getString(2);
					String mobno2=cursor.getString(3);
					String email=cursor.getString(4);
					String address=cursor.getString(5);
					
					
					StringBuffer sb=new StringBuffer();
					sb.append(name).append("\n").append(mobno1).append("\n").append(mobno2).append("\n").append(email).append("\n").append(address);
					details.add(sb);
					
				}
			}
			db.close();
			return details;
		}
	}

