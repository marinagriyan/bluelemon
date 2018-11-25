package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bluelemon.bluelemon.Adapters.AlertsAdapter;
import com.bluelemon.bluelemon.Adapters.DocumentsMainPagerAdapter;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        startActivity(new Intent(this, AddAlertActivity.class));
        Utils.setTabLayoutClicks(this);
        findViewById(R.id.profile).setOnClickListener(this);

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
                startActivity(new Intent(AlertActivity.this, ProfileActivity.class));
                break;
        }
    }
}
