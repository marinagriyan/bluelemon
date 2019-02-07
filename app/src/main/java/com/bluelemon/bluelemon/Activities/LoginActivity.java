package com.bluelemon.bluelemon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Preferences;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        preferences = App.getInstance().getPreferences();
        if (preferences.getAccessToken() != null) {
            openApp();
        }
    }

    private void initViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.forgot_password).setOnClickListener(this);
        findViewById(R.id.sign_in).setOnClickListener(this);
    }

    private void login() {
        String device = Settings.Secure.getString(getContentResolver(),  Settings.Secure.ANDROID_ID);
        Call<JsonObject> call = RetrofitClient.getInstance().getApi().login(Constants.ORIGIN, device,  username.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    if (response.body() != null && !response.body().get("Body").isJsonNull()){
                        try {
                            JsonObject body = response.body().getAsJsonObject("Body");
                            String token = body.get("AccessToken").getAsString();
                            preferences.setSites(body.getAsJsonObject("SiteAccesses"));
                            Set<String> siteAccesses = body.getAsJsonObject("SiteAccesses").keySet();
                            JsonArray sites = new JsonArray();
                            for (String site : siteAccesses) {
                                sites.add(site);
                            }
                            preferences.setSiteIDs(sites);
                            App.getInstance().getPreferences().setAccessToken(token);
                            openApp();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(LoginActivity.this, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openApp(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgot_password:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.sign_in:
                login();
                break;
        }
    }
}
