package com.bluelemon.bluelemon.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.UpdatePassword;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText email, pass, confirmPass;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_password);
        username = findViewById(R.id.username);

        username.setText(App.getInstance().getPreferences().getUserName());
        email.setText(App.getInstance().getPreferences().getEmail());

        findViewById(R.id.profile).setVisibility(View.GONE);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        findViewById(R.id.save).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.edit_email).setOnClickListener(this);
        findViewById(R.id.edit_password).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                updatePassword();
                break;
            case R.id.logout:
                logout();
                break;    
            case R.id.back:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
            case R.id.edit_email:
            case R.id.edit_password:
                findViewById(R.id.email_layout).setVisibility(View.GONE);
                pass.setEnabled(true);
                confirmPass.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void logout() {
        Call<JsonObject> call = RetrofitClient.getInstance().getApi()
                .logout(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Utils.logout(ProfileActivity.this);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void updatePassword(){
        if (!pass.getText().toString().equals(confirmPass.getText().toString())){
            Toast.makeText(ProfileActivity.this, "Passwords don't match!", Toast.LENGTH_LONG).show();
        } else {
            JsonObject body = new JsonObject();
            body.addProperty("userName", username.getText().toString());
            body.addProperty("password", username.getText().toString());
            body.addProperty("repeatPassword", username.getText().toString());

            Call<UpdatePassword> call = RetrofitClient.getInstance().getApi()
                    .updatePassword(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
            call.enqueue(new Callback<UpdatePassword>() {
                @Override
                public void onResponse(Call<UpdatePassword> call, Response<UpdatePassword> response) {
                    if (response.body() != null && response.body().getMessage().equals("Success")){
                        Toast.makeText(ProfileActivity.this, "Password Updated!", Toast.LENGTH_LONG).show();
                        findViewById(R.id.email_layout).setVisibility(View.VISIBLE);
                        pass.setEnabled(false);
                        confirmPass.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<UpdatePassword> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
}
