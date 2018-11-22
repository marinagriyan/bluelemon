package com.bluelemon.bluelemon;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Utils {
    public static void setTabLayoutClicks(final Activity activity){
        activity.findViewById(R.id.documents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECTED_TAB = 0;
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
        activity.findViewById(R.id.training).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECTED_TAB = 1;
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
        activity.findViewById(R.id.risks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECTED_TAB = 2;
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
        activity.findViewById(R.id.equipment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECTED_TAB = 3;
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
        activity.findViewById(R.id.incidents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECTED_TAB = 4;
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
    }
}
