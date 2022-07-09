package com.tutorialsee;

import com.tutorialsee.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_screen);
	}
	public void onStart() {
	super.onStart();

	new Thread(new Runnable() {
		public void run() {
			try {

					Thread.sleep(5000);
					 Intent ii=new Intent(getBaseContext(),MainActivity.class);
					 ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	                    startActivity(ii);


			}
			catch (Throwable t) {
			}
		}
	}).start();
}

}
