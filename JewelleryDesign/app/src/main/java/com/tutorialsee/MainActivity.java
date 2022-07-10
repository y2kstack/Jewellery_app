package com.tutorialsee;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.tutorialsee.R;
import com.tutorialsee.cart.dbhelpercart;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener,OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	private RelativeLayout mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	static Context context;
	public static int lp = 0;
	int flag = 1;
	static ActionBar actionBar;
	RelativeLayout lin15,lin2,lin13,lin4444,lin100,lin5555,lin555,lin16,lin17,lin6666,lin18,lin7777,lin444,lin21,lin14,lin28,lin666;
	public static String CUR_PAGE_TITLE = "Title";
	public static AutoCompleteTextView autoComplete;
	@SuppressWarnings("unused")
	private Handler mHandler = new Handler();
	ImageView btn_card, btn_search, btn_close;
	ImageView btn_logo,img_view,img_videw,img_viddsew,img_vidfdew;
	public static dbhelpercart dbHelpercart;
	public static TextView tx;
	private FirebaseAuth firebaseAuth;
	GoogleSignInClient gsc;

	@Override
	public void onResume() {
		Log.v("DEBUG", "onResume of LoginFragment");

		super.onResume();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_main);
		context = this;		
		dbHelpercart = new dbhelpercart(context);
		actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
		actionBar.setCustomView(R.layout.row);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.listicon);
//		btn_search = ((ImageView) findViewById(R.id.btn_search));
		btn_close = ((ImageView) findViewById(R.id.btn_close));
		btn_logo = ((ImageView) findViewById(R.id.btn_logo));
		img_view = ((ImageView) findViewById(R.id.img_view));
		img_videw = ((ImageView) findViewById(R.id.img_videw));
		img_viddsew = ((ImageView) findViewById(R.id.img_viddsew));
		img_vidfdew = ((ImageView) findViewById(R.id.img_vidfdew));
		btn_card = ((ImageView) findViewById(R.id.btn_notifcation));
		lin100 = (RelativeLayout) findViewById(R.id.lin100);
		firebaseAuth = FirebaseAuth.getInstance();

//
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(getString(R.string.default_web_client_id))
				.requestEmail()
				.build();

		  gsc = GoogleSignIn.getClient(this, gso);



		btn_card.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Fragment newContent = new Notifications();
				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});



		autoComplete = ((AutoCompleteTextView) findViewById(R.id.edt_search));
		
		btn_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btn_logo.setVisibility(View.VISIBLE);
				btn_card.setVisibility(View.VISIBLE);
//				btn_search.setVisibility(View.VISIBLE);
				btn_close.setVisibility(View.GONE);
				autoComplete.setVisibility(View.GONE);

			}
		});


		initMenu();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (RelativeLayout) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, // host Activity
				mDrawerLayout, // DrawerLayout object
				// nav drawer image to replace 'Up' image
				R.string.drawer_open, // "open drawer" description for
				// accessibility
				R.string.drawer_close // "close drawer" description for
				// accessibility
				) {
			public void onDrawerClosed(View view) {
				img_view.setImageResource(R.drawable.rightarrow);
				img_videw.setImageResource(R.drawable.rightarrow);
				img_viddsew.setImageResource(R.drawable.rightarrow);
				img_vidfdew.setImageResource(R.drawable.rightarrow);
				lin7777.setVisibility(View.GONE);
				lin5555.setVisibility(View.GONE);
				lin555.setVisibility(View.GONE);
				lin6666.setVisibility(View.GONE);
				lin4444.setVisibility(View.GONE);
				btn_logo.setVisibility(View.VISIBLE);
				btn_card.setVisibility(View.VISIBLE);
//				btn_search.setVisibility(View.VISIBLE);
				btn_close.setVisibility(View.GONE);
				autoComplete.setVisibility(View.GONE);
				autoComplete.setText("");
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				btn_logo.setVisibility(View.VISIBLE);
				btn_card.setVisibility(View.VISIBLE);
//				btn_search.setVisibility(View.VISIBLE);
				btn_close.setVisibility(View.GONE);
				autoComplete.setVisibility(View.GONE);
				autoComplete.setText("");
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
				//onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			switchFragment(new LoginActivity());
			setSelected(lin15);

		}
	}


	@SuppressLint("CutPasteId")
	private void initMenu() {
		lin15 = (RelativeLayout) findViewById(R.id.lin15);
		lin2 = (RelativeLayout) findViewById(R.id.lin2);
		lin13 = (RelativeLayout) findViewById(R.id.lin13);
		lin4444 = (RelativeLayout) findViewById(R.id.lin4444);
		lin16 = (RelativeLayout) findViewById(R.id.lin16);
		lin5555 = (RelativeLayout) findViewById(R.id.lin5555);
		lin555 = (RelativeLayout) findViewById(R.id.lin555);
		lin17 = (RelativeLayout) findViewById(R.id.lin17);
		lin6666 = (RelativeLayout) findViewById(R.id.lin6666);
		lin18 = (RelativeLayout) findViewById(R.id.lin18);
		lin7777 = (RelativeLayout) findViewById(R.id.lin7777);
		lin444 = (RelativeLayout) findViewById(R.id.lin444);
		lin21 = (RelativeLayout) findViewById(R.id.lin21);
		lin14 = (RelativeLayout) findViewById(R.id.lin14);
		lin28 = (RelativeLayout) findViewById(R.id.lin28);
		lin666 = (RelativeLayout) findViewById(R.id.lin666);
		lin100 = (RelativeLayout) findViewById(R.id.lin100);
		lin666.setOnClickListener(this);
		
		lin28.setOnClickListener(this);
		lin18.setOnClickListener(this);
		lin21.setOnClickListener(this);
		lin444.setOnClickListener(this);
		lin7777.setOnClickListener(this);
		lin14.setOnClickListener(this);
		lin6666.setOnClickListener(this);
		lin17.setOnClickListener(this);
		lin5555.setOnClickListener(this);
		lin555.setOnClickListener(this);
		lin4444.setOnClickListener(this);
		lin16.setOnClickListener(this);
		lin13.setOnClickListener(this);
		lin2.setOnClickListener(this);
		lin15.setOnClickListener(this);

		lin100.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				firebaseAuth.signOut();
				gsc.signOut();
				Fragment newContent = new LoginActivity();
				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
				
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		return true;

	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		Bundle bundle = new Bundle();
		
		if (v.getId() == R.id.lin2) {
			newContent = new UserProfile();
		}else if (v.getId() == R.id.lin28) {
			newContent = new AboutUs();
		}else if (v.getId() == R.id.lin666) {
				newContent = new Horoscope();
		}else if (v.getId() == R.id.lin15) {
			newContent = new HomeActivtiy();
		}else if (v.getId() == R.id.lin21) {
			newContent = new Feedback_activity();
		}else if (v.getId() == R.id.lin14) {
			Log.d("debug", "onClick: ");
			newContent = new ContactUs();
		}else if (v.getId() == R.id.lin16) {
			Log.d("debug", "onClick: ");
			newContent = new Diamond();
		}else if (v.getId() == R.id.lin13) {
			Log.d("debug", "onClick: Lin13 ");
			newContent = new Jewellery();
		}else if (v.getId() == R.id.lin17) {
			Log.d("debug", "onClick: Earring ");
			newContent = new Earring();
		}else if (v.getId() == R.id.lin18) {
			Log.d("debug", "onClick: Ring ");
			newContent = new Ring();
		}else if (v.getId() == R.id.lin2) {
			newContent = new UserProfile();
		} else if (v.getId() == R.id.lin16) {
			
			if (flag % 2 == 0)
			  {
				img_videw.setImageResource(R.drawable.bottomarrow);
				lin5555.setVisibility(View.VISIBLE);
					flag = flag+1;
				} else {
					img_videw.setImageResource(R.drawable.rightarrow);
					lin5555.setVisibility(View.GONE);
					flag = flag+1;
				}
	}else if (v.getId() == R.id.lin17) {
		
		if (flag % 2 == 0)
		  {
			img_viddsew.setImageResource(R.drawable.bottomarrow);
			lin6666.setVisibility(View.VISIBLE);
				flag = flag+1;
			} else {
				img_viddsew.setImageResource(R.drawable.rightarrow);
				lin6666.setVisibility(View.GONE);
				flag = flag+1;
			}
}
	else if (v.getId() == R.id.lin18) {

	if (flag % 2 == 0)
	  {
		img_vidfdew.setImageResource(R.drawable.bottomarrow);
		lin7777.setVisibility(View.VISIBLE);
			flag = flag+1;
		} else {
		img_vidfdew.setImageResource(R.drawable.rightarrow);
		lin7777.setVisibility(View.GONE);
		flag = flag + 1;
	}

		}else if (v.getId() == R.id.lin13) {
			// debug
			Log.d("debug", "onClick: ");
			newContent = new Jewellery();
		}

			if (newContent != null) {
					newContent.setArguments(bundle);
					switchFragment(newContent);
				}

	}



	// switching fragment
	private void switchFragment(Fragment fragment) {

		mDrawerLayout.closeDrawer(mDrawerList);

		FragmentManager fragmentManager = getSupportFragmentManager();
		// fragmentManager.beginTransaction().replace(R.id.content_frame,
		// fragment).commit();
		fragmentManager.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commit();
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	// set the selected option as enabled
	private void setSelected(RelativeLayout rl) {

		lin15.setSelected(false);
		lin2.setSelected(false);
		lin13.setSelected(false);
		lin4444.setSelected(false);
		lin5555.setSelected(false);
		lin555.setSelected(false);
		lin16.setSelected(false);
		lin17.setSelected(false);
		lin6666.setSelected(false);
		lin18.setSelected(false);
		lin7777.setSelected(false);
		lin444.setSelected(false);
		lin21.setSelected(false);
		lin14.setSelected(false);
		lin28.setSelected(false);
		lin666.setSelected(false);
		rl.setSelected(true); // set current selection

	}

	// When using the ActionBarDrawerToggle, you must call it during
	// onPostCreate() and onConfigurationChanged()
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}



	@SuppressLint("NewApi")
	@Override
	public void onBackPressed() {

		if (MainActivity.lp == 1) {

			Log.v("MainActivity", "" + MainActivity.lp);

		} else {
			Log.v("MainActivity", "" + MainActivity.lp);
			super.onBackPressed();
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (MainActivity.lp == 1) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
				System.exit(0);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}
}