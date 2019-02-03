package com.bluelemon.bluelemon.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Fragments.CoursesFragment;
import com.bluelemon.bluelemon.Models.Responses.CourseBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private MainActivity activity;
    private List<CourseBody> list;

    public CourseAdapter(MainActivity activity, List<CourseBody> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            CourseBody body = list.get(position);
            holder.name.setText(body.getCourseTitle());
            holder.date.setText(Utils.dayFormatFromTimestamp(body.getCourseCompleted()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
        }
    }
}
