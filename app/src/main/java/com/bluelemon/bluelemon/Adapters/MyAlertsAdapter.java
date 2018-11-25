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
import com.bluelemon.bluelemon.Models.AlertsModel;
import com.bluelemon.bluelemon.Models.SingleAlert;
import com.bluelemon.bluelemon.R;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class MyAlertsAdapter extends SectionRecyclerViewAdapter<AlertsModel, SingleAlert, MyAlertsAdapter.SectionViewHolder, MyAlertsAdapter.ChildViewHolder> {

    private Activity activity;
    private List<AlertsModel> sectionItemList;

    public MyAlertsAdapter(Activity activity, List<AlertsModel> sectionItemList) {
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
        return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.alert_item, viewGroup, false));
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder viewHolder, int i, AlertsModel sectionHeader) {
        viewHolder.date.setText(sectionItemList.get(i).getDate());
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder viewHolder, int i, int i1, SingleAlert child) {
        if (!sectionItemList.get(i).getChildItems().get(i1).isChecked()){
            viewHolder.status.setBackground(activity.getDrawable(R.drawable.yellow_circle));
        }
        viewHolder.title.setText(sectionItemList.get(i).getChildItems().get(i1).getTitle());
        viewHolder.time.setText(sectionItemList.get(i).getChildItems().get(i1).getTime());
        viewHolder.type.setText(sectionItemList.get(i).getChildItems().get(i1).getType());
        viewHolder.department.setText(sectionItemList.get(i).getChildItems().get(i1).getDepartment());

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
        private ImageView status;
        private TextView title;
        private TextView time;
        private TextView type;
        private TextView department;

        public ChildViewHolder(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            type = itemView.findViewById(R.id.type);
            department = itemView.findViewById(R.id.department);
        }
    }
}
