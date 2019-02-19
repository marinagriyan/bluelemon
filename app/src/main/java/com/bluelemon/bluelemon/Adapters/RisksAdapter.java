package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.DownloadActivity;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.RiskBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RisksAdapter extends RecyclerView.Adapter<RisksAdapter.ViewHolder>{
    private Activity activity;
    private List<RiskBody> list;

    public RisksAdapter(Activity activity, List<RiskBody> list) {
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
        try {
            final RiskBody model = list.get(i);
            viewHolder.status.setText(model.getStatus());
            viewHolder.title.setText(model.getName());
            viewHolder.date.setText(Utils.dayFormatFromTimestamp(model.getCreated()));
            viewHolder.code.setText(model.getRefno());

            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DownloadActivity.class);
                    intent.putExtra("id", model.getiD());
                    activity.startActivity(intent);
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
