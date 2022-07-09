package com.tutorialsee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.tutorialsee.cart.CartItem;
import com.tutorialsee.cart.CartItemAdapter;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddCart extends Fragment {
	public static TextView txt_checkout;
	public static TextView ssd;
	@SuppressWarnings("unused")
	private static final String TAG = "ShoppingCartActivity";
	ArrayList<HashMap<String, String>> array1=new ArrayList<HashMap<String, String>>();
	ListView lvCartItems;
	ImageView bClear;
	public static TextView tvTotalPrice;
	Context context;
	 ListView listviews ;
	ItemDetails mItems;
	public static RelativeLayout _rel;
	public static LinearLayout shopnow;
	public static CartItemAdapter cartItemAdapter;
	List<ItemDetails> itemListsys = new ArrayList<ItemDetails>();
	ArrayList<HashMap<String, String>> arraylist;
	List<ItemDetails> itemList = new ArrayList<ItemDetails>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.cart, container, false);
		MainActivity.actionBar.show();
		context = container.getContext();
		return v;
	}
	/*	PrefernceSettings.openDataBase(context);
		String abc = PrefernceSettings.getUserId();
		txt_checkout = (TextView) v.findViewById(R.id.txt_checkout);
		ssd = (TextView) v.findViewById(R.id.ssd);
		shopnow = (LinearLayout) v.findViewById(R.id.shopnow);
		_rel = (RelativeLayout) v.findViewById(R.id._rel);
		lvCartItems = (ListView) v.findViewById(R.id.ListViewCatalog);
		
		txt_checkout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Fragment newContent = new HomeActivtiy();
				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});
		
		
		
		tvTotalPrice = (TextView) v.findViewById(R.id.img_totalprise);
		//ssd = (LinearLayout) v. findViewById(R.id.ssd);
		
		
		LayoutInflater layoutInflater = getLayoutInflater(savedInstanceState);
		cartItemAdapter = new CartItemAdapter(getActivity(),R.layout.carddesign);
		if (!abc.isEmpty()) {
			MainActivity.dbHelpercart.deleteRecord();
			Cartdetails ck1 = new Cartdetails();
			ck1.execute();
		} else {
			cartItemAdapter.updateCartItems(getCartItems());
			lvCartItems.setAdapter(cartItemAdapter);
			MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()	+ "");
			tvTotalPrice.setText(String.valueOf(BigDecimal.valueOf(MainActivity.dbHelpercart.gettotalprice())));
			if(	MainActivity.dbHelpercart.countRecord()>0){
				//shopnow.setVisibility(View.GONE);
				ssd.setVisibility(View.GONE);
				//txt_checkout.setVisibility(View.VISIBLE);
				_rel.setVisibility(View.VISIBLE);
			}else{
				
				//shopnow.setVisibility(View.VISIBLE);
				ssd.setVisibility(View.VISIBLE);
				//txt_checkout.setVisibility(View.GONE);
				_rel.setVisibility(View.GONE);
			}
		}
		
	
		
		MainActivity.btn_close.setVisibility(View.GONE);
		MainActivity.autoComplete.setVisibility(View.GONE);
		MainActivity.btn_logo.setVisibility(View.VISIBLE);
		MainActivity.btn_card.setVisibility(View.VISIBLE);
		MainActivity.btn_search.setVisibility(View.VISIBLE);
		MainActivity.tx.setVisibility(View.VISIBLE);
		
		shopnow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (MainActivity._ordd.getText().toString().contains("Login")) {
					if (MainActivity.dbHelpercart.countRecord() < 1) {
						Toast toast = Toast.makeText(getActivity(),
								"Your cart is Empty", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 90);
						toast.show();
					} else {
						Fragment newContent = new CheckOutSecond();
						if (newContent != null) {
							switchFragment(newContent);
						}
					}
				} else {
					if (MainActivity.dbHelpercart.countRecord() < 1) {
						Toast toast = Toast.makeText(getActivity(),
								"Your cart is Empty", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 90);
						toast.show();
					} else {
						Fragment newContent = new CheckOut();
						if (newContent != null) {
							switchFragment(newContent);
						}
					}
				}
			}
		});
		
		
		if(Network.isConnectionFast(context)){
			String abcs = PrefernceSettings.getUserId();
			Log.v("User Id", PrefernceSettings.getUserId());
			String res = BaseActivity.getJsonnew(getActivity(),Constant.ADDRESSLIST() + abcs);
			PrefernceSettings.setRESBILLINGSHIPPING(res);
			try{
				JSONArray jArray = new JSONArray(res);
				int number =  jArray.length();
				number = number-1 ;
				for(int i =0 ;i<jArray.length(); i++){
					JSONObject jObject = jArray.getJSONObject(i);
					String avv =  jObject.getString("aid");
				
					if(i==0){
					PrefernceSettings.setBILLING(avv);
					PrefernceSettings.setSHIPPING(avv);
					}
					}
			}catch (Exception e) {
				e.printStackTrace();	
			}
		}else{
			Toast toast = Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}

		try{
			Tracker t = ((GoogleAnalyticsApp) getActivity().getApplicationContext()).getTracker(TrackerName.APP_TRACKER);
			t.setScreenName("Add To Cart");
			t.send(new HitBuilders.AppViewBuilder().build());
			String campaignData = "https://play.google.com/store/apps/details?id=com.cms.kilol&referrer=utm_source%3Dgoogle%26utm_medium%3Dcpc%26anid%3Dadmob";
			t.send(new HitBuilders.ScreenViewBuilder().setCampaignParamsFromUrl(campaignData).build());
			t.enableAutoActivityTracking(true);
		   }catch (Exception e) {
				e.printStackTrace();	
			}
		
		return v;
	}

	
	private List<CartItem> getCartItems() {
		  List<CartItem> cartItems = new ArrayList<CartItem>();
		  Cursor allitem = MainActivity.dbHelpercart.selectRecords();
		  if (allitem.moveToFirst()) {
		   do {
		    CartItem cartItem = new CartItem();
		    ItemDetails k = new ItemDetails();
		    k.setNid(allitem.getLong(1));
		    k.setTitle(allitem.getString(2));
		    k.setThumb_Images(allitem.getString(3));
		    k.setQty(allitem.getInt(4));
		    Log.v("", allitem.getString(5));
		    k.setPrice(allitem.getString(5));
		    k.setSizeselected(allitem.getString(6));
		    k.setmax(allitem.getString(7));
		    cartItem.setProduct(k);
		    cartItem.setQuantity(allitem.getInt(4));
		    Log.v("", allitem.getString(4));
		    cartItems.add(cartItem);
		   } while (allitem.moveToNext());
		  }
		  allitem.close();

		
		  return cartItems;
		 }
	

	private void switchFragment(Fragment fragment) {
		getActivity(). getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.addToBackStack("my_fragment").commitAllowingStateLoss();
	}

	public void onBackPressed() {
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		GoogleAnalytics.getInstance(getActivity()).reportActivityStart(getActivity());
	}


	@Override
	public void onStop() {
		super.onStop();
		GoogleAnalytics.getInstance(getActivity()).reportActivityStop(getActivity());
	}
	
	
	class Cartdetails extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();
			String res = BaseActivity.getJsonnew(getActivity(),	Constant.CartDetails() + abc);

			try {
				JSONArray jArray = new JSONArray(res);
				int number = jArray.length();
				number = number - 1;
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jObject = jArray.getJSONObject(i);

					ItemDetails k = new ItemDetails();
					k.setNid(Long.parseLong((String) jObject.getString("nid")));
					String Title = jObject.getString("title");
					if (Title.contains("GV")) {
						Title = Title + "|" + jObject.getString("message")
								+ "|" + jObject.getString("mail");
						k.setTitle(Title);
					} else {
						k.setTitle(jObject.getString("title"));
					}
					Log.v("Title", k.getTitle());
					String price = jObject.getString("price");
					double f = Double.parseDouble(price);
					k.setPrice(String.format("%.2f", new BigDecimal(f)));
					k.setQty(Integer.parseInt((String) jObject.getString("qty")));
					k.setThumb_Images(((String) jObject.getString("thumb_images")));
					k.setSizeselected(((String) jObject.getString("size_id")));
					k.setmax(jObject.getString("total_stock"));
					Log.v("", jObject.getString("title"));
					itemListsys.add(k);

				}

			} catch (Exception e) {
				e.printStackTrace();
				return "not";
			}
			return null;
		}

		protected void onPostExecute(String args) {

			if (itemListsys.size() > 0) {

				for (int i = 0; i < itemListsys.size(); i++) {
					MainActivity.dbHelpercart.inserRecord(
							((ItemDetails) itemListsys.get(i)).getNid(),
							((ItemDetails) itemListsys.get(i)).getTitle(),
							((ItemDetails) itemListsys.get(i)).getThumb_Images(),
							((ItemDetails) itemListsys.get(i)).getQty(),
							(String) ((ItemDetails) itemListsys.get(i)).getPrice(), 
							((ItemDetails) itemListsys.get(i)).getSizeselected(),
							((ItemDetails) itemListsys.get(i)).getmax());

				}
				 
			}
			
			cartItemAdapter.updateCartItems(getCartItems());

			lvCartItems.setAdapter(cartItemAdapter);
			if(	cartItemAdapter.getCount()>0){
				ssd.setVisibility(View.GONE);
				_rel.setVisibility(View.VISIBLE);
			}else{
				ssd.setVisibility(View.VISIBLE);
				_rel.setVisibility(View.GONE);
			
			}
			MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()	+ "");
			tvTotalPrice.setText(String.valueOf(BigDecimal.valueOf(MainActivity.dbHelpercart.gettotalprice())));
		}
	}
	@Override
	public void onResume(){
	    super.onResume();

	}
	
*/
	
}