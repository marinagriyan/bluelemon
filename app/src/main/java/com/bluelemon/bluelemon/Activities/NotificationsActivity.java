package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bluelemon.bluelemon.Adapters.NotificationsAdapter;
import com.bluelemon.bluelemon.Models.Notification;
import com.bluelemon.bluelemon.Models.NotificationsModel;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<NotificationsModel> list = new ArrayList<>();

        List<Notification> notifications = new ArrayList<>();

        notifications.add(new Notification("Ritana I.", "Reported a new incidet", "10:15 PM"));
        notifications.add(new Notification("Ritana I.", "Reported a new incidet", "10:15 PM"));
        notifications.add(new Notification("Ritana I.", "Reported a new incidet", "10:15 PM"));

        list.add(new NotificationsModel("June 12, 2018", notifications));
        list.add(new NotificationsModel("June 12, 2018", notifications));
        list.add(new NotificationsModel("June 12, 2018", notifications));
        list.add(new NotificationsModel("June 12, 2018", notifications));

        recyclerView.setAdapter(new NotificationsAdapter(this, list));

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.security_alert).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.security_alert:
                startActivity(new Intent(NotificationsActivity.this, AlertsActivity.class));
                finish();
                break;
        }
    }
}
