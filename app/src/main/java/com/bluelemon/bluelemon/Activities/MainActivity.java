package com.bluelemon.bluelemon.Activities;

import android.os.Bundle;

import com.bluelemon.bluelemon.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   startActivity(new Intent(this, SignInActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
