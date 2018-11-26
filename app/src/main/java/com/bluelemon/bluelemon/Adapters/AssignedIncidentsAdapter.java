package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Models.IncidentsModel;
import com.bluelemon.bluelemon.Models.SingleIncident;
import com.bluelemon.bluelemon.R;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class AssignedIncidentsAdapter extends SectionRecyclerViewAdapter<IncidentsModel, SingleIncident, AssignedIncidentsAdapter.SectionViewHolder, AssignedIncidentsAdapter.ChildViewHolder> {
    private Activity activity;
    private List<IncidentsModel> sectionItemList;

    public AssignedIncidentsAdapter(Activity activity, List<IncidentsModel> sectionItemList) {
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
        return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.assigned_incidents_item, viewGroup, false));
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder viewHolder, int i, IncidentsModel sectionHeader) {
        viewHolder.date.setText(sectionItemList.get(i).getDate());
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder viewHolder, int i, int i1, SingleIncident child) {

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
