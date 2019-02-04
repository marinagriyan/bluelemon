package com.bluelemon.bluelemon.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.R;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class AddAlertActivity extends AppCompatActivity implements View.OnClickListener {
    private View pinLayout;
    private View searchLayout;
    private View confirmLayout;
    private Location myLocation;

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

    private void searchForLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constants.REQUEST_PERMISSIONS);
        } else {
            getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @SuppressLint("MissingPermission")
                @Override
                public void onSuccess(Location location) {
                    searchLayout.setVisibility(View.GONE);
                    myLocation = location;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    searchForLocation();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_PERMISSIONS){
            searchForLocation();
        }
    }
}
