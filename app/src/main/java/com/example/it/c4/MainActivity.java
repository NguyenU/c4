package com.example.it.c4;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

	private static Socket socket;
//	private static ServerSocket serverSocket;
//	private static InputStreamReader inputStreamReader;
//	private static BufferedReader bufferedReader;
	private static PrintWriter printWriter;



	String message = "";
	private static String ip = "10.10.10.222";
	EditText connectionTxt;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Ringing Button
		Button AC_button = findViewById(R.id.AC_button);
		final MediaPlayer ringing = MediaPlayer.create(this, R.raw.ringing);

		AC_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ringing(ringing);
			}
		});


		// Connection Test stuff
		connectionTxt = findViewById(R.id.connectionTestTxt);



	}

	protected void ringing(MediaPlayer ringing){

		if(ringing.isPlaying()){
			ringing.stop();
			try {
				ringing.prepare();
			} catch (IllegalStateException e){
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		} else {
			ringing.start();
			ringing.setLooping(true);
		}
	}

	protected void connetionTest(View v){
		message = connectionTxt.getText().toString();
		myTask myTasks = new myTask();
		myTasks.execute();

		Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_LONG).show();



	}

	class myTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... voids) {

			try {
				socket = new Socket(ip, 5000);     // Connect to the socket at port 5000
				printWriter = new PrintWriter(socket.getOutputStream());        // set the output stream
				printWriter.write(message);             // Send message through?
				printWriter.flush();
				printWriter.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}


