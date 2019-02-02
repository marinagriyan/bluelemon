package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.R;

public class AddIncidentFragment extends Fragment implements View.OnClickListener{
    private MainActivity activity;
    private View generalInformationLayout;
    private View yourDetailsLayout;
    private TextView generalInformation;
    private TextView yourDetails;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_incident, container, false);
        activity.showBack(true);

        generalInformationLayout = view.findViewById(R.id.general_information_layout);
        yourDetailsLayout = view.findViewById(R.id.your_details_layout);

        view.findViewById(R.id.complete).setOnClickListener(this);
        generalInformation = view.findViewById(R.id.general_information);
        generalInformation.setOnClickListener(this);
        yourDetails = view.findViewById(R.id.your_details);
        yourDetails.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.complete:
                activity.setFragment(new IncidentsFragment());
                break;
            case R.id.general_information:
                switchLayoutVisibility(generalInformationLayout, generalInformation);
                break;
            case R.id.your_details:
                switchLayoutVisibility(yourDetailsLayout, yourDetails);
                break;
        }
    }

    private void switchLayoutVisibility(View layout, TextView title){
        if (layout.getVisibility() == View.VISIBLE){
            layout.setVisibility(View.GONE);
            title.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.plus, 0);
        } else {
            layout.setVisibility(View.VISIBLE);
            title.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.minus, 0);
        }
    }
}
