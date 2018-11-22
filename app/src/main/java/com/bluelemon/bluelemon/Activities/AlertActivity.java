package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        startActivity(new Intent(this, AddAlertActivity.class));
        Utils.setTabLayoutClicks(this);
        findViewById(R.id.profile).setOnClickListener(this);
        if (Constants.GET_LOCATION){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile:
                startActivity(new Intent(AlertActivity.this, SignInActivity.class));
                break;
        }
    }
}
