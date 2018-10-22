package com.example.it.c4;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		Button AC_button = findViewById(R.id.AC_button);
		final MediaPlayer ringing = MediaPlayer.create(this, R.raw.ringing);


		AC_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ringing(ringing);
			}
		});
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

}


