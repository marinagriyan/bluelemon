package com.bluelemon.bluelemon;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Preferences extends Application {
    private static final String PREFERENCES_NAME = "prefs";

    private static final String ACCESS_TOKEN = "access_token";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String SITES_IDS = "site_ids";
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

    public String getUserName(){
        return _preferences.getString(USERNAME, null);
    }

    public void setUserName(String value){ getEditor().putString(USERNAME, value).apply(); }

    public String getEmail(){
        return _preferences.getString(EMAIL, null);
    }

    public void setEmail(String value){ getEditor().putString(EMAIL, value).apply(); }

    public JsonArray getSiteIDs(){
        JsonArray jsonArray = null;
        try {
            jsonArray = new Gson().fromJson(_preferences.getString(SITES_IDS, ""), JsonArray.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void setSiteIDs(JsonArray value){
        getEditor().putString(SITES_IDS, value.toString()).apply();
    }

    public HashMap<String, String> getSiteNames(){
        HashMap<String, String> map = new Gson().fromJson(
                _preferences.getString(SITES, null), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        HashMap<String,String> swapped = new HashMap<>();
        for(HashMap.Entry<String,String> entry : map.entrySet()){
            swapped.put(entry.getValue(), entry.getKey());
        }
        return swapped;
    }

    public HashMap<String, String> getSites(){
        HashMap<String, String> map = new Gson().fromJson(
                _preferences.getString(SITES, null), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        return map;
    }

    public void setSites(JsonObject value){
        getEditor().putString(SITES, value.toString()).apply();
    }
}
