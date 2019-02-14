package com.bluelemon.bluelemon.Adapters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Fragments.EditDocumentFragment;
import com.bluelemon.bluelemon.Fragments.NewDocumentFragment;
import com.bluelemon.bluelemon.Fragments.ViewDocumentFragment;
import com.bluelemon.bluelemon.Models.Responses.DocumentBody;
import com.bluelemon.bluelemon.Models.Responses.SingleDocument;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder>{
    private MainActivity activity;
    private List<DocumentBody> list;

    public DocumentsAdapter(MainActivity activity, List<DocumentBody> list) {
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
                    EditDocumentFragment fragment = new EditDocumentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", body.getDocumentID());
                    fragment.setArguments(bundle);
                    activity.addFragment(fragment, 1);
                }
            });
            viewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.download(activity, body.getItemID(), body.getFileName());
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewDocumentFragment fragment = new ViewDocumentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", body.getDocumentID());
                    fragment.setArguments(bundle);
                    activity.addFragment(fragment, 10);
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
