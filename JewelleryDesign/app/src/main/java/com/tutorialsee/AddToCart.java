package com.tutorialsee;

import com.tutorialsee.R;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddToCart extends Fragment {
	Context context;
	Button btnLogin;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.add_to_cart, container, false);
		MainActivity.actionBar.show();
		context = container.getContext();
		btnLogin = (Button) v.findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Fragment newContent = new Shipping();
				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});
		
		return v;
	}
	
	
	
	
	
	private void switchFragment(Fragment fragment) {
		getActivity(). getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commitAllowingStateLoss();
	}

	public void onBackPressed() {
		
	}
}
