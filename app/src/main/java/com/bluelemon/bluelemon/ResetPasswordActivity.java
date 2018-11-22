package com.bluelemon.bluelemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ResetPasswordActivity extends AppCompatActivity {
    private LinearLayout resetLayout;
    private LinearLayout congratsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetLayout = findViewById(R.id.reset_layout);
        congratsLayout = findViewById(R.id.congrats_layout);

        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLayout.setVisibility(View.GONE);
                congratsLayout.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
