package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Fragments.DocumentsMainFragment;
import com.bluelemon.bluelemon.Fragments.TrainingFragment;
import com.bluelemon.bluelemon.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private FrameLayout view_stub;
    private TabLayout tabLayout;
    private int[] tabIcons;
    private int[] tabSelectedIcons;
    private String[] tabLabels;
    private DocumentsMainFragment documentsMainFragment;
    private TrainingFragment trainingFragment;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        view_stub = findViewById(R.id.view_stub);
        tabLayout = findViewById(R.id.tabs);
        tabIcons = new int[]{R.drawable.documents_icon, R.drawable.training_icon, R.drawable.risk_icon, R.drawable.equipment_icon, R.drawable.incident_icon};
        tabSelectedIcons = new int[]{R.drawable.documents_icon_pr, R.drawable.training_icon_pr, R.drawable.risk_icon_pr, R.drawable.equipment_icon_pr, R.drawable.incident_icon_pr};
        tabLabels = getResources().getStringArray(R.array.tabs);
        for (int i = 0; i < tabLabels.length; i ++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabLabels[i]).setIcon(tabIcons[i]));
        }

        documentsMainFragment = new DocumentsMainFragment();
        fragment = documentsMainFragment;
        trainingFragment = new TrainingFragment();

        getSupportFragmentManager().beginTransaction()
                .add(view_stub.getId(), documentsMainFragment).commit();

        tabLayout.addOnTabSelectedListener(this);

        int selectedTab = Constants.SELECTED_TAB;
        tabLayout.getTabAt(selectedTab).select();
        tabLayout.getTabAt(selectedTab).setIcon(tabSelectedIcons[selectedTab]);

        findViewById(R.id.profile).setOnClickListener(this);
        findViewById(R.id.security_alert).setOnClickListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (view_stub != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View stubView = inflater.inflate(layoutResID, view_stub, false);
            view_stub.addView(stubView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.security_alert:
                startActivity(new Intent(MainActivity.this, AlertActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.setIcon(tabSelectedIcons[position]);
        Constants.SELECTED_TAB = position;
        switch (position) {
            case 0:
                fragment = documentsMainFragment;
                break;
            case 1:
                fragment = trainingFragment;
                break;
            case 2:
                fragment = trainingFragment;
                break;
            case 3:
                fragment = trainingFragment;
                break;
            case 4:
                fragment = trainingFragment;
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(view_stub.getId(), fragment)
                .commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.setIcon(tabIcons[position]);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
