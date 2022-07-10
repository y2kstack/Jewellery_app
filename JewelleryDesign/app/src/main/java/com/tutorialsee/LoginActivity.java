package com.tutorialsee;

import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.tutorialsee.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class LoginActivity extends Fragment {

	private static WeakReference<LoginActivity> wrActivity = null;
	TextView captcher,txt_reg,forgetpassword;
	private static final int MAXIMUM_BIT_LENGTH = 50;
	private static final int RADIX = 32;
	private Context mContext;
	Context context;
	private Activity mActivity;
	Dialog dialog;
	EditText  email,txt_username,txt_password;
	Button btnLogin;
	static String  error = "";
	ImageView back;
	ArrayList<HashMap<String, String>> array,array1;
	static String msg = "",mail = "",status;
	private FirebaseAuth mFirebaseAuth;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
		wrActivity = new WeakReference<LoginActivity>(this);
		mActivity = getActivity();
		mContext = getActivity().getApplicationContext();
		 mFirebaseAuth = FirebaseAuth.getInstance();

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			try {
				GoogleSignInAccount account = task.getResult(ApiException.class);
				firebaseAuthWithGoogle(account);
			} catch (ApiException e) {
				Log.d(String.valueOf(e), "error");
			}
		}
	}
	private void finishF() {
		Activity activity = (Activity)getContext();
		activity.finish();
	}


	private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

		AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
		mFirebaseAuth.signInWithCredential(credential)
				.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
					@Override
					public void onSuccess(AuthResult authResult) {


						FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//						updateUI(firebaseUser);

						String uid = firebaseUser.getUid();
						String email = firebaseUser.getEmail();
						Uri photo = firebaseUser.getPhotoUrl();



						if (authResult.getAdditionalUserInfo().isNewUser()){
							Toast.makeText(getActivity(),"Account created "+email,Toast.LENGTH_SHORT).show();

						}
						else {
							Toast.makeText(getActivity(),"Existing user "+email,Toast.LENGTH_SHORT).show();

						}

//						startActivity(new Intent(getActivity(),HomeActivtiy.class));
						Fragment newContent = new HomeActivtiy();
						if (newContent != null) {
							switchFragment(newContent);
						}

					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Log.d("","aaa");
					}
				});
	}



	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.login_activity, container, false);
		MainActivity.actionBar.hide();
		context = container.getContext();

		captcher = (TextView) v.findViewById(R.id.captcher);
		String randomText = getRandomText();
		Log.v("aaaaaaaaa", randomText);
		captcher.setText(randomText);

		back = (ImageView) v.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		});

		txt_reg = (TextView) v.findViewById(R.id.txt_reg);
		forgetpassword = (TextView) v.findViewById(R.id.forgetpassword);
		txt_username = (EditText) v.findViewById(R.id.txt_username);
		txt_password = (EditText) v.findViewById(R.id.txt_password);
		//	txt_code = (EditText) v.findViewById(R.id.txt_code);
		btnLogin = (Button) v.findViewById(R.id.btnLogin);


		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(getString(R.string.default_web_client_id))
				.requestEmail()
				.build();

		final GoogleSignInClient mSignInClient = GoogleSignIn.getClient(getActivity().getApplicationContext(), gso);


		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent signInIntent = mSignInClient.getSignInIntent();
				startActivityForResult(signInIntent, 1);

			}
		});


		forgetpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.forgotpass_popup);
				dialog.setTitle("Forgot Password");
				TextView save = (TextView) dialog.findViewById(R.id.text6);
				TextView cancel = (TextView) dialog.findViewById(R.id.text5);
				email = (EditText) dialog.findViewById(R.id.email);
				save.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//	Add();
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});


				dialog.show();
				// Add();
				//mySpinner = (Spinner)dialog.findViewById(R.id.spinner1);
				dialog.show();
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
				lp.copyFrom(dialog.getWindow().getAttributes());
				lp.width = WindowManager.LayoutParams.MATCH_PARENT;
				lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
				dialog.getWindow().setAttributes(lp);
			}
		});
		txt_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Fragment newContent = new Registration();
				Log.d("changingview", "registration page");

				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});





		return v;
	}



	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		context = this;
		captcher = (TextView) findViewById(R.id.captcher);
		String randomText = getRandomText();
		Log.v("aaaaaaaaa", randomText);
		captcher.setText(randomText);

		txt_reg = (TextView) findViewById(R.id.txt_reg);
		forgetpassword = (TextView) findViewById(R.id.forgetpassword);
		txt_username = (EditText) findViewById(R.id.txt_username);
		txt_password = (EditText) findViewById(R.id.txt_password);
		txt_code = (EditText) findViewById(R.id.txt_code);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(Network.isConnectionFast(context)){
					if(!validate()){

							Login msp = new Login();
							msp.execute();

					}
				}else{
					Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();

				}
			}
		});


		forgetpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.forgotpass_popup);
                dialog.setTitle("Forgot Password");
                TextView save = (TextView) dialog.findViewById(R.id.text6);
    			TextView cancel = (TextView) dialog.findViewById(R.id.text5);
    			email = (EditText) dialog.findViewById(R.id.email);
    			save.setOnClickListener(new View.OnClickListener() {
    				@Override
    				public void onClick(View v) {
    				//	Add();
    				}
    			});
    			cancel.setOnClickListener(new View.OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					dialog.dismiss();
    				}
    			});


                dialog.show();
               // Add();
            	//mySpinner = (Spinner)dialog.findViewById(R.id.spinner1);
    			dialog.show();
    			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    			lp.copyFrom(dialog.getWindow().getAttributes());
    			lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    			lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    			dialog.getWindow().setAttributes(lp);
			}
		});
		txt_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 Intent ii=new Intent(getBaseContext(),Registration.class);
				 ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(ii);
			}
		});
	}
*/

	private boolean isValidEmaillId(String email) {

		return Pattern
				.compile(
						"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
								+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
								+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
								+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
								+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
								+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
				.matcher(email).matches();
	}


	private boolean validate(){
		if(txt_username.getText().toString().trim().equals("")){
			txt_username.setText("");
			txt_username.requestFocus();
			Toast.makeText(getActivity(), "Either Username is wrong or is empty", Toast.LENGTH_SHORT).show();

			return true;
		}else if(txt_password.getText().toString().trim().equals("")){
			txt_password.setText("");
			txt_password.requestFocus();
			Toast.makeText(getActivity(), "Either Password is wrong or is empty", Toast.LENGTH_SHORT).show();

			return true;
//		}else if(txt_code.getText().toString().trim().equals("")){
//			txt_code.setText("");
//			txt_code.requestFocus();
//				  Toast.makeText(getActivity(), "Either Varification Code is wrong or is empty", Toast.LENGTH_SHORT).show();
//
//			return true;
		}else if (email.getText().toString().trim().equals("")) {
			if (isValidEmaillId(email.getText().toString().trim())) {
				Toast.makeText(getActivity(), "Valid Email Address.",
						Toast.LENGTH_SHORT).show();
			} else {

				Toast.makeText(getActivity(), "InValid Email Address.",
						Toast.LENGTH_SHORT).show();
			}
		}
		return false;


	}



	private String getRandomText() {
		SecureRandom random = new SecureRandom();
		// randomly generated BigInteger
		BigInteger bigInteger = new BigInteger(MAXIMUM_BIT_LENGTH, random);
		// String representation of this BigInteger in the given radix.
		String randomText = bigInteger.toString(RADIX);
		return randomText;
	}



	class Login extends AsyncTask<String, Void, String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			MyProgressDialog.show(context);
		}
		protected String doInBackground(String... params) {
			array = new ArrayList<HashMap<String, String>>();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			nameValuePair.add(new BasicNameValuePair("name",txt_username.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("password",txt_password.getText().toString()));
//			nameValuePair.add(new BasicNameValuePair("code",txt_code.getText().toString()));
			String res = BaseActivity.getJson(nameValuePair, getActivity(), Constant.REG );
			try{
				JSONObject jObject = new JSONObject(res);
				Log.v("AAAAAAAAAAAAAAAAA", res);

				if(res.contains("msg")){
					msg = jObject.getString("msg");
				}
				if(res.contains("status")){
					mail = jObject.getString("status");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}


		protected void onPostExecute(String args) {
			MyProgressDialog.close(context);
			/*if(mail.trim().equals("2")){
				Intent ii=new Intent(getBaseContext(),MobileLogin.class);
				ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(ii);
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

			}else if(mail.trim().equals("4")) {
				Intent si=new Intent(getBaseContext(),OTO.class);
				si.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(si);
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

			}else if(mail.trim().equals("1")) {
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
			}*/
		}
	}
	/*public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			finishAffinity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}*/

/*

	protected void Add() {
		if(Network.isConnectionFast(context)){
			if (isValidEmaillId(email.getText().toString().trim())) {
				forgotpass msp = new forgotpass();
				msp.execute();
			} else {
				Toast.makeText(getApplicationContext(), "InValid Email Address.",Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast toast = Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}

	class forgotpass extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			MyProgressDialog.show(context);
		}

		@Override
		protected String doInBackground(String... params) {
			array1 = new ArrayList<HashMap<String, String>>();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("email", email.getText().toString()));
			String res = BaseActivity.getJson(nameValuePairs,getApplicationContext(), Constant.REG);
			try {
				JSONObject jObject = new JSONObject(res);
				if (res.contains("success")) {
					msg = jObject.getString("success");
				}
				if (res.contains("error")) {
					error = jObject.getString("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {
			MyProgressDialog.close(context);

			if (msg.trim().equals("true")) {
				txt_password.setText("");
				Toast toast = Toast.makeText(getApplicationContext(),"Your password has been changed",Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				dialog.dismiss();

			} else if (error.trim().equals("Mail Address Not exit")) {
				Toast toast = Toast.makeText(context,"Mail Address Does Not Exist", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			} else {
				Toast toast = Toast.makeText(context,"Please check your internet connection",Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}
	}*/


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