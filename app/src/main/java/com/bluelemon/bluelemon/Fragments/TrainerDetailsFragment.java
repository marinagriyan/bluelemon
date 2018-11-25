package com.bluelemon.bluelemon.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.DocumentsMainPagerAdapter;
import com.bluelemon.bluelemon.Adapters.TrainingsPagerAdapter;
import com.bluelemon.bluelemon.R;

public class TrainerDetailsFragment extends Fragment implements View.OnClickListener {
    private MainActivity activity;
    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trainer_details, container, false);
        activity.showBack(true);

        tabLayout = view.findViewById(R.id.tab_layout);
        pager = view.findViewById(R.id.view_pager);
        FragmentManager fm = getChildFragmentManager();
        TrainingsPagerAdapter adapter = new TrainingsPagerAdapter(fm, activity);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
