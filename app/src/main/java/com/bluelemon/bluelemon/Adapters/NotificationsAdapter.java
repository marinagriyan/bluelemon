package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.AlertDetailsActivity;
import com.bluelemon.bluelemon.Models.NotificationsModel;
import com.bluelemon.bluelemon.Models.Notification;
import com.bluelemon.bluelemon.R;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

public class NotificationsAdapter extends SectionRecyclerViewAdapter<NotificationsModel, Notification, NotificationsAdapter.SectionViewHolder, NotificationsAdapter.ChildViewHolder> {

    private Activity activity;
    private List<NotificationsModel> sectionItemList;

    public NotificationsAdapter(Activity activity, List<NotificationsModel> sectionItemList) {
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
        return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.notification_item, viewGroup, false));
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder viewHolder, int i, NotificationsModel sectionHeader) {
        viewHolder.date.setText(sectionItemList.get(i).getDate());
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder viewHolder, int i, int i1, Notification child) {

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
