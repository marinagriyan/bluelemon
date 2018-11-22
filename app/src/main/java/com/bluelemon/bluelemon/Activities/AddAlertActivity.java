package com.bluelemon.bluelemon.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bluelemon.bluelemon.R;
import com.google.android.gms.location.LocationServices;

public class AddAlertActivity extends AppCompatActivity implements View.OnClickListener {
    private View pinLayout;
    private View searchLayout;
    private View mainLayout;
    private double lat = 0, lng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        pinLayout = findViewById(R.id.pin_location_layout);
        searchLayout = findViewById(R.id.search_layout);
        mainLayout = findViewById(R.id.main_layout);

        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.pin_location).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
            case R.id.cancel:
                finish();
                break;
            case R.id.pin_location:
                pinLayout.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                searchForLocation();
                break;
        }
    }

    private void searchForLocation(){
        // get location
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }
}
