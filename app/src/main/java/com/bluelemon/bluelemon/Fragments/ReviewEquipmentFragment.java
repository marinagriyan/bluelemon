package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.R;

public class ReviewEquipmentFragment extends Fragment {
    private MainActivity activity;
    private LinearLayout requirementsList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_equipment, container, false);


        requirementsList = view.findViewById(R.id.requirements);

        activity.showBack(true);

        for (int i = 0; i < 12; i ++) {
            TextView textView = (TextView) inflater.inflate(R.layout.requirement, container, false);
            if (i % 2 == 0) {
                textView.setBackgroundColor(activity.getResources().getColor(R.color.fieldBorderGray));
            }
            textView.setText("Rivets and fastenings in place and secure.");
            requirementsList.addView(textView);
        }

        return view;
    }

}
