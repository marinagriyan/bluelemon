package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.AlertDetailsActivity;
import com.bluelemon.bluelemon.Models.CoursesModel;
import com.bluelemon.bluelemon.Models.SingleCourse;
import com.bluelemon.bluelemon.Models.SingleCourse;
import com.bluelemon.bluelemon.R;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class CoursesAdapter extends SectionRecyclerViewAdapter<CoursesModel, SingleCourse, CoursesAdapter.SectionViewHolder, CoursesAdapter.ChildViewHolder> {

    private Activity activity;
    private List<CoursesModel> sectionItemList;

    public CoursesAdapter(Activity activity, List<CoursesModel> sectionItemList) {
        super(activity, sectionItemList);
        this.activity = activity;
        this.sectionItemList = sectionItemList;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(activity).inflate(R.layout.date_layout, viewGroup, false));
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.course_item, viewGroup, false));
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder viewHolder, int i, CoursesModel sectionHeader) {
        viewHolder.date.setText(sectionItemList.get(i).getDate());
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder viewHolder, int i, int i1, SingleCourse child) {
        viewHolder.title.setText(sectionItemList.get(i).getChildItems().get(i1).getTitle());
        viewHolder.category.setText(sectionItemList.get(i).getChildItems().get(i1).getCategory());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, AlertDetailsActivity.class));
            }
        });
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        public SectionViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView;
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView category;

        public ChildViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
        }
    }
}
