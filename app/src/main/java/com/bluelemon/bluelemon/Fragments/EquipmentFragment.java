package com.bluelemon.bluelemon.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.DocumentsAdapter;
import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

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
