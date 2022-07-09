package com.tutorialsee;

import com.tutorialsee.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Horoscope extends Fragment {
	Context context;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.horoscope, container, false);
		MainActivity.actionBar.show();
		context = container.getContext();
	
		
		return v;
	}
	
	
	
	
	
	private void switchFragment(Fragment fragment) {
		getActivity(). getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commitAllowingStateLoss();
	}

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStack();
		} 
	}
}
