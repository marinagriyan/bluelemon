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

import com.bluelemon.bluelemon.Adapters.CoursesAdapter;
import com.bluelemon.bluelemon.Adapters.MyAlertsAdapter;
import com.bluelemon.bluelemon.Models.AlertsModel;
import com.bluelemon.bluelemon.Models.CoursesModel;
import com.bluelemon.bluelemon.Models.SingleAlert;
import com.bluelemon.bluelemon.Models.SingleCourse;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        List<CoursesModel> sections = new ArrayList<>();

        List<SingleCourse> childList = new ArrayList<>();

        childList.add(new SingleCourse("How to speak Armenian", "Language Course"));
        childList.add(new SingleCourse("How to speak Armenian", "Language Course"));


        sections.add(new CoursesModel("June 12, 2018", childList));

        recyclerView.setAdapter(new CoursesAdapter(activity, sections));

        return view;
    }

}
