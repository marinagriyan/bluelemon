package com.bluelemon.bluelemon.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainingFragment extends Fragment {
    private GridLayout grid;
    private MainActivity activity;
    private HashMap<Integer, View> cards = new HashMap<>();
    private List<Integer> selectedIds = new ArrayList<>();
    private View searchLayout;
    private View completeLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_training, container, false);

        grid = view.findViewById(R.id.staff);
        grid.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i ++) {
                    View staff = inflater.inflate(R.layout.staff_card, container, false);
                    staff.getLayoutParams().width = grid.getWidth() / 2;
                    final int finalI = i;
                    staff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(selectedIds.size() > 0) {
                               select(finalI);
                            } else {
                                activity.addFragment(new TrainerDetailsFragment(), 11);
                            }
                        }
                    });
                    staff.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            select(finalI);
                            return true;
                        }
                    });
                    cards.put(i, staff);
                    grid.addView(staff);
                }
            }
        });

        searchLayout = view.findViewById(R.id.search_layout_container);
        completeLayout = view.findViewById(R.id.complete_layout);
        view.findViewById(R.id.complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSelectors(-1);
            }
        });
        return view;
    }

    private void select(int index){
        if (selectedIds.contains(index)){
            selectedIds.remove(index);
            if (selectedIds.size() == 0){
                hideSelectors(index);
            } else {
                showSelectors();
            }
        } else {
            selectedIds.add(index);
            showSelectors();
        }
    }

    private void hideSelectors(int index){
        try {
            for (int i = 0; i < cards.size(); i ++) {
                View view = cards.get(i).findViewById(R.id.selector);
                if (i == index) {
                    view.setBackground(activity.getDrawable(R.drawable.gray_circle_stroke));
                }
                view.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                completeLayout.setVisibility(View.GONE);
            }
        } catch (Exception e){
            e.getMessage();
        }

    }

    private void showSelectors(){
        try {
            for (int i = 0; i < cards.size(); i ++) {
                View view = cards.get(i).findViewById(R.id.selector);
                if (selectedIds.contains(i)) {
                    view.setBackground(activity.getDrawable(R.drawable.blue_circle));
                } else {
                    view.setBackground(activity.getDrawable(R.drawable.gray_circle_stroke));
                }
                view.setVisibility(View.VISIBLE);
                searchLayout.setVisibility(View.GONE);
                completeLayout.setVisibility(View.VISIBLE);
            }
        } catch (Exception e){
            e.getMessage();
        }
    }
}
