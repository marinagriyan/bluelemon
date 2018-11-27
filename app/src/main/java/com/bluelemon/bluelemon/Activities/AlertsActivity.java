package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bluelemon.bluelemon.Adapters.AlertsAdapter;
import com.bluelemon.bluelemon.Models.Notification;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

public class AlertsActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        startActivity(new Intent(this, AddAlertActivity.class));
        Utils.setTabLayoutClicks(this);
        findViewById(R.id.profile).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);

        tabLayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.view_pager);
        FragmentManager fm = getSupportFragmentManager();
        AlertsAdapter adapter = new AlertsAdapter(fm, this);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile:
                startActivity(new Intent(AlertsActivity.this, ProfileActivity.class));
                break;
            case R.id.add:
                startActivity(new Intent(AlertsActivity.this, AddAlertActivity.class));
                break;
            case R.id.notification:
                startActivity(new Intent(AlertsActivity.this, NotificationsActivity.class));
                break;
        }
    }
}
