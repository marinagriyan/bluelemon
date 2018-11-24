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

public class EquipmentFragment extends Fragment {
    private MainActivity activity;
    private LinearLayout folders;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);

        folders = view.findViewById(R.id.folders);

        view.findViewById(R.id.create_folder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new NewFolderFragment());
            }
        });

        TextView folder = (TextView) inflater.inflate(R.layout.folder_item, container, false);
        folder.setBackground(activity.getDrawable(R.drawable.button_red));
        folder.setText("Quarantine");
        folders.addView(folder);

        TextView folder2 = (TextView) inflater.inflate(R.layout.folder_item, container, false);
        folder2.setBackground(activity.getDrawable(R.drawable.button_yellow));
        folder2.setText("Missing");
        folders.addView(folder2);

        TextView folder3 = (TextView) inflater.inflate(R.layout.folder_item, container, false);
        folder3.setBackground(activity.getDrawable(R.drawable.button_red));
        folder3.setText("Broken");
        folders.addView(folder3);

        return view;
    }
}
