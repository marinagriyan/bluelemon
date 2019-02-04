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
import com.bluelemon.bluelemon.Models.FolderModel;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class EquipmentFragment extends Fragment {
    private MainActivity activity;
    private LinearLayout folders;
    private List<FolderModel> list = new ArrayList<>();

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
                activity.addFragment(new FolderEquipmentFragment(), 31);
            }
        });

        view.findViewById(R.id.show_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addFragment(new AllFoldersFragment(), 32);
            }
        });


        list.add(new FolderModel("Quarantine", "Count1"));
        list.add(new FolderModel("Missing", "Count1"));
        list.add(new FolderModel("Broken", "Count1"));

        for (int i = 0; i < list.size(); i ++){
            TextView folder = (TextView) inflater.inflate(R.layout.folder_category, container, false);
            folder.setBackground(activity.getDrawable(R.drawable.button_red));
            folder.setText(list.get(i).getTitle());
            folder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.addFragment(new AllFoldersFragment(), 32);
                }
            });
            folders.addView(folder);
        }

        return view;
    }
}
