package com.bluelemon.bluelemon;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.bluelemon.bluelemon.Activities.MainActivity;

public class Utils {
    public static void setTabLayoutClicks(final Activity activity){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.documents:
                        Constants.SELECTED_TAB = 0;
                        break;
                    case R.id.training:
                        Constants.SELECTED_TAB = 1;
                        break;
                    case R.id.risks:
                        Constants.SELECTED_TAB = 2;
                        break;
                    case R.id.equipment:
                        Constants.SELECTED_TAB = 3;
                        break;
                    case R.id.incidents:
                        Constants.SELECTED_TAB = 4;
                        break;
                }
                Intent i = new Intent(activity, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(i);
                activity.finish();
            }
        };
        activity.findViewById(R.id.documents).setOnClickListener(listener);
        activity.findViewById(R.id.training).setOnClickListener(listener);
        activity.findViewById(R.id.risks).setOnClickListener(listener);
        activity.findViewById(R.id.equipment).setOnClickListener(listener);
        activity.findViewById(R.id.incidents).setOnClickListener(listener);
    }
}
