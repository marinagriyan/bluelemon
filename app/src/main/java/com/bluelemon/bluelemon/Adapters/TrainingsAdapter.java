package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Models.Responses.CourseBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

import java.util.List;

public class TrainingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private List<CourseBody> list;

    public TrainingsAdapter(Activity activity, List<CourseBody> list) {
        this.activity = activity;
        this.list = list;
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new TrainingsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_item, parent, false));
        } else if (viewType == TYPE_HEADER) {
            return new TrainingsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_header, parent, false));
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            try {
                CourseBody body = list.get(position);
                ((ViewHolder) holder).name.setText(body.getCourseTitle());
                ((ViewHolder) holder).date.setText(Utils.dayFormatFromTimestamp(body.getCourseCompleted()));
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
        }
    }

    @Override
    public int getItemCount() {
        // add 1 to data size, due to existence of header
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private String getItem(int position) {
       // return data[position - 1];
        return "";
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        public VHHeader(View itemView) {
            super(itemView);
        }
    }
}
