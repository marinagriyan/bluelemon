package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bluelemon.bluelemon.Models.Responses.DocumentCategory;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<DocumentCategory> {

    private Activity activity;
    private List<DocumentCategory> list;

    public SpinnerAdapter(Activity activity, List<DocumentCategory> list, int textViewResourceId) {
        super(activity, textViewResourceId, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public DocumentCategory getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(list.get(position).getCategoryName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(list.get(position).getCategoryName());
        return label;
    }
}