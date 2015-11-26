package com.example.blunobasicdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class PermissonActivity extends Activity implements OnCheckedChangeListener{
	
	private CheckBox user1;
	private CheckBox user2;
	private CheckBox user3;
	private CheckBox user4;
	private TextView test;
	private MainActivity mainActivity;
	private boolean []permission=new boolean[4];
	
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.permission);
	
		test=(TextView) findViewById(R.id.test);
		user1=(CheckBox) findViewById(R.id.user1);
		user1.setOnCheckedChangeListener(this);
		user2=(CheckBox) findViewById(R.id.user2);
		user2.setOnCheckedChangeListener(this);
		user3=(CheckBox) findViewById(R.id.user3);
		user3.setOnCheckedChangeListener(this);
		user4=(CheckBox) findViewById(R.id.user4);
		user4.setOnCheckedChangeListener(this);	
		
		pref=PreferenceManager.getDefaultSharedPreferences(this);
		user1.setChecked(pref.getBoolean("permmission1", false));
		user2.setChecked(pref.getBoolean("permmission2", false));
		user3.setChecked(pref.getBoolean("permmission3", false));
		user4.setChecked(pref.getBoolean("permmission4", false));
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		//O(กษ_กษ)Oนþนþ~
		mainActivity=MainActivity.getMainActivity();
				
		switch(buttonView.getId()){
		case R.id.user1:
			if(isChecked){
				test.setText("1");
				mainActivity.serialSend("set01");
			}else{
				mainActivity.serialSend("set00");
			}break;		
		case R.id.user2:
			if(isChecked){
				test.setText("2");
				mainActivity.serialSend("set11");
			}else{
				mainActivity.serialSend("set10");
			}break;
		case R.id.user3:
			if(isChecked){
				test.setText("3");
				mainActivity.serialSend("set21");
			}else{
				mainActivity.serialSend("set20");
			}break;
		case R.id.user4:
			if(isChecked){
				test.setText("4");
				mainActivity.serialSend("set31");
			}else{
				mainActivity.serialSend("set30");
			}break;
		default:
			break;							
		}
	}
	
	protected void onStop() {
		
		editor=pref.edit();
		
		permission[0]=user1.isChecked();
		permission[1]=user2.isChecked();	
		permission[2]=user3.isChecked();
		permission[3]=user4.isChecked();
		
		
		editor.putBoolean("permmission1", permission[0]);			
		editor.putBoolean("permmission2", permission[1]);
		editor.putBoolean("permmission3", permission[2]);
		editor.putBoolean("permmission4", permission[3]);
		editor.commit();
		
		super.onStop();													
	}

	
}
