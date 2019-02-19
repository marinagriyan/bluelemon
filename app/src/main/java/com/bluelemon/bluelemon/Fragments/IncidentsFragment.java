package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.DocumentsMainPagerAdapter;
import com.bluelemon.bluelemon.Adapters.IncidentsMainAdapter;
import com.bluelemon.bluelemon.R;

public class IncidentsFragment extends Fragment implements  View.OnClickListener{

    private MainActivity activity;
    private TabLayout tabLayout;
    private ViewPager pager;
    private AddIncidentFragment addIncidentFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_incidents_main, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        pager = view.findViewById(R.id.view_pager);
        FragmentManager fm = getChildFragmentManager();
        IncidentsMainAdapter adapter = new IncidentsMainAdapter(fm, activity);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    public void setAddIncidentFragment(AddIncidentFragment addIncidentFragment) {
        this.addIncidentFragment = addIncidentFragment;
    }

    public AddIncidentFragment getAddIncidentFragment() {
        return addIncidentFragment;
    }
}
