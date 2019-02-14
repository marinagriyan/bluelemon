package com.bluelemon.bluelemon.Adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Fragments.EditCertificateFragment;
import com.bluelemon.bluelemon.Fragments.NewCertificateFragment;
import com.bluelemon.bluelemon.Fragments.ViewCertificateFragment;
import com.bluelemon.bluelemon.Models.Responses.DocumentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

import java.util.List;

public class CertificatesAdapter extends RecyclerView.Adapter<CertificatesAdapter.ViewHolder>{
    private MainActivity activity;
    private List<DocumentBody> list;

    public CertificatesAdapter(MainActivity activity, List<DocumentBody> list) {
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
        try {
            final DocumentBody body = list.get(i);
            if (!body.getExpired()) {
                viewHolder.status.setBackground(activity.getDrawable(R.drawable.green_circle));
                viewHolder.renew.setVisibility(View.GONE);
            }
            viewHolder.title.setText(body.getDocumentName());
            viewHolder.date.setText(Utils.dayFormatFromTimestamp(body.getDocumentDate()));
            viewHolder.category.setText(body.getCategoryName());
            viewHolder.location.setText(body.getSite());
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditCertificateFragment fragment = new EditCertificateFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", body.getDocumentID());
                    fragment.setArguments(bundle);
                    activity.addFragment(fragment, 20);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewCertificateFragment fragment = new ViewCertificateFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", body.getDocumentID());
                    fragment.setArguments(bundle);
                    activity.addFragment(fragment, 2);
                }
            });
            viewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.download(activity, body.getItemID(), body.getFileName());
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
        private ImageView status;
        private TextView title;
        private TextView date;
        private TextView category;
        private TextView location;
        private View download;
        private View edit;
        private View renew;

        ViewHolder(@NonNull View view) {
            super(view);
            status = view.findViewById(R.id.status);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            category = view.findViewById(R.id.category);
            location = view.findViewById(R.id.location);
            download = view.findViewById(R.id.download);
            edit = view.findViewById(R.id.edit);
            renew = view.findViewById(R.id.renew);
        }
    }
}
