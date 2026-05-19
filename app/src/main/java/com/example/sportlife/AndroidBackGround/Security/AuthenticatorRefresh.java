package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import lombok.RequiredArgsConstructor;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

@RequiredArgsConstructor
public class AuthenticatorRefresh implements Authenticator {
    private  final Context context;
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) {
        SessionManager session=new SessionManager(context);
        Log.d("rrr","ererer");
        RefreshService refreshService=new RefreshService(new CallBackHandlerImpl(null,null));
        String tokenRefresh= session.getRefreshToken();
        RefreshResponse refresh=refreshService.refresh(tokenRefresh);
        if(refresh==null){
            return null;
        }
        session.saveToken(refresh.getAccessToken(),refresh.getRefreshToken());
        String tokenAccess=refresh.getAccessToken();
        return response.request().newBuilder().addHeader("Authorization", "Bearer " + tokenAccess).build();
    }
}
