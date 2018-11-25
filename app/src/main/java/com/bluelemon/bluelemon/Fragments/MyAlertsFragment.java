package com.bluelemon.bluelemon.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelemon.bluelemon.Adapters.AlertItemsAdapter;
import com.bluelemon.bluelemon.Models.AlertsModel;
import com.bluelemon.bluelemon.Models.SingleAlert;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class MyAlertsFragment extends Fragment {
    private Activity activity;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_alerts, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        List<AlertsModel> sections = new ArrayList<>();

        List<SingleAlert> childList = new ArrayList<>();
        childList.add(new SingleAlert("Fire 1", "3:35 PM", "Fire", "Marketing"));
        childList.add(new SingleAlert("Fire 2", "7:45 PM", "Fire", "Marketing"));

        sections.add(new AlertsModel("Today", childList));

        childList.add(new SingleAlert("Fire 3", "6:25 AM", "Fire", "Marketing"));

        sections.add(new AlertsModel("Yesterday", childList));

        recyclerView.setAdapter(new AlertItemsAdapter(activity, sections));

        return view;
    }

}
