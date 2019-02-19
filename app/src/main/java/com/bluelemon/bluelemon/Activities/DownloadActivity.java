package com.bluelemon.bluelemon.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadActivity extends AppCompatActivity {
    private String name;
    private String date;
    private byte[] file;
    private TextView fileDate, fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        fileDate = findViewById(R.id.file_date);
        fileName = findViewById(R.id.file_name);

        if (getIntent().getIntExtra("id", 0)!= 0){
            getDocument(getIntent().getIntExtra("id", 0));
        }
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file != null) {
                    if (Utils.writeFile(DownloadActivity.this, name, file)){
                        finish();
                    }
                }
            }
        });
    }

    private void getDocument(int id){
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .downloadRisk(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null){
                    try {
                        String disposition = response.headers().get("Content-Disposition");
                        name = disposition.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
                        date = response.headers().get("Date");
                        file = response.body().bytes();
                        fileName.setText(name);
                        fileDate.setText(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
