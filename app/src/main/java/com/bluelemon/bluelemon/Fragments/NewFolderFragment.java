package com.bluelemon.bluelemon.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.EquipmentsAdapter;
import com.bluelemon.bluelemon.Models.EquipmentModel;
import com.bluelemon.bluelemon.R;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class NewFolderFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private List<EquipmentModel> list = new ArrayList<>();
    private View move;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_folder, container, false);

        activity.showBack(true);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        move = view.findViewById(R.id.move);

        list.add(new EquipmentModel("LX Mallion 001", "February 20, 2019", "180 Days", "ID 1543", "ELEX"));
        list.add(new EquipmentModel("LX Mallion 001", "February 20, 2019", "180 Days", "ID 1543", "ELEX"));

        recyclerView.setAdapter(new EquipmentsAdapter(activity, this, list));

        view.findViewById(R.id.add_equipment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    public void move(){
        move.setVisibility(View.VISIBLE);
    }
}
