package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Fragments.AddIncidentFragment;
import com.bluelemon.bluelemon.Fragments.DocumentsMainFragment;
import com.bluelemon.bluelemon.Fragments.EditEquipmentFragment;
import com.bluelemon.bluelemon.Fragments.EquipmentFragment;
import com.bluelemon.bluelemon.Fragments.IncidentsFragment;
import com.bluelemon.bluelemon.Fragments.NewEquipmentFragment;
import com.bluelemon.bluelemon.Fragments.RisksFragment;
import com.bluelemon.bluelemon.Fragments.TrainingFragment;
import com.bluelemon.bluelemon.R;

import java.util.ArrayDeque;
import java.util.Deque;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private FrameLayout view_stub;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private int[] tabIcons = {R.drawable.documents_icon, R.drawable.training_icon, R.drawable.risk_icon, R.drawable.equipment_icon, R.drawable.incident_icon};
    private int[] tabSelectedIcons = {R.drawable.documents_icon_pr, R.drawable.training_icon_pr, R.drawable.risk_icon_pr, R.drawable.equipment_icon_pr, R.drawable.incident_icon_pr};
    private String[] tabLabels;
    private DocumentsMainFragment documentsMainFragment;
    private TrainingFragment trainingFragment;
    private RisksFragment risksFragment;
    private EquipmentFragment equipmentFragment;
    private NewEquipmentFragment newEquipmentFragment;
    private EditEquipmentFragment editEquipmentFragment;
    private IncidentsFragment incidentsFragment;
    private Fragment fragment;

    private ImageView profile;
    private ImageView back;

    private View messageLayout;
    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> secondStack = new ArrayDeque<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        view_stub = findViewById(R.id.view_stub);
        tabLayout = findViewById(R.id.tabs);
        tabLabels = getResources().getStringArray(R.array.tabs);

        fragmentManager = getSupportFragmentManager();

        documentsMainFragment = new DocumentsMainFragment();
        fragment = documentsMainFragment;
        trainingFragment = new TrainingFragment();
        risksFragment = new RisksFragment();
        equipmentFragment = new EquipmentFragment();
        incidentsFragment = new IncidentsFragment();

        setupTabs();

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(this);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        findViewById(R.id.security_alert).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);

        messageLayout = findViewById(R.id.completed_layout);
        findViewById(R.id.continue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageLayout.setVisibility(View.GONE);
            }
        });
    }

    private void setupTabs() {
        for (int i = 0; i < tabLabels.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabLabels[i]).setIcon(tabIcons[i]));
        }
        tabLayout.addOnTabSelectedListener(this);
        try {
            int selectedTab = Constants.SELECTED_TAB;
            setFragment(documentsMainFragment, "0");
            pushFragmentIntoStack(0);
            tabLayout.getTabAt(selectedTab).select();
            tabLayout.getTabAt(selectedTab).setIcon(tabSelectedIcons[selectedTab]);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void pushFragmentIntoStack(int id) {
        stack.remove(id);
        stack.push(id);
    }

    private void setFragment(Fragment fragment, String tag) {
        fragmentManager.beginTransaction().replace(view_stub.getId(), fragment, tag).addToBackStack(tag).commit();
    }

    public void showMessageLayout(){
        messageLayout.setVisibility(View.VISIBLE);
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
                startActivity(new Intent(MainActivity.this, AlertsActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
            case R.id.notification:
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (secondStack.size() > 0){
            secondStack.pop();
            if (secondStack.size() == 0){
                showBack(false);
            }
            super.onBackPressed();
        } else {
            if (stack.size() > 1) {
                stack.pop();
                int index = stack.peek();
                tabLayout.getTabAt(index).select();
            } else {
                //openExitDialog();
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.setIcon(tabSelectedIcons[position]);
        Constants.SELECTED_TAB = position;
        switch (position){
            case 0:
                setFragment(documentsMainFragment, "0");
                break;
            case 1:
                setFragment(trainingFragment, "1");
                break;
            case 2:
                setFragment(risksFragment, "1");
                break;
            case 3:
                setFragment(equipmentFragment, "3");
                break;
            case 4:
                setFragment(incidentsFragment, "4");
                break;

        }
        pushFragmentIntoStack(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        tab.setIcon(tabIcons[position]);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void addFragment(Fragment fragment, int id){
        fragmentManager.beginTransaction().replace(view_stub.getId(), fragment).addToBackStack(String.valueOf(id)).commit();
        secondStack.push(id);
    }

    public void removeFragment(){
        fragmentManager.popBackStack();
        secondStack.pop();
    }

    public void showBack(boolean show){
        if (show){
            profile.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.GONE);
            profile.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddIncidentFragment.ADD_IMAGE || requestCode == AddIncidentFragment.CHANGE_IMAGE){
            incidentsFragment.getAddIncidentFragment().onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == NewEquipmentFragment.ADD_IMAGE){
            newEquipmentFragment.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == EditEquipmentFragment.ADD_IMAGE) {
            editEquipmentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setNewEquipmentFragment(NewEquipmentFragment newEquipmentFragment) {
        this.newEquipmentFragment = newEquipmentFragment;
    }

    public void setEditEquipmentFragment(EditEquipmentFragment editEquipmentFragment) {
        this.editEquipmentFragment = editEquipmentFragment;
    }

    public IncidentsFragment getIncidentsFragment() {
        return incidentsFragment;
    }
}
