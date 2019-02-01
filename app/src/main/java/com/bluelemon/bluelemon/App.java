package com.bluelemon.bluelemon;

import android.app.Application;

public class App extends Application {

    private static App instance;
    private Preferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        //    Fabric.with(this, new Crashlytics());

        instance = this;
        preferences = new Preferences(getApplicationContext());
    }

    public static App getInstance() {
        return instance;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
