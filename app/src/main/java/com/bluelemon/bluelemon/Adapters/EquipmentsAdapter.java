package com.bluelemon.bluelemon.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Fragments.NewDocumentFragment;
import com.bluelemon.bluelemon.Fragments.NewEquipmentFragment;
import com.bluelemon.bluelemon.Fragments.NewFolderFragment;
import com.bluelemon.bluelemon.Fragments.ReviewEquipmentFragment;
import com.bluelemon.bluelemon.Models.EquipmentModel;
import com.bluelemon.bluelemon.R;

import java.util.List;

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.ViewHolder> {
    private MainActivity activity;
    private List<EquipmentModel> list;
    private NewFolderFragment fragment;

    public EquipmentsAdapter(MainActivity activity, NewFolderFragment fragment, List<EquipmentModel> list) {
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
        final EquipmentModel model = list.get(i);

        viewHolder.title.setText(model.getTitle());
        viewHolder.date.setText(model.getDate());
        viewHolder.days.setText(model.getDays());
        viewHolder.id.setText(model.getId());
        viewHolder.department.setText(model.getDepartment());
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new NewEquipmentFragment());
            }
        });
        viewHolder.review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new ReviewEquipmentFragment());
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fragment.move();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;
        private TextView days;
        private TextView id;
        private TextView department;
        private View review;
        private View edit;


        ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            days = view.findViewById(R.id.day_count);
            id = view.findViewById(R.id.id_number);
            department = view.findViewById(R.id.department);
            review = view.findViewById(R.id.view);
            edit = view.findViewById(R.id.edit);
        }

    }

}
