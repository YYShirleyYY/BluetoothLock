package com.example.blunobasicdemo;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MyDataBaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_USER = "create table User("+"user text primary key,"+"password text)";
	private Context mContext;

	public MyDataBaseHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
	}

	
}
