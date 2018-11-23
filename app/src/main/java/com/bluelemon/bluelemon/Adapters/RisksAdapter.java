package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.Models.RiskModel;
import com.bluelemon.bluelemon.R;

import java.util.List;

public class RisksAdapter extends RecyclerView.Adapter<RisksAdapter.ViewHolder>{
    private Activity activity;
    private List<RiskModel> list;

    public RisksAdapter(Activity activity, List<RiskModel> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.risks_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RiskModel model = list.get(i);
        viewHolder.status.setText(model.getStatus());
        viewHolder.title.setText(model.getTitle());
        viewHolder.date.setText(model.getDate());
        viewHolder.code.setText(model.getCode());

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView status;
        private TextView title;
        private TextView date;
        private TextView code;
        private View view;

        public ViewHolder(@NonNull View v) {
            super(v);
            status = v.findViewById(R.id.status);
            title = v.findViewById(R.id.title);
            date = v.findViewById(R.id.date);
            code = v.findViewById(R.id.code);
            view = v.findViewById(R.id.view);
        }
    }
}
