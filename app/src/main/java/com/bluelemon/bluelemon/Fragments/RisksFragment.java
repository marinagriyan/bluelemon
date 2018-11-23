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
import com.bluelemon.bluelemon.Adapters.DocumentsAdapter;
import com.bluelemon.bluelemon.Adapters.RisksAdapter;
import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.Models.RiskModel;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class RisksFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private List<RiskModel> list = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risks, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        list.add(new RiskModel("Live", "Risk Title", "June 12, 2018", "AH0928117"));
        list.add(new RiskModel("Live", "Risk Title 2", "February 20, 2018", "AH0928117"));

        recyclerView.setAdapter(new RisksAdapter(activity, list));

        return view;
    }
}
