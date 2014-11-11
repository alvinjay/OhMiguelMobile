package com.example.client;

import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.TextView;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.client.Client.OnMessageReceived;

public class Server {

	private String serverMessage;
	public static  String SERVERIP ; // your computer IP
															// address
	public static int SERVERPORT = 2222;
	private OnMessageReceived mMessageListener = null;
	private boolean mRun = false;
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private TextView status;
	
	private DataInputStream is = null;
	private PrintStream os = null;
	
	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 10;
	private static final ClientThread[] threads = new ClientThread[maxClientsCount];
	
	PrintWriter out;
	BufferedReader in;

	/**
	 * Constructor of the class. OnMessagedReceived listens for the messages
	 * received from server
	 */
	public Server(OnMessageReceived listener) {
		mMessageListener = listener;
	}

	/**
	 * Sends the message entered by client to the server
	 * 
	 * @param message
	 *            text entered by client
	 */
	public void sendMessage(String message) {
		if (out != null && !out.checkError()) {
			out.println(message);
			out.flush();
		}
	}

	public void stopClient() {
		mRun = false;
	}

	public void run() {

		mRun = true;

		 /*
         * Open a server socket on the portNumber (default 2222). Note that we can
         * not choose a port less than 1023 if we are not privileged users (root).
         */
        try {
        	serverSocket = new ServerSocket(Client.SERVERPORT);
//          	status.setText("Running at port: " + Client.SERVERPORT);
          	
          	 /*
             * Create input and output streams for this client.
             */
            
//            is = new DataInputStream(serverSocket.getInputStream());
//            os = new PrintStream(serverSocket.getOutputStream());
        } catch (IOException e) {
        	//failed to open socket
        	Log.e("Error:", "Failed to establish connection");
        }
       
        
        while (true) {
            try {
            	clientSocket = serverSocket.accept();
            	Log.d("Client","Connected");
            	int i = 0;
                for (i = 0; i < maxClientsCount; i++) {
                   if (threads[i] == null) {
                     (threads[i] = new ClientThread(clientSocket, threads)).start();
                     break;
                   }
                 }
//            	status.setText("Client connected");
            	break;
            } catch (IOException e) {
            	System.out.println(e);
            } catch (NetworkOnMainThreadException e){
            	Log.d("Client","Error");
//            	status.setText("Something went wrong: " + e.getMessage());
            }
        }
        
        Log.d("Update", "Client connected");

	}

	// Declare the interface. The method messageReceived(String message) will
	// must be implemented in the MyActivity
	// class at on asynckTask doInBackground
	public interface OnMessageReceived {
		public void messageReceived(String message);
	}
}