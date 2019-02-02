package com.bluelemon.bluelemon;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Locale;

public class Preferences extends Application {
    private static final String PREFERENCES_NAME = "prefs";

    private static final String ACCESS_TOKEN = "access_token";
    private static final String SITES = "sites";

    private SharedPreferences _preferences;

    public Preferences(Context context) {
        _preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return _preferences.edit();
    }

    public String getAccessToken(){
        String token = _preferences.getString(ACCESS_TOKEN, null);
        if (token != null) {
            return String.format(Locale.ENGLISH, "Bearer %s", token);
        } else {
            return null;
        }
    }

    public void setAccessToken(String value){ getEditor().putString(ACCESS_TOKEN, value).apply(); }

    public JsonArray getSites(){
        JsonArray jsonArray = null;
        try {
            jsonArray = new Gson().fromJson(_preferences.getString(SITES, ""), JsonArray.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void setSites(JsonArray value){
        getEditor().putString(SITES, value.toString()).apply();
    }
}
