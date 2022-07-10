package com.tutorialsee;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


@SuppressLint({ "SetJavaScriptEnabled", "SimpleDateFormat" })
public class ProductDetails extends Fragment implements OnClickListener,
		OnItemSelectedListener {
	RelativeLayout _lin, _lin1, _lin2;
	protected static final String ARG_DETIALS_ID = null;
	ArrayList<HashMap<String, String>> array1;
	//List<ItemDetails> itemList = new ArrayList<ItemDetails>();
	LinearLayout lay, lay1, lay2, addtocart, confim;
	ImageView _btn, _dbtn, _dbtn1, _dbtn2, _img_product, imageviewnew;
	//ItemDetails item;
	Dialog dialog;
	ViewPager mViewPager;
	private ImageView[] dots;
	private int currentIndex;
	//Product_Adapter p_adap;
	TextView _txt_name, _txt_rs, txt_webviewtext,txt_rs, txt_costrs, txt_rs_id, phoneNumber;
	private WebView wbView, wbview1, wbview2;
	boolean back_a = false;
	boolean back = false;
	static String msg = "";
	//ItemDetails mItems;
	int flag = 1;
	ImageView btn_search, btn_logo, backButton;
	EditText edt_search;
	String flg = "L";
	Context context;
	HashMap<String, String> sizee = new HashMap<String, String>();
	private static String[] pics;
	DateFormat df;
	String date;
	private List<View> views;
	private ViewPagerAdapter vpAdapter;
	String value;
	ArrayAdapter<String> dataAdapters;
	int pidtest;
	String quatest;
	TextView addtocarsst;
	String size_name;
	int showsixe = 0;
	int showsixes = 0;
	JewelleryModel jewellery;

	public ProductDetails(JewelleryModel jewelleryModel) {
		jewellery = jewelleryModel;
	}

	@SuppressLint({ "CutPasteId", "SetJavaScriptEnabled" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.lp = 2;

		/*if (getArguments().containsKey(ARG_DETIALS_ID)) {
			mItems = (ItemDetails) getArguments().getSerializable(
					ARG_DETIALS_ID);

		}*/



	}

	@SuppressWarnings({ "unused", "deprecation" })
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.product_details, container, false);

		MainActivity.actionBar.hide();
		context = container.getContext();

		backButton = (ImageView) v.findViewById(R.id.backButton);
//
//
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();

			}
		});
//


		//PrefernceSettings.openDataBase(context);

		/*String tt = mItems.getImagePath1();
		Log.v("tag", mItems.getImagePath1());
		if ((mItems.getFullImages() != null)
				&& !(mItems.getFullImages().contains("null"))) {
			tt = tt + "," + mItems.getFullImages();
		}
		if ((mItems.getImagePath() != null)
				&& !(mItems.getImagePath().contains("null"))) {
			tt = tt + "," + mItems.getImagePath();
		}
		Log.v("tt", tt);
		pics = tt.split(",");
		Log.v("tt1", "" + pics.length);

		MainActivity.btn_close.setVisibility(View.GONE);
		MainActivity.autoComplete.setVisibility(View.GONE);
		MainActivity.btn_logo.setVisibility(View.VISIBLE);
		MainActivity.btn_card.setVisibility(View.VISIBLE);
		MainActivity.btn_search.setVisibility(View.VISIBLE);
		MainActivity.tx.setVisibility(View.VISIBLE);
*/
//		lay = (LinearLayout) v.findViewById(R.id.lay);
//		lay1 = (LinearLayout) v.findViewById(R.id.lay1);
//		lay2 = (LinearLayout) v.findViewById(R.id.lay2);
		addtocart = (LinearLayout) v.findViewById(R.id.addtocart);
		addtocarsst = (TextView) v.findViewById(R.id.addtocarsst);
//		confim = (LinearLayout) v.findViewById(R.id.confim);

//		_dbtn = (ImageView) v.findViewById(R.id.gobtn);
//		_dbtn1 = (ImageView) v.findViewById(R.id.gobtnn);
//		_dbtn2 = (ImageView) v.findViewById(R.id.gobtnnn);
//		_lin = (RelativeLayout) v.findViewById(R.id.linn);
//		_lin1 = (RelativeLayout) v.findViewById(R.id.linnn);
//		_lin2 = (RelativeLayout) v.findViewById(R.id.linnnn);
		
		imageviewnew = (ImageView) v.findViewById(R.id.imageviewnew);

		TextView productidview = (TextView) v.findViewById(R.id.dsfdfgf);

		TextView productDesc = (TextView) v.findViewById(R.id.textView3);


		imageviewnew.setImageResource(jewellery.getimgId());

		productidview.setText("Product Id : " + jewellery.getProdId());
		productDesc.setText("Product Description : "+ jewellery.getDesc());

		addtocarsst.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// redirect to contact page
				Fragment newContent = new ContactUs();

				if (newContent != null) {
					switchFragment(newContent);
				}
			}
		});


		_txt_name = (TextView) v.findViewById(R.id.txt_name);

		_txt_rs = (TextView) v.findViewById(R.id.txt_rs_id);

		txt_rs = (TextView) v.findViewById(R.id.txt_rs);
		txt_costrs = (TextView) v.findViewById(R.id.txt_costrs);
		_txt_name.setText(jewellery.getCourse_name());
		_txt_rs.setText(jewellery.getCourse_name());

		phoneNumber = (TextView) v.findViewById(R.id.phoneNumber);



		txt_rs_id = (TextView) v.findViewById(R.id.txt_rs_id);

		txt_rs_id.setText(jewellery.getProdId());

		txt_rs.setText(jewellery.getType());

		phoneNumber.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

//				String number = ("tel:" + "999999999");
//				mIntent = new Intent(Intent.ACTION_CALL);
//				mIntent.setData(Uri.parse(number));
//// Here, thisActivity is the current activity
//				if (ContextCompat.checkSelfPermission(thisActivity,
//						Manifest.permission.CALL_PHONE)
//						!= PackageManager.PERMISSION_GRANTED) {
//
//					ActivityCompat.requestPermissions(thisActivity,
//							new String[]{Manifest.permission.CALL_PHONE},
//							MY_PERMISSIONS_REQUEST_CALL_PHONE);
//
//					// MY_PERMISSIONS_REQUEST_C


				}
		});





//		_txt_rs.setText(mItems.getPrice());
//		if (mItems.getCost_Price() != null) {
//			if (mItems.getCost_Price().charAt(0) == '0') {
//				txt_costrs.setVisibility(View.GONE);
//			} else {
//				txt_costrs.setVisibility(View.VISIBLE);
//				txt_costrs.setText(mItems.getCost_Price());
//			}
//		}

		/*Picasso.with(getActivity())
				.load("http://homemonkey.com/timthumb.php?src="
						+ mItems.getThumb_Images().replace(" ", "%20")
						+ "&w=400&h=0").placeholder(R.drawable.dummyfirst)
				.into(imageviewnew);*/

//		WebView wbView = (WebView) v.findViewById(R.id.webView);
//		wbView.getSettings().setJavaScriptEnabled(true);
		//wbView.loadData(mItems.getBody(), "text/html; charset=UTF-8", null);

//		WebView wbView1 = (WebView) v.findViewById(R.id.webviewcaresss);
//		wbView1.getSettings().setJavaScriptEnabled(true);
	//	wbView1.loadData(mItems.getCare().replace("\n", "<br>"),"text/html; charset=UTF-8", null);
//
//		WebView wbView2 = (WebView) v.findViewById(R.id.webViewsizechart);
//		wbView2.getSettings().setJavaScriptEnabled(true);
		///wbView2.loadData(mItems.getSize_Chart(), "text/html; charset=UTF-8",null);

//		String stringLitersOfPetrol = null;
//		df = new SimpleDateFormat("HH:mm");

//		addtocart.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//			/*	String abc = PrefernceSettings.getUserId();
//				mItems.setmax(PrefernceSettings.getMax());
//				showsixe = 0;
//				try {
//					showsixe = MainActivity.dbHelpercart
//							.countRecordbynid(mItems.getNid());
//				} catch (Exception ex) {
//				}
//				MainActivity.dbHelpercart.inserRecord(mItems.getNid(),
//						mItems.getTitle(), mItems.getThumb_Images(),
//						Integer.valueOf(PrefernceSettings.getQuantity()),
//						mItems.getPrice().toString(), mItems.getSizeselected(),
//						mItems.getmax());
//				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()
//						+ "");
//
//				if (!abc.isEmpty()) {
//					pidtest = (int) mItems.getNid();
//					quatest = PrefernceSettings.getQuantity();
//					size_name = (String) mItems.getSizeselected();
//					Cartcheck ck = new Cartcheck();
//					ck.execute();
//				} else {
//					Log.v("aa", "" + showsixe);
//					Log.v("bb", "" + mItems.getmax());
//					if (Integer.parseInt(mItems.getmax()) > showsixe) {
//						Toast toast = Toast.makeText(context, "Added to Cart",
//								Toast.LENGTH_SHORT);
//						toast.setGravity(Gravity.CENTER, 0, 0);
//						toast.show();
//					} else {
//						Toast toast = Toast.makeText(context, "There is only "
//								+ Integer.parseInt(mItems.getmax())
//								+ " items in stock", Toast.LENGTH_SHORT);
//						toast.setGravity(Gravity.CENTER, 0, 0);
//						toast.show();
//					}
//
//					 * Fragment newContent = new AddCart(); if (newContent !=
//					 * null) { switchFragment(newContent); }
//
//				}
//
//				try {
//					InputMethodManager input = (InputMethodManager) getActivity()
//							.getSystemService(Activity.INPUT_METHOD_SERVICE);
//					input.hideSoftInputFromWindow(getActivity()
//							.getCurrentFocus().getWindowToken(), 0);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//*/
//			}
//
//		});

	

		return v;
	}


	@Override
	public void onClick(View v) {
		if (v == lay) {
			if (_lin.getVisibility() == View.GONE) {
				_lin.setVisibility(View.VISIBLE);

				/*MainActivity.btn_close.setVisibility(View.GONE);
				MainActivity.autoComplete.setVisibility(View.GONE);
				MainActivity.btn_logo.setVisibility(View.VISIBLE);
				MainActivity.btn_card.setVisibility(View.VISIBLE);
				MainActivity.btn_search.setVisibility(View.VISIBLE);
				MainActivity.tx.setVisibility(View.VISIBLE);*/
				try {
					InputMethodManager input = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					input.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				back = true;
			}
		} else if (v == _dbtn) {
			@SuppressWarnings("unused")
			Animation slideUp = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_up);
			Animation slideDown = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_down);
			if (_lin.getVisibility() == View.VISIBLE) {
				// MainActivity.actionBar.show();
				_lin.startAnimation(slideDown);
				_lin.setVisibility(View.GONE);

				/*MainActivity.btn_close.setVisibility(View.GONE);
				MainActivity.autoComplete.setVisibility(View.GONE);
				MainActivity.btn_logo.setVisibility(View.VISIBLE);
				MainActivity.btn_card.setVisibility(View.VISIBLE);
				MainActivity.btn_search.setVisibility(View.VISIBLE);
				MainActivity.tx.setVisibility(View.VISIBLE);*/
				try {
					InputMethodManager input = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					input.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				back = false;
			}

		} else if (v == lay1) {
			if (_lin1.getVisibility() == View.GONE) {
				_lin1.setVisibility(View.VISIBLE);

				/*MainActivity.btn_close.setVisibility(View.GONE);
				MainActivity.autoComplete.setVisibility(View.GONE);
				MainActivity.btn_logo.setVisibility(View.VISIBLE);
				MainActivity.btn_card.setVisibility(View.VISIBLE);
				MainActivity.btn_search.setVisibility(View.VISIBLE);
				MainActivity.tx.setVisibility(View.VISIBLE);*/
				try {
					InputMethodManager input = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					input.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				back = true;
			}
		} else if (v == _dbtn1) {
			@SuppressWarnings("unused")
			Animation slideUp = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_up);
			Animation slideDown = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_down);
			if (_lin1.getVisibility() == View.VISIBLE) {
				// MainActivity.actionBar.show();
				_lin1.startAnimation(slideDown);
				_lin1.setVisibility(View.GONE);

				/*MainActivity.btn_close.setVisibility(View.GONE);
				MainActivity.autoComplete.setVisibility(View.GONE);
				MainActivity.btn_logo.setVisibility(View.VISIBLE);
				MainActivity.btn_card.setVisibility(View.VISIBLE);
				MainActivity.btn_search.setVisibility(View.VISIBLE);
				MainActivity.tx.setVisibility(View.VISIBLE);*/
				try {
					InputMethodManager input = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					input.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				back = false;
			}
		} else if (v == lay2) {
			if (_lin2.getVisibility() == View.GONE) {
				_lin2.setVisibility(View.VISIBLE);

				/*MainActivity.btn_close.setVisibility(View.GONE);
				MainActivity.autoComplete.setVisibility(View.GONE);
				MainActivity.btn_logo.setVisibility(View.VISIBLE);
				MainActivity.btn_card.setVisibility(View.VISIBLE);
				MainActivity.btn_search.setVisibility(View.VISIBLE);
				MainActivity.tx.setVisibility(View.VISIBLE);*/
				try {
					InputMethodManager input = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					input.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				back = true;
			}
		} else if (v == _dbtn2) {
			@SuppressWarnings("unused")
			Animation slideUp = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_up);
			Animation slideDown = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_down);
			if (_lin2.getVisibility() == View.VISIBLE) {
				// MainActivity.actionBar.show();
				_lin2.startAnimation(slideDown);
				_lin2.setVisibility(View.GONE);

				/*MainActivity.btn_close.setVisibility(View.GONE);
				MainActivity.autoComplete.setVisibility(View.GONE);
				MainActivity.btn_logo.setVisibility(View.VISIBLE);
				MainActivity.btn_card.setVisibility(View.VISIBLE);
				MainActivity.btn_search.setVisibility(View.VISIBLE);
				MainActivity.tx.setVisibility(View.VISIBLE);*/
				try {
					InputMethodManager input = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					input.hideSoftInputFromWindow(getActivity()
							.getCurrentFocus().getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				back = false;
			}
		}

	}

	

	public class ViewDialog implements OnClickListener, ViewPager.OnPageChangeListener {
		@SuppressWarnings("deprecation")
		public <AddListAddressd> void showDialog(Activity getActivity,
				String msg) {
			dialog = new Dialog(getActivity);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCancelable(false);
			dialog.setContentView(R.layout.imageviewfull);
			ImageView gobtnnsssdd = (ImageView) dialog
					.findViewById(R.id.gobtnnsssdd);
			mViewPager = (ViewPager) dialog.findViewById(R.id.view_pager);
			//mViewPager.setAdapter(new TouchImageAdapter());

			gobtnnsssdd.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.cancel();
				}
			});
			vpAdapter = new ViewPagerAdapter(views);

			mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				public void onPageScrollStateChanged(int state) {

				}

				public void onPageScrolled(int position, float positionOffset,
						int positionOffsetPixels) {

				}

				public void onPageSelected(int position) {
				/*	if (position < 0 || position > pics.length - 1
							|| currentIndex == position) {
						return;
					}
					dots[position].setEnabled(false);
					dots[currentIndex].setEnabled(true);
					currentIndex = position;*/
				}
			});

			//initDots();

			views = new ArrayList<View>();
			LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

		/*	for (int i = 0; i < pics.length; i++) {

				if (pics[i] != null) {

					if (!pics[i].toString().contains("null")) {
						ImageView iv = new ImageView(getActivity());
						iv.setLayoutParams(mParams);
						Log.v("aaaaaaaaaaaaaaaaaaaaaaaaa", pics[i] + i + ","
								+ pics.length);
						Picasso.with(getActivity())
								.load(pics[i].toString().replace(" ", "%20")
										+ "&w=0&h=0").into(iv);
						views.add(iv);
					}
				}
			}*/

			dialog.show();
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog.getWindow().getAttributes());
			lp.width = WindowManager.LayoutParams.MATCH_PARENT;
			lp.height = WindowManager.LayoutParams.MATCH_PARENT;
			dialog.getWindow().setAttributes(lp);
		}

	/*	class TouchImageAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return pics.length;
			}

			@Override
			public View instantiateItem(ViewGroup container, int position) {

				// img.setImageResource(pics[position]);
				if (pics[position] != null
						&& (!pics[position].toString().contains("null"))) {
					TouchImageView img = new TouchImageView(
							container.getContext());
					Picasso.with(container.getContext())
							.load(pics[position].replace(" ", "%20"))
							.placeholder(R.drawable.dammyicon).into(img);

					container.addView(img,
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
					return img;
				} else {
					return null;
				}

			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

		}*/

		// View Pager code
		private void initDots() {
			// Activity v = null;
			LinearLayout ll = (LinearLayout) dialog.findViewById(R.id.ll);
			dots = new ImageView[pics.length];
			for (int i = 0; i < 3; i++) {
				ImageView ivvvv = (ImageView) ll.getChildAt(i);
				ivvvv.setVisibility(View.GONE);
			}
			for (int i = 0; i < pics.length; i++) {
				dots[i] = (ImageView) ll.getChildAt(i);
				dots[i].setVisibility(View.VISIBLE);
				dots[i].setEnabled(true);
				dots[i].setOnClickListener((OnClickListener) context);
				dots[i].setTag(i);
			}

			currentIndex = 0;
			dots[currentIndex].setEnabled(false);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			// setCurDot(arg0);
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int position = (Integer) arg0.getTag();

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	private void switchFragment(Fragment fragment) {
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment)
				.addToBackStack("my_fragment").commitAllowingStateLoss();
	}

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStack();
		}

	}

	
	@Override
	public void onResume() {
		super.onResume();

	}

	/*class Cartcheck extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();

			String UERRR = Constant.Cart() + abc + "&pid=" + pidtest + ""
					+ "&qty=" + quatest + "&size_name=" + size_name
					+ "&action=add";
			Log.v("New URL", UERRR);

			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);
				msg = jObject.getString("response_message");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {
			// Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
			Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			if (Network.isConnectionFast(context)) {
				itemList.clear();
				Cartdetails ck = new Cartdetails();
				ck.execute();
			} else {
				Toast.makeText(context, "Person information is null",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	class Cartdetails extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// MyProgressDialog.show(context);
		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			String res = BaseActivity.getJsonnew(getActivity(),
					Constant.CartDetails() + abc);

			try {
				JSONArray jArray = new JSONArray(res);
				int number = jArray.length();
				number = number - 1;

				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jObject = jArray.getJSONObject(i);

					ItemDetails k = new ItemDetails();
					k.setNid(Long.parseLong((String) jObject.getString("nid")));
					k.setTitle(jObject.getString("title"));
					String price = jObject.getString("price");
					double f = Double.parseDouble(price);
					k.setPrice(String.format("%.2f", new BigDecimal(f)));
					k.setQty(Integer.parseInt((String) jObject.getString("qty")));
					k.setThumb_Images(((String) jObject
							.getString("thumb_images")));
					k.setSizeselected(((String) jObject.getString("size_id")));
					k.setmax(jObject.getString("total_stock"));
					Log.v("", jObject.getString("title"));
					itemList.add(k);

				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return null;
		}

		protected void onPostExecute(String args) {
			// MyProgressDialog.close(context);

			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					MainActivity.dbHelpercart
							.inserRecord(((ItemDetails) itemList.get(i))
									.getNid(), ((ItemDetails) itemList.get(i))
									.getTitle(),
									((ItemDetails) itemList.get(i))
											.getThumb_Images(),
									((ItemDetails) itemList.get(i)).getQty(),
									(String) ((ItemDetails) itemList.get(i))
											.getPrice(),
									((ItemDetails) itemList.get(i))
											.getSizeselected(),
									((ItemDetails) itemList.get(i)).getmax());

				}
				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()
						+ "");
			}

		}
	}

	class Cartchecks extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();

			String UERRR = Constant.Cart() + abc + "&pid=" + pidtest + ""
					+ "&qty=" + quatest + "&size_name=" + size_name
					+ "&action=add";
			Log.v("New URL", UERRR);

			String res = BaseActivity.getJsonnew(context, UERRR);
			Log.v("URL", res);

			try {
				JSONObject jObject = new JSONObject(res);
				msg = jObject.getString("response_message");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String args) {
			// Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
			Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			if (Network.isConnectionFast(context)) {
				itemList.clear();
				Cartdetailss ck = new Cartdetailss();
				ck.execute();
			} else {
				Toast.makeText(context, "Person information is null",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	class Cartdetailss extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// MyProgressDialog.show(context);
		}

		@Override
		protected String doInBackground(String... params) {
			String abc = PrefernceSettings.getUserId();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			String res = BaseActivity.getJsonnew(getActivity(),
					Constant.CartDetails() + abc);

			try {
				JSONArray jArray = new JSONArray(res);
				int number = jArray.length();
				number = number - 1;

				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jObject = jArray.getJSONObject(i);

					ItemDetails k = new ItemDetails();
					k.setNid(Long.parseLong((String) jObject.getString("nid")));
					k.setTitle(jObject.getString("title"));
					String price = jObject.getString("price");
					double f = Double.parseDouble(price);
					k.setPrice(String.format("%.2f", new BigDecimal(f)));
					k.setQty(Integer.parseInt((String) jObject.getString("qty")));
					k.setThumb_Images(((String) jObject
							.getString("thumb_images")));
					k.setSizeselected(((String) jObject.getString("size_id")));
					k.setmax(jObject.getString("total_stock"));
					Log.v("", jObject.getString("title"));
					itemList.add(k);

				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return null;
		}

		protected void onPostExecute(String args) {
			// MyProgressDialog.close(context);

			if (itemList.size() > 0) {
				for (int i = 0; i < itemList.size(); i++) {
					MainActivity.dbHelpercart
							.inserRecord(((ItemDetails) itemList.get(i))
									.getNid(), ((ItemDetails) itemList.get(i))
									.getTitle(),
									((ItemDetails) itemList.get(i))
											.getThumb_Images(),
									((ItemDetails) itemList.get(i)).getQty(),
									(String) ((ItemDetails) itemList.get(i))
											.getPrice(),
									((ItemDetails) itemList.get(i))
											.getSizeselected(),
									((ItemDetails) itemList.get(i)).getmax());

				}
				MainActivity.tx.setText(MainActivity.dbHelpercart.countRecord()
						+ "");
			}

			Fragment newContent = new CheckOut();
			if (newContent != null) {
				switchFragment(newContent);
			}

			
		}
	}*/

}