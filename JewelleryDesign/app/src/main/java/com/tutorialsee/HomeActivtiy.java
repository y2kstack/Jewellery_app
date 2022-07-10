package com.tutorialsee;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tutorialsee.R;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivtiy extends Fragment implements ViewPager.OnPageChangeListener,OnClickListener {
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private ImageView[] dots;
	private int currentIndex;
	LinearLayout ll;
	LinearLayout user,mycart,home,Offers,more;
	ImageView s,ss,sss,ssss, _orddd;
	TextView email_slide;
	private FirebaseAuth mFirebaseAuth;



	private static final String[] pics = {
		"https://www.bookeventz.com/blog/wp-content/uploads/2018/11/South-Indian-Bridal-Jewellery.jpg",
		"https://cdn.yehaindia.com/wp-content/uploads/2020/06/Jewellery-1.jpg",
		"https://www.hazoorilaljewellers.com/wp-content/uploads/2022/02/01-1.jpg",
	"https://www.shopkhoj.com/wp-content/uploads/2018/07/Khazana.jpg" };


	Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 1;
		mFirebaseAuth = FirebaseAuth.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.home_activtiy, container, false);
		MainActivity.actionBar.show();
		context = container.getContext();

		more = (LinearLayout) v.findViewById(R.id.more);
		Offers = (LinearLayout) v.findViewById(R.id.Offers);
		home = (LinearLayout) v.findViewById(R.id.home);
		mycart = (LinearLayout) v.findViewById(R.id.mycart);
		user = (LinearLayout) v.findViewById(R.id.user);

		FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

		String email = firebaseUser.getEmail();
		Uri photo = firebaseUser.getPhotoUrl();
		String displayName = firebaseUser.getDisplayName();

		ImageView profileImage = (ImageView) v.findViewById(R.id._orddd);




//		Picasso.with(getContext())
//				.load(photo)
//				.placeholder(android.R.drawable.sym_def_app_icon)
//				.error(android.R.drawable.sym_def_app_icon)
//				.into(profileImage);
//		if (account != null){
//			//Display User Image from Google Account
//			//Objects.requireNonNull() prevents getPhotoUrl() from returning a NullPointerException
//			String personImage = Objects.requireNonNull(account.getPhotoUrl()).toString();
//			Glide.with(this).load(personImage).into(profileImage);
//		}

//		profileImage.setImageDrawable();


//		s = (LinearLayout) v.findViewById(R.id.s);
//		ss = (ImageView) v.findViewById(R.id.ss);
//		sss = (ImageView) v.findViewById(R.id.sss);
//		ssss = (ImageView) v.findViewById(R.id.ssss);

		//http://ratnajyoti.com/newjewel/r/jewelary/r_app/g.png
//		Picasso.with(context).load("http://www.tutorialsee.com/demo/image/b.png").into(s);
//		Picasso.with(context).load("http://www.tutorialsee.com/demo/image/c.png").into(ss);
//		Picasso.with(context).load("http://www.tutorialsee.com/demo/image/d.png").into(sss);
//		Picasso.with(context).load("http://www.tutorialsee.com/demo/image/a.png").into(ssss);

		ll = (LinearLayout) v.findViewById(R.id.ll);

		more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*Fragment newContent = new HomeActivtiy();
				if (newContent != null) {
					switchFragment(newContent);
				}*/
			}
		});
		
		Offers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "There are no offers available at this time", Toast.LENGTH_LONG).show();
				
				 
			}
		});
		
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Fragment newContent = new HomeActivtiy();
				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});
		
//		user.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Fragment newContent = new LoginActivity();
//				if (newContent != null) {
//					switchFragment(newContent);
//				}
//			}
//		});

		mycart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Fragment newContent = new AddToCart();
				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});

		// View Pager code
		views = new ArrayList<View>();
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(getActivity());
			iv.setLayoutParams(mParams);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			Picasso.with(getActivity()).load(pics[i].replace(" ", "%20")).into(iv);
			views.add(iv);
		}
		vp = (ViewPager) v.findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(views);
		vp.setAdapter(vpAdapter);

		vp.setOnPageChangeListener(this);

		initDots();


		return v;

	}


	public static Bitmap getBitmapFromURL(String src) {
		try {
			Log.e("src",src);
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			Log.e("Bitmap","returned");
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Exception",e.getMessage());
			return null;
		}
	}


	public void onBackPressed() {
		MainActivity.lp = 1;
	}

	@Override
	public void onResume() {
		MainActivity.lp = 1;
		super.onResume();
	}

	// switching fragment
	@SuppressWarnings("unused")
	private void switchFragment(Fragment fragment) {

		// mDrawerLayout.closeDrawer(mDrawerList);
		getActivity().getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commit();
	}

	@SuppressWarnings("unused")
	private void initDots() {
		Activity v = null;

		dots = new ImageView[pics.length];
		for (int i = 0; i < pics.length; i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);
			dots[i].setOnClickListener((OnClickListener) context);
			dots[i].setTag(i);
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);
	}

	// View Pager code
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			return;
		}
		vp.setCurrentItem(position);
	}

	// View Pager code
	private void setCurDot(int positon) {
		if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
			return;
		}
		dots[positon].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		currentIndex = positon;
	}

	// View Pager code
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}

	// View Pager code
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	// View Pager code
	@Override
	public void onPageSelected(int arg0) {
		setCurDot(arg0);
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);

	}


}