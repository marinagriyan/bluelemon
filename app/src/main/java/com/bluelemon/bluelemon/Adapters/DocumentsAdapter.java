package com.bluelemon.bluelemon.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Fragments.NewDocumentFragment;
import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.R;

import java.util.List;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder>{
    private MainActivity activity;
    private List<DocumentModel> list;

    public DocumentsAdapter(MainActivity activity, List<DocumentModel> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.documents_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DocumentModel model = list.get(i);
        if (model.getStatus()){
            viewHolder.status.setBackground(activity.getDrawable(R.drawable.green_circle));
            viewHolder.renew.setVisibility(View.GONE);
        }
        viewHolder.title.setText(model.getTitle());
        viewHolder.date.setText(model.getDate());
        viewHolder.category.setText(model.getCategory());
        viewHolder.location.setText(model.getLocation());
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new NewDocumentFragment());
            }
        });
        viewHolder.renew.setOnClickListener(new View.OnClickListener() {
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
        private ImageView status;
        private TextView title;
        private TextView date;
        private TextView category;
        private TextView location;
        private View edit;
        private View renew;

        ViewHolder(@NonNull View view) {
            super(view);
            status = view.findViewById(R.id.status);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            category = view.findViewById(R.id.category);
            location = view.findViewById(R.id.location);
            edit = view.findViewById(R.id.edit);
            renew = view.findViewById(R.id.renew);
        }
    }
}
