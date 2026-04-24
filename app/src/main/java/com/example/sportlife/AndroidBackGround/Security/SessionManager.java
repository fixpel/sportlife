package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.UnknownNullability;

import lombok.RequiredArgsConstructor;

public  class SessionManager {
    private final SharedPreferences preferences;
    private final SecurityContext context;
    public SessionManager(Context contextShared, SecurityContext context){
        this.preferences=contextShared.getSharedPreferences("User", Context.MODE_PRIVATE);
        this.context = context;
    }
    public  void saveToken(String accessToken,String refreshToken){
        preferences.edit().putString("access",accessToken).putString("refresh",refreshToken).apply();
        context.setTokenRefresh(refreshToken);
        context.setTokenAccess(accessToken);
    }
    public String gerAccessToken(){
        return  preferences.getString("access",null);
    }
    public String getRefreshToken(){
        return preferences.getString("refresh",null);
    }
}
