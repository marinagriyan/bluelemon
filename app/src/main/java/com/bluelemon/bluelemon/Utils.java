package com.bluelemon.bluelemon;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.LoginActivity;
import com.bluelemon.bluelemon.Activities.MainActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.DOWNLOAD_SERVICE;

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

    public static void logout(Activity activity){
        App.getInstance().getPreferences().setAccessToken(null);
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
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
            return new SimpleDateFormat("d MMMM, yyyy", Locale.UK).format(date);
        } else return "";
    }

    public static void download(final Activity activity, int id, final String name){
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .downloadDocument(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null){
                    try {
                        Utils.writeFile(activity, name, response.body().bytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static boolean writeFile(Activity activity, String name, byte[] body){
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PERMISSIONS);
        } else {
            String dir = "BlueLemon Files";
            File storageDir =  new File(Environment.getExternalStorageDirectory(), dir);
            if (!storageDir.exists()) {
                storageDir.mkdirs();
            }
            File file = new File(storageDir, name);
            if (!file.getParentFile().mkdirs()) {
                file = new File(storageDir, name);
            }
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(file, true);
                os.write(body);
                os.close();
                Toast.makeText(activity, "File downloaded!", Toast.LENGTH_SHORT).show();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void showDialog(Activity activity, String message,
                                  String posBtn, DialogInterface.OnClickListener posBtnListener){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.
                setMessage(message).
                setPositiveButton(posBtn, posBtnListener)
                .setCancelable(false).show();
    }

}
