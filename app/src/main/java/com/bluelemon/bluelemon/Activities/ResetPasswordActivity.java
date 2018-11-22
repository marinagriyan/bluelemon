package com.bluelemon.bluelemon.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluelemon.bluelemon.R;

public class ResetPasswordActivity extends AppCompatActivity {
    private LinearLayout resetLayout;
    private LinearLayout congratsLayout;
    private EditText password1;
    private EditText password2;
    private TextView matchMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetLayout = findViewById(R.id.reset_layout);
        congratsLayout = findViewById(R.id.congrats_layout);

        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        matchMessage = findViewById(R.id.doesnt_match);

        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password1.getText().toString().equals(password2.getText().toString())) {
                    resetLayout.setVisibility(View.GONE);
                    congratsLayout.setVisibility(View.VISIBLE);
                } else {
                    matchMessage.setVisibility(View.VISIBLE);
                }
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
