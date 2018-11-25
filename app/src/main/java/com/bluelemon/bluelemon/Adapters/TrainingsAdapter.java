package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelemon.bluelemon.R;

public class TrainingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;

    public TrainingsAdapter(Activity activity) {
        this.activity = activity;
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            return new TrainingsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_item, parent, false));
        } else if (viewType == TYPE_HEADER) {
            //inflate your layout and pass it to view holder
            return new TrainingsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_header, parent, false));
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            //cast holder to ViewHolder and set data
        } else if (holder instanceof VHHeader) {
            //cast holder to VHHeader and set data for header.
        }
    }

    @Override
    public int getItemCount() {
        // add 1 to data size, due to existence of header
        return 5;
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
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        public VHHeader(View itemView) {
            super(itemView);
        }
    }
}
