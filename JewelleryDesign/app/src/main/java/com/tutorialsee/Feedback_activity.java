package com.tutorialsee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.tutorialsee.R;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Feedback_activity extends Fragment implements OnItemSelectedListener {
	Context context;
	ImageView back;
	Spinner spinner ;
	EditText name,email,mobile,tellabout,message;
	Button submit;
	static String  error = "";
	ArrayList<HashMap<String, String>> array;
	static String msg = "",mail = "",status;
	String item;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}


	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.feedback_activity, container, false);
		MainActivity.actionBar.hide();
		context = container.getContext();
		
		name  = (EditText) v.findViewById(R.id.name);
		email  = (EditText) v.findViewById(R.id.email);
		mobile  = (EditText) v.findViewById(R.id.mobile);
		tellabout  = (EditText) v.findViewById(R.id.tellabout);
		message  = (EditText) v.findViewById(R.id.message);
		submit  = (Button) v.findViewById(R.id.submit);
		back  = (ImageView) v.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			/*	if(Network.isConnectionFast(context)){
				if(!validate()){
					
						Feedback msp = new Feedback();
						msp.execute();
						
				}
			}else{
				Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();
				
			}*/
			}
		});

		  spinner = (Spinner) v.findViewById(R.id.spinner);
	      spinner.setOnItemSelectedListener(this);
	      
	      // Spinner Drop down elements
	      List<String> categories = new ArrayList<String>();
	      categories.add("General");
	      categories.add("Suggestions");
	      categories.add("Appreciation");
	      categories.add("Bug/Error Report");
	      categories.add("Purchase Requirement");
	      categories.add("Complaint");
	      categories.add("Interested in Services");
	     
	      // Creating adapter for spinner
	      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
	      
	      // Drop down layout style - list view with radio button
	      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      
	      // attaching data adapter to spinner
	      spinner.setAdapter(dataAdapter);
		
		
		
		return v;
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
		if(name.getText().toString().trim().equals("")){
			name.setText("");
			name.requestFocus();
			Toast.makeText(getActivity(), "Either Username is wrong or is empty", Toast.LENGTH_SHORT).show();

			return true;
		}else if(tellabout.getText().toString().trim().equals("")){
			tellabout.setText("");
			tellabout.requestFocus();
				  Toast.makeText(getActivity(), "Either  Tell me About your Self is wrong or is empty", Toast.LENGTH_SHORT).show();
				
			return true;
		}else if(mobile.getText().toString().trim().equals("")){
			mobile.setText("");
			mobile.requestFocus();
				  Toast.makeText(getActivity(), "Either Mobile Number is wrong or is empty", Toast.LENGTH_SHORT).show();
				
			return true;
		}else if(message.getText().toString().trim().equals("")){
			message.setText("");
			message.requestFocus();
				  Toast.makeText(getActivity(), "Either  message is wrong or is empty", Toast.LENGTH_SHORT).show();
				
			return true;
		}else if (email.getText().toString().trim().equals("")) {
			if (isValidEmaillId(email.getText().toString().trim())) {
				Toast.makeText(getActivity(), "Valid Email Address.",Toast.LENGTH_SHORT).show();
			} else {

				Toast.makeText(getActivity(), "InValid Email Address.",Toast.LENGTH_SHORT).show();
			}
		}
		return false;
	
		
	}

	
	
/*
	class Feedback extends AsyncTask<String, Void, String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			MyProgressDialog.show(context);
		}
		
		protected String doInBackground(String... params) {
			array = new ArrayList<HashMap<String, String>>();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			nameValuePair.add(new BasicNameValuePair("name",name.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("mobile",mobile.getText().toString()));
			
			nameValuePair.add(new BasicNameValuePair("email",email.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("tellabout",tellabout.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("message",message.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("type",item));
			
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
			if(mail.trim().equals("2")){
				
				 
			}else if(mail.trim().equals("4")) {
				
			}else if(mail.trim().equals("1")) {
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();	
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


	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// On selecting a spinner item
	       item = parent.getItemAtPosition(position).toString();
	      
	     
	   
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}
