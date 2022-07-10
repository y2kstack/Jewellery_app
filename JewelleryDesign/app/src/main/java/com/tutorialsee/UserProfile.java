package com.tutorialsee;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.tutorialsee.R;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserProfile extends Fragment {
	Context context;
	ImageView edit,back;
	LinearLayout changepassword,order;
	private FirebaseAuth firebaseAuth;
	TextView email, mobile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}


	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user_profile, container, false);
		MainActivity.actionBar.hide();
		context = container.getContext();
//		edit = (ImageView) v.findViewById(R.id.edit);
		back = (ImageView) v.findViewById(R.id.back);
		TextView emailView = (TextView) v.findViewById(R.id.email);
		TextView mobileView = (TextView) v.findViewById(R.id.mobile);
		firebaseAuth = FirebaseAuth.getInstance();
		FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

		String email = firebaseUser.getEmail();
		Uri photo = firebaseUser.getPhotoUrl();
		String displayName = firebaseUser.getDisplayName();

		emailView.setText(email);
		mobileView.setText(displayName);

//		changepassword = (LinearLayout) v.findViewById(R.id.changepassword);
//		order = (LinearLayout) v.findViewById(R.id.order);
//		order.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Fragment newContent = new OrderActivity();
//				if (newContent != null) {
//					switchFragment(newContent);
//				}
//
//			}
//		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		});
//		changepassword.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Fragment newContent = new ChangePassword();
//				if (newContent != null) {
//					switchFragment(newContent);
//				}
//
//			}
//		});
//		edit.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Fragment newContent = new EditProfile();
//				if (newContent != null) {
//					switchFragment(newContent);
//				}
//
//			}
//		});
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
