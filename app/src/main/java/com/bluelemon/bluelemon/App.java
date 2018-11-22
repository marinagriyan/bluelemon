package com.bluelemon.bluelemon;

import android.app.Application;

public class App extends Application {

    private static App _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //    Fabric.with(this, new Crashlytics());

        _instance = this;
    }

    public static App getInstance() {
        return _instance;
    }

}
