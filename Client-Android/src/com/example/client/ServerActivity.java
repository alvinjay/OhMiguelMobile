package com.example.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.example.client.R;
import com.example.client.R.id;
import com.example.client.R.layout;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ServerActivity extends Activity {

//	 private ListView mList;
//	    private ArrayList<String> arrayList;
//	    private MyCustomAdapter mAdapter;
//	    private Client mClient;
		private ServerSocket serverSocket;
		private Socket clientSocket;
		private TextView status;
		private Server mServer;
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_server);
	        
	        status = (TextView)findViewById(R.id.status);
	        
	        // The default port number.
	        int portNumber = 2222;

	        
//	 
//	        arrayList = new ArrayList<String>();
//	 
//	        final EditText editText = (EditText) findViewById(R.id.editText);
//	        Button send = (Button)findViewById(R.id.send_button);
//	 
//	        //relate the listView from java to the one created in xml
//	        mList = (ListView)findViewById(R.id.list);
//	        mAdapter = new MyCustomAdapter(this, arrayList);
//	        mList.setAdapter(mAdapter);
	 
	        // connect to the server
	        new connectTask().execute(Client.SERVERPORT + "");
	 
//	        send.setOnClickListener(new View.OnClickListener() {
//	            @Override
//	            public void onClick(View view) {
//	 
//	                String message = editText.getText().toString();
//	 
//	                //add the text in the arrayList
//	                //arrayList.add("c: " + message);
//	 
//	                //sends the message to the server
//	                if (mClient != null) { 
//	                    mClient.sendMessage(message);
//	                }
//	 
//	                //refresh the list
//	                mAdapter.notifyDataSetChanged();
//	                editText.setText("");
//	            }
//	        });
	 
	    }
	 
	    public class connectTask extends AsyncTask<String,String,Client> {
	 
	        @Override
	        protected Client doInBackground(String... message) {
	        	
	            //we create a Client object and
	            mServer = new Server(status);
	            mServer.run();
//	 
	            return null;
	        }
	 
//	        @Override
//	        protected void onProgressUpdate(String... values) {
//	            super.onProgressUpdate(values);
//	 
//	            //in the arrayList we add the messaged received from server
//	            arrayList.add(values[0]);
//	            // notify the adapter that the data set has changed. This means that new message received
//	            // from server was added to the list
//	            mAdapter.notifyDataSetChanged();
//	        }
	    }

}
