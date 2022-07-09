package com.tutorialsee;

import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.tutorialsee.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Registration extends Fragment {
	private static WeakReference<Registration> wrActivity = null;
TextView captcher;
private static final int MAXIMUM_BIT_LENGTH = 50;
private static final int RADIX = 32;
private Context mContext;
Context context;
private Activity mActivity;
EditText  txt_email,txt_username,txt_password,txt_zip,txt_fullusername,txt_mobile;
Button btnLogin;
static String  error = "";
ImageView back;
ArrayList<HashMap<String, String>> array;
static String msg = "",mail = "",status;



@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	MainActivity.lp=2;
	wrActivity = new WeakReference<Registration>(this);
}


public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.registration_activity, container, false);
	MainActivity.actionBar.hide();
	context = container.getContext();
	//PrefernceSettings.openDataBase(context);
	
	captcher = (TextView) v.findViewById(R.id.captcher);
	String randomText = getRandomText();
	Log.v("aaaaaaaaa", randomText);
//	captcher.setText(randomText);

	back = (ImageView) v.findViewById(R.id.back);
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			onBackPressed();
			
		}
	});
	txt_mobile = (EditText) v.findViewById(R.id.txt_mobile);
	txt_fullusername = (EditText) v.findViewById(R.id.txt_fullusername);
	txt_zip = (EditText) v.findViewById(R.id.txt_zip);
	txt_email = (EditText) v.findViewById(R.id.txt_email);
	txt_username = (EditText) v.findViewById(R.id.txt_username);
	txt_password = (EditText) v.findViewById(R.id.txt_password);
//	txt_code = (EditText) v.findViewById(R.id.txt_code);
	btnLogin = (Button) v.findViewById(R.id.btnLogin);
	btnLogin.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			if(Network.isConnectionFast(context)){
				if(!validate()){
						Login msp = new Login();
						msp.execute();
				}
			}else{
				registerUser();

			}
		}
	});
	
	

	
	return v;	
}


	public void registerUser(){
		final String email = txt_email.getText().toString().trim();
		final String password = txt_password.getText().toString().trim();
		final String fullname = txt_fullusername.getText().toString().trim();
		final String username = txt_username.getText().toString().trim();


//		mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
//					@Override
//
//					public void onComplete(@NonNull Task<AuthResult> task) {
//						if (task.isSuccessful()) {
//							Toast.makeText(null, "User successfully added", Toast.LENGTH_SHORT).show();
//						} else {
//							Toast.makeText(null, "Error", Toast.LENGTH_SHORT).show();
//						}
//					}
//				});
	}

	
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
		}else if(txt_fullusername.getText().toString().trim().equals("")){
			txt_fullusername.setText("");
			txt_fullusername.requestFocus();
				  Toast.makeText(getActivity(), "Either  Full Name is wrong or is empty", Toast.LENGTH_SHORT).show();
				
			return true;
		}else if (txt_email.getText().toString().trim().equals("")) {
			if (isValidEmaillId(txt_email.getText().toString().trim())) {
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
//			nameValuePair.add(new BasicNameValuePair("name",txt_username.getText().toString()));
//			nameValuePair.add(new BasicNameValuePair("password",txt_password.getText().toString()));
//			nameValuePair.add(new BasicNameValuePair("code",txt_code.getText().toString()));
//
//			nameValuePair.add(new BasicNameValuePair("email",txt_email.getText().toString()));
////			nameValuePair.add(new BasicNameValuePair("zip",txt_zip.getText().toString()));
//			nameValuePair.add(new BasicNameValuePair("fullname",txt_fullusername.getText().toString()));
//			nameValuePair.add(new BasicNameValuePair("mobile",txt_mobile.getText().toString()));

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