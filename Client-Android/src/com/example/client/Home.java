package com.example.client;

import com.example.client.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Home extends Activity{
private Button connect, server;
private EditText ipAddress, port;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.home);
	ipAddress=(EditText) findViewById(R.id.editText1);
	port=(EditText) findViewById(R.id.serverPort);
	connect=(Button)findViewById(R.id.button1);
	server=(Button)findViewById(R.id.serverButton);
	connect.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String ip=ipAddress.getText().toString();
			Client.SERVERIP=ip;
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			//Log.e("ServerIP", Client.SERVERIP);
			startActivity(intent);
		}
	});
	
	server.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// TODO Auto-generated method stub
			int portNumber = Integer.parseInt(port.getText().toString());
			Client.SERVERPORT = portNumber;
			Intent intent = new Intent(getBaseContext(), ServerActivity.class);
			//Log.e("ServerIP", Client.SERVERIP);
			startActivity(intent);
		}
	});
}


}
