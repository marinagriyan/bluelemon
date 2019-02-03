package com.bluelemon.bluelemon.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Fragments.FolderEquipmentFragment;
import com.bluelemon.bluelemon.Models.FolderModel;
import com.bluelemon.bluelemon.R;

import java.util.List;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.ViewHolder>{
    private MainActivity activity;
    private List<FolderModel> list;

    public FoldersAdapter(MainActivity activity, List<FolderModel> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.folder_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final FolderModel model = list.get(i);
        viewHolder.title.setText(model.getTitle());
        viewHolder.count.setText(model.getCount());

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new FolderEquipmentFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView count;
        private View view;

        public ViewHolder(@NonNull View v) {
            super(v);
            title = v.findViewById(R.id.title);
            count = v.findViewById(R.id.count);
            view = v.findViewById(R.id.view);
        }
    }
}
