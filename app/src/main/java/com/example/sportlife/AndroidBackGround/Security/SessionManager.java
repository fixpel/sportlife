package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.content.SharedPreferences;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SessionManager {
    private SharedPreferences preferences;
    public SessionManager(Context contextShared){
        this.preferences=contextShared.getSharedPreferences("User", Context.MODE_PRIVATE);
    }
    public void saveToken(String accessToken,String refreshToken){
        preferences.edit().putString("access",accessToken).putString("refresh",refreshToken).apply();
    }
    public void saveName(String name){
        preferences.edit().putString("name",name).apply();
    }
    public String getName(){
        return preferences.getString("name",null);
    }
    public String getAccessToken(){
        return  preferences.getString("access",null);
    }
    public String getRefreshToken(){
        return preferences.getString("refresh",null);
    }
}
