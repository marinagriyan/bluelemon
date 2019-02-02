package com.bluelemon.bluelemon;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class Preferences extends Application {
    private static final String PREFERENCES_NAME = "prefs";

    private static final String ACCESS_TOKEN = "access_token";

    private SharedPreferences _preferences;

    public Preferences(Context context) {
        _preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return _preferences.edit();
    }

    public String getAccessToken(){
        String token = _preferences.getString(ACCESS_TOKEN, null);
        return String.format(Locale.ENGLISH, "Bearer %s", token);
    }

    public void setAccessToken(String value){ getEditor().putString(ACCESS_TOKEN, value).apply(); }
}
