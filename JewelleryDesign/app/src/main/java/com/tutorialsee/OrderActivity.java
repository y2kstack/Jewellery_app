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
import android.widget.LinearLayout;

public class OrderActivity extends Fragment {
	Context context;
	ImageView back;
	LinearLayout ff;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.order_activity, container, false);
		MainActivity.actionBar.hide();
		context = container.getContext();
	back = (ImageView) v.findViewById(R.id.back);
	ff = (LinearLayout) v.findViewById(R.id.ff);
	ff.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Fragment newContent = new OrderDetailsActivity();
			if (newContent != null) {
				switchFragment(newContent);
			}
			
		}
	});
	
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			onBackPressed();
			
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
