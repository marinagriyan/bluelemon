package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.AlertDetailsActivity;
import com.bluelemon.bluelemon.Models.AlertsModel;
import com.bluelemon.bluelemon.Models.SingleAlert;
import com.bluelemon.bluelemon.R;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class MyIncidentsAdapter extends SectionRecyclerViewAdapter<AlertsModel, SingleAlert, MyIncidentsAdapter.SectionViewHolder, MyIncidentsAdapter.ChildViewHolder> {

    private Activity activity;

    public MyIncidentsAdapter(Context context, List<AlertsModel> sectionItemList, Activity activity) {
        super(context, sectionItemList);
        this.activity = activity;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(activity).inflate(R.layout.date_layout, viewGroup, false));
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.incidents_item, viewGroup, false));
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder viewHolder, int i, AlertsModel sectionHeader) {
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder viewHolder, int i, int i1, SingleAlert child) {

    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        public SectionViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView;
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {


        public ChildViewHolder(View itemView) {
            super(itemView);

        }
    }
}
