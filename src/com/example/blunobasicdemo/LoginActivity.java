package com.example.blunobasicdemo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private TextView textUser;	
	private TextView textPassword;
	private EditText editUser;
	private EditText editPassword;	
	private Button login;
	private MyDataBaseHelper dbHelper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		dbHelper=new MyDataBaseHelper(this, "UserStore.db", null, 1);
		final SQLiteDatabase db=dbHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("user", "123");
		values.put("password", "abc");
		db.insert("User", null, values);
		values.clear();
		
		values.put("user", "1234");
		values.put("password", "ye");
		db.insert("User", null, values);
		values.clear();
		
		values.put("user", "12345");
		values.put("password", "yy");
		db.insert("User", null, values);
		values.clear();
		
		values.put("user", "shirley");
		values.put("password", "yy");
		db.insert("User", null, values);
		values.clear();
		
		values.put("user", "shirley3");
		values.put("password", "yyy");
		db.insert("User", null, values);
		values.clear();
		
		
		editUser=(EditText)findViewById(R.id.editUser);
		editPassword=(EditText)findViewById(R.id.editPassword);			//initial the EditText of the sending data
		login = (Button) findViewById(R.id.login);		//initial the button for sending the data
		login.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String user=editUser.getText().toString();
				String password=editPassword.getText().toString();
								
				Cursor cursor=db.rawQuery("select * from User where user=? and password=?",new String[]{user,password});
				
				//if(user.equals("123")&&password.equals("abc")){
				if(cursor.getCount()!=0)	{
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					intent.putExtra("user", user);
					intent.putExtra("password", password);
					startActivity(intent);
				}else{
					Toast.makeText(LoginActivity.this, "用户名或者密码无效", Toast.LENGTH_SHORT).show();
				}
				cursor.close();

			}
		});
		
		
	}
	
	

	
}
