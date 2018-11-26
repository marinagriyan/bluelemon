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
import com.bluelemon.bluelemon.Adapters.AssignedIncidentsAdapter;
import com.bluelemon.bluelemon.Adapters.MyIncidentsAdapter;
import com.bluelemon.bluelemon.Models.IncidentsModel;
import com.bluelemon.bluelemon.Models.SingleIncident;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class AssignedIncidentsFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assigned_incidents, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        List<IncidentsModel> list = new ArrayList<>();

        List<SingleIncident> singleIncidentList = new ArrayList<>();

        singleIncidentList.add(new SingleIncident("2018 / ATH / 0001", "Fall from height", "Riddor Reportable", "Whipped", "Ritana M.", "John S."));
        singleIncidentList.add(new SingleIncident("2018 / ATH / 0001", "Fall from height", "Riddor Reportable", "Whipped", "Ritana M.", "John S."));

        list.add(new IncidentsModel("June 12, 2018", singleIncidentList));
        list.add(new IncidentsModel("June 12, 2018", singleIncidentList));

        recyclerView.setAdapter(new AssignedIncidentsAdapter(activity, list));
        return view;
    }

}
