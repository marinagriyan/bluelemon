package com.bluelemon.bluelemon;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;

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

    public static void showError(Activity activity, ResponseBody errorBody){
        String message = "Error";
        try {
            JSONObject error = new JSONObject(errorBody.string());
            message = error.getString("Error");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static String dayFormatFromTimestamp(String inputDate) {
        Date date = null;
        if (inputDate != null) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK).parse(inputDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (date != null) {
            return new SimpleDateFormat("MMMM d, yyyy", Locale.UK).format(date);
        } else return "";
    }


}
