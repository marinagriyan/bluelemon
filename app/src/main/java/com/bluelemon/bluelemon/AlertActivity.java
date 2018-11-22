package com.bluelemon.bluelemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        Utils.setTabLayoutClicks(this);
        findViewById(R.id.profile).setOnClickListener(this);
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
