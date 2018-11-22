package com.bluelemon.bluelemon;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DocumentsFragment extends Fragment {

    private BaseActivity activity;
    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documents, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        pager = view.findViewById(R.id.view_pager) ;
        pager.setAdapter(new PagerAdapter(activity.getSupportFragmentManager(), activity));
        tabLayout.setupWithViewPager(pager);
        return view;
    }
}
