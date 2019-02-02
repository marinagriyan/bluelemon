package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Models.Responses.AccidentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

import java.util.List;
import java.util.Locale;

public class MyIncidentsAdapter extends RecyclerView.Adapter<MyIncidentsAdapter.VH> {
    private Activity activity;
    private List<AccidentBody> list;
    private String date = "";

    public MyIncidentsAdapter(Activity activity, List<AccidentBody> sectionItemList) {
        this.activity = activity;
        this.list = sectionItemList;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(activity).inflate(R.layout.incidents_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        try{
            AccidentBody body = list.get(i);
            if (!Utils.dayFormatFromTimestamp(body.getAccidentDate()).equals(date)){
                date = Utils.dayFormatFromTimestamp(body.getAccidentDate());
                vh.date.setVisibility(View.VISIBLE);
                vh.date.setText(date);
            }
            vh.reference.setText(body.getReference());
            vh.stage.setText(body.getAccidentInvestigationStageName());
            vh.categoryName.setText(body.getCategoryName());
            vh.severityName.setText(body.getSeverityName());
            vh.type.setText(body.getAccidentTypeName());
            vh.reportedBy.setText(String.format(Locale.ENGLISH, "%s %s.", body.getReportedByFirstName(), body.getReportedBySurname().charAt(0)));
            if (body.getAccidentInvestigatorUserName() != null && !body.getAccidentInvestigatorUserName().equals("")) {
                vh.itemView.findViewById(R.id.investigator_layout).setVisibility(View.VISIBLE);
                vh.investigator.setText(body.getAccidentInvestigatorUserName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView reference;
        private TextView stage;
        private TextView categoryName;
        private TextView severityName;
        private TextView type;
        private TextView reportedBy;
        private TextView investigator;
        VH(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            reference = itemView.findViewById(R.id.reference);
            stage = itemView.findViewById(R.id.stage);
            categoryName = itemView.findViewById(R.id.category_name);
            severityName = itemView.findViewById(R.id.severity_name);
            type = itemView.findViewById(R.id.type);
            reportedBy = itemView.findViewById(R.id.reported_by);
            investigator = itemView.findViewById(R.id.investigator);
        }
    }
}
