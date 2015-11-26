package com.example.blunobasicdemo;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity  extends BlunoLibrary {
	private Button buttonScan;
	private Button buttonSerialSend;
	private Button buttonAdmin;
	private TextView textView;	
	private TextView serialReceivedText;
	private static MainActivity mainActivity;
	
	private MyDataBaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		onCreateProcess();						//onCreate Process by BlunoLibrary
        
		//dbHelper=new MyDataBaseHelper(this, "UserStore.db", null, 1);
		
        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200
		
        mainActivity=this;
        
        Intent intent=getIntent();
        String rightUser=intent.getStringExtra("user");
        String rightPassword=intent.getStringExtra("password");
        final String send=rightUser+rightPassword;
        //O(∩_∩)O哈哈~
        
        textView=(TextView) findViewById(R.id.editText2);
        
        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
       
        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String send=new StringBuilder().append(serialSendText.getText()).append(serialSendpwText.getText()).toString();
				//textView.setText(send);
				serialSend(send);
				
				
				//serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
			}
		});
        
        buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
			}
		});
	}

	public static MainActivity getMainActivity(){ return mainActivity;} 

	
	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();														//onResume Process by BlunoLibrary
		/*if (serialReceivedText.getText().toString().equals("congratulations O(∩_∩)O~")){
			Intent intent=new Intent(MainActivity.this,PermissonActivity.class);
			startActivity(intent);
		}*/
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        //onPauseProcess();														//onPause Process by BlunoLibrary
    }
	
	protected void onStop() {
		super.onStop();
		//onStopProcess();														//onStop Process by BlunoLibrary
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
        
        onPauseProcess();
        onStopProcess();
        
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
		switch (theConnectionState) {											//Four connection state
		case isConnected:
			buttonScan.setText("Connected");
			break;
		case isConnecting:
			buttonScan.setText("Connecting");
			break;
		case isToScan:
			buttonScan.setText("Scan");
			break;
		case isScanning:
			buttonScan.setText("Scanning");
			break;
		case isDisconnecting:
			buttonScan.setText("isDisconnecting");
			break;
		default:
			break;
		}
	}

	@Override
	public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
		// TODO Auto-generated method stub
		//serialReceivedText.append(theString);							//append the text into the EditText
		//The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
		//serialReceivedText.setText(theString);
		
		String admin="admin";
		String yes="yes";
		String no="no";
		
		if (theString.trim().equals(yes)){
			serialReceivedText.setText("开锁成功");
		//	Intent intent=new Intent(MainActivity.this,PermissonActivity.class);
		//	startActivity(intent);
		}else if(theString.trim().equals(no)){
			serialReceivedText.setText("sorry╮(╯▽╰)╭");
		}
		else if(theString.trim().equals(admin)){
			serialReceivedText.setText("开锁成功");
			buttonAdmin = (Button) findViewById(R.id.buttonAdmin);		//initial the button for sending the data
			buttonAdmin.setVisibility(View.VISIBLE);
			buttonAdmin.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(MainActivity.this,PermissonActivity.class);
					startActivity(intent);
				}
				
			});
		}
		else{
			serialReceivedText.setText(theString);
		}
		
		/*if (serialReceivedText.getText().toString().equals("congratulations O(∩_∩)O~")){
			Intent intent=new Intent(MainActivity.this,PermissonActivity.class);
			startActivity(intent);
		}*/
					
	}

}