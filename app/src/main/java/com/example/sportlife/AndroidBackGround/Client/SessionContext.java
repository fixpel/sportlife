package com.example.sportlife.AndroidBackGround.Client;

import android.app.Application;
import android.content.Context;

public class SessionContext extends Application {
    private static Context appSessionContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appSessionContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appSessionContext;
    }
}
