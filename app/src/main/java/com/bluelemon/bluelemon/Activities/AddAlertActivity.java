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
    private View confirmLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        pinLayout = findViewById(R.id.pin_location_layout);
        searchLayout = findViewById(R.id.search_layout);
        confirmLayout = findViewById(R.id.confirm_layout);

        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.pin_location).setOnClickListener(this);
        findViewById(R.id.cancel_search).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);

        findViewById(R.id.send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
            case R.id.cancel_search:
            case R.id.cancel:
            case R.id.back:
                finish();
                break;
            case R.id.pin_location:
                pinLayout.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                searchForLocation();
                break;
            case R.id.send:
                confirmLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.confirm:
                finish();
                break;
        }
    }

    private void searchForLocation(){
        // get location
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchLayout.setVisibility(View.GONE);
            }
        }, 3000);
    }
}
