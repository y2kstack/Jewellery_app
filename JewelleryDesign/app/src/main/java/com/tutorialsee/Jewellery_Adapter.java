package com.tutorialsee;



import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import java.util.ArrayList;

public class Jewellery_Adapter extends ArrayAdapter<JewelleryModel> {
    public Jewellery_Adapter(@NonNull Context context, ArrayList<JewelleryModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.jewellery_listitem, parent, false);
        }
        JewelleryModel JewelleryModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.idIVcourse);
        TextView courseId = listitemView.findViewById(R.id.dsfdfgf);
        courseTV.setText(JewelleryModel.getCourse_name());
        courseIV.setImageResource(JewelleryModel.getimgId());
        return listitemView;
    }
}