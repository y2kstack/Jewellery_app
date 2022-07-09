package com.tutorialsee;

import com.tutorialsee.R;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Shipping extends Fragment {
	Context context;
	TextView txt_pyment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.shipping_activity, container, false);
		MainActivity.actionBar.show();
		context = container.getContext();
		txt_pyment = (TextView) v.findViewById(R.id.txt_pyment);
		txt_pyment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Fragment newContent = new Payment_activity();
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
		if (getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStack();
		} 
	}
}
