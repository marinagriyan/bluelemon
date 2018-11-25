package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bluelemon.bluelemon.Adapters.ActionPlansAdapter;
import com.bluelemon.bluelemon.Models.ActionPlan;
import com.bluelemon.bluelemon.Models.PlanMember;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class AlertDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ActionPlan> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<PlanMember> planMembers = new ArrayList<>();
        planMembers.add(new PlanMember("Jonathan Brewings", "Seen", "June 12, 2018"));
        planMembers.add(new PlanMember("Jonathan Brewings", "Received", "June 12, 2018"));
        planMembers.add(new PlanMember("Jonathan Brewings", "Seen", "June 12, 2018"));
        planMembers.add(new PlanMember("Jonathan Brewings", "Received", "June 12, 2018"));

        list.add(new ActionPlan("Harverter of sorrow", "14 people done", planMembers));
        list.add(new ActionPlan("Harverter of sorrow", "14 people done", planMembers));
        list.add(new ActionPlan("Harverter of sorrow", "14 people done", planMembers));


        recyclerView.setAdapter(new ActionPlansAdapter(this, list));

        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlertDetailsActivity.this, ProfileActivity.class));
            }
        });
    }
}
