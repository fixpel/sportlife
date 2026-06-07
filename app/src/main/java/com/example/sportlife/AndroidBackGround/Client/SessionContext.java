package com.example.sportlife.AndroidBackGround.Client;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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
    public static Map<String,String> getDictionary(String nameFile) throws IOException {
        InputStream file = appSessionContext.getAssets().open(nameFile);
        int size = file.available();
        byte[] buffer = new byte[size];
        file.read(buffer);
        file.close();
        String json = new String(buffer, StandardCharsets.UTF_8);
        Type hash = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> dictionary = new Gson().fromJson(json, hash);
        return dictionary;
    }
}
