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
    private boolean isGeneralInfoVisible = true;
    private boolean isDetailsVisible = false;

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
                if (isGeneralInfoVisible) {
                    generalInformationLayout.setVisibility(View.GONE);
                    generalInformation.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.plus, 0);
                    isGeneralInfoVisible = false;
                } else {
                    generalInformationLayout.setVisibility(View.VISIBLE);
                    generalInformation.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.minus, 0);
                    isGeneralInfoVisible = true;
                }
                break;
            case R.id.your_details:
                if (isDetailsVisible) {
                    yourDetailsLayout.setVisibility(View.GONE);
                    yourDetails.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.plus, 0);
                    isDetailsVisible = false;
                } else {
                    yourDetailsLayout.setVisibility(View.VISIBLE);
                    yourDetails.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.minus, 0);
                    isDetailsVisible = true;
                }
                break;
        }
    }
}
