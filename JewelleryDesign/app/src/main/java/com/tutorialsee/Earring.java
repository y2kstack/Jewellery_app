package com.tutorialsee;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Earring extends Fragment {

    Context context;
    LinearLayout filter, sort, click;
    GridView jewelleryGridView;
    ArrayList<JewelleryModel> JewelleryModelArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.lp = 2;

        JewelleryModelArrayList = new ArrayList<JewelleryModel>();

        JewelleryModelArrayList.add(new JewelleryModel("DSA", R.drawable.e1, "lorem ipsium", "e1a", "e1b"));
        JewelleryModelArrayList.add(new JewelleryModel("JAVA", R.drawable.e2, "lorem ipsium", "e2a", "e2b"));
        JewelleryModelArrayList.add(new JewelleryModel("C++", R.drawable.e3, "lorem ipsium", "e3a", "e3b"));
        JewelleryModelArrayList.add(new JewelleryModel("Python", R.drawable.e4, "lorem ipsium", "e4a", "e4b"));
        JewelleryModelArrayList.add(new JewelleryModel("Javascript", R.drawable.e5, "lorem ipsium", "e5a", "e5b"));
        JewelleryModelArrayList.add(new JewelleryModel("DSA", R.drawable.e6, "lorem ipsium", "e6a", "e6b"));
        JewelleryModelArrayList.add(new JewelleryModel("DSA", R.drawable.e1, "lorem ipsium", "e1a", "e1b"));
        JewelleryModelArrayList.add(new JewelleryModel("JAVA", R.drawable.e2, "lorem ipsium", "e2a", "e2b"));
        JewelleryModelArrayList.add(new JewelleryModel("C++", R.drawable.e3, "lorem ipsium", "e3a", "e3b"));
        JewelleryModelArrayList.add(new JewelleryModel("Python", R.drawable.e4, "lorem ipsium", "e4a", "e4b"));
        JewelleryModelArrayList.add(new JewelleryModel("Javascript", R.drawable.e5, "lorem ipsium", "e5a", "e5b"));
        JewelleryModelArrayList.add(new JewelleryModel("DSA", R.drawable.e6, "lorem ipsium", "e6a", "e6b"));

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.jewellery_list, container, false);
        MainActivity.actionBar.show();
        context = container.getContext();

        jewelleryGridView = v.findViewById(R.id.idGridView);

        Jewellery_Adapter adapter = new Jewellery_Adapter(getActivity().getApplicationContext(),
                JewelleryModelArrayList);
        jewelleryGridView.setAdapter(adapter);

        jewelleryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {

                // DO something

                Fragment newContent = new ProductDetails(JewelleryModelArrayList.get(position));
                if (newContent != null) {
                    switchFragment(newContent);
                }
                System.out.print(JewelleryModelArrayList.get(position));

            }
        });

        // sort = (LinearLayout) v.findViewById(R.id.sort);
        // filter = (LinearLayout) v.findViewById(R.id.filter);
        //
        // click = (LinearLayout) v.findViewById(R.id.click);
        // click.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View arg0) {
        // Fragment newContent = new ProductDetails();
        // if (newContent != null) {
        // switchFragment(newContent);
        // }

        // }
        // });
        // filter.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View arg0) {
        //// Jewellery.ViewDialog alert = new Diamond.ViewDialog();
        //// alert.showDialog(getActivity(), "Error de conexi�n al servidor");
        //
        //
        // }
        // });
        // sort.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View arg0) {
        //// Jewellery.ViewDialogs alerts = new Jewellery.ViewDialogs();
        //// alerts.showDialog(getActivity(), "Error de conexi�n al servidor");
        //
        //
        // }
        // });

        return v;
    }

    public class ViewDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.filter);

            TextView text = (TextView) dialog.findViewById(R.id.cancle);
            // text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btnLogin);
            dialogButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dialog.dismiss();
                }
            });
            text.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

    public class ViewDialogs {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.sort);

            // TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            // text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btnLogin);
            dialogButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
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
}
