package com.booze.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import com.booze.util.HttpConnectionUtil;

public class SplashScreen extends Activity {
	private static final int SPLASH_TIME = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				runOnUiThread(new Thread(){
					@Override
					public void run() {
						if(HttpConnectionUtil.checkInternetConn(SplashScreen.this)){
						Intent i = new Intent(SplashScreen.this, HomeActivity.class);
						startActivity(i);
						finish();
						}else{
							Toast.makeText(SplashScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
							finish();
						}
					}
				});
			}
		}, SPLASH_TIME);
	}
}
