package com.bluelemon.bluelemon.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.bluelemon.bluelemon.R;

public class TrainingFragment extends Fragment {
    private GridLayout grid;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_training, container, false);

        grid = view.findViewById(R.id.staff);
        grid.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i ++) {
                    View staff = inflater.inflate(R.layout.staff_card, container, false);
                    staff.getLayoutParams().width = grid.getWidth() / 2;
                    grid.addView(staff);

                }
            }
        });

        return view;
    }

}
