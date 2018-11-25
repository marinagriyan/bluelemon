package com.bluelemon.bluelemon.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluelemon.bluelemon.Models.ActionPlan;
import com.bluelemon.bluelemon.Models.PlanMember;
import com.bluelemon.bluelemon.R;

import java.lang.reflect.Member;
import java.util.List;

public class ActionPlansAdapter extends RecyclerView.Adapter<ActionPlansAdapter.ViewHolder>{

    private Activity activity;
    private List<ActionPlan> list;

    public ActionPlansAdapter(Activity activity, List<ActionPlan> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.action_plan_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.numberOfPeople.setText(list.get(i).getNumberofPeople());
        int membersSize = list.get(i).getMembers().size();
        for (int j = 0; j < membersSize; j++){
            PlanMember member = list.get(i).getMembers().get(j);
            View view = LayoutInflater.from(activity).inflate(R.layout.action_plan_item_member, viewHolder.members, false);
            TextView memberName = view.findViewById(R.id.member);
            memberName.setText(member.getName());
            TextView status = view.findViewById(R.id.status);
            status.setText(member.getStatus());
            if (j == membersSize - 1){
                view.findViewById(R.id.separator).setVisibility(View.GONE);
            }
            viewHolder.members.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView numberOfPeople;
        private LinearLayout members;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            numberOfPeople = itemView.findViewById(R.id.number_of_people);
            members = itemView.findViewById(R.id.members);
        }
    }
}
