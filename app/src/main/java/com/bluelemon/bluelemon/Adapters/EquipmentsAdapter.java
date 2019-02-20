package com.bluelemon.bluelemon.Adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Fragments.EditEquipmentFragment;
import com.bluelemon.bluelemon.Fragments.FolderEquipmentFragment;
import com.bluelemon.bluelemon.Fragments.ViewEquipmentFragment;
import com.bluelemon.bluelemon.Models.Responses.EquipmentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.Gson;

import java.util.List;

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.ViewHolder> {
    private MainActivity activity;
    private List<EquipmentBody> list;
    private FolderEquipmentFragment fragment;

    public EquipmentsAdapter(MainActivity activity, FolderEquipmentFragment fragment, List<EquipmentBody> list) {
        this.activity = activity;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public EquipmentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EquipmentsAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.equipment_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentsAdapter.ViewHolder viewHolder, int i) {
        try {
            final EquipmentBody model = list.get(i);

            viewHolder.refNo.setText(model.getRefno());
            viewHolder.date.setText(Utils.dayFormatFromTimestamp(model.getCheckDate()));
            viewHolder.frequency.setText(String.format("%s Days", model.getFrequency()));
            viewHolder.id.setText(String.format("ID %s", model.getEquipmentInstanceReference()));
            viewHolder.department.setText(model.getDepartmentName());
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditEquipmentFragment fragment = new EditEquipmentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("equipment", new Gson().toJson(model));
                    fragment.setArguments(bundle);
                    activity.addFragment(fragment, 35);
                }
            });
            viewHolder.review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewEquipmentFragment fragment = new ViewEquipmentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("equipment", new Gson().toJson(model));
                    fragment.setArguments(bundle);
                    activity.addFragment(fragment, 36);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    fragment.move();
                    return false;
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView refNo;
        private TextView date;
        private TextView frequency;
        private TextView id;
        private TextView department;
        private View review;
        private View edit;


        ViewHolder(@NonNull View view) {
            super(view);
            refNo = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            frequency = view.findViewById(R.id.day_count);
            id = view.findViewById(R.id.id_number);
            department = view.findViewById(R.id.department);
            review = view.findViewById(R.id.view);
            edit = view.findViewById(R.id.edit);
        }

    }

}
