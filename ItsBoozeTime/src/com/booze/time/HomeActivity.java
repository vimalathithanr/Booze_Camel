package com.booze.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	private ImageButton settings;
	private ImageButton g_search;
	private ImageButton places;
	private ImageButton favourites;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		settings = (ImageButton) findViewById(R.id.settings);
		g_search = (ImageButton) findViewById(R.id.g_search);
		places = (ImageButton) findViewById(R.id.places);
		favourites = (ImageButton) findViewById(R.id.favourites);
		settings.setOnClickListener(this);
		g_search.setOnClickListener(this);
		places.setOnClickListener(this);
		favourites.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == places){
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
		}else{
			Toast.makeText(this, "Service is not available", Toast.LENGTH_SHORT).show();
		}
	}
}
