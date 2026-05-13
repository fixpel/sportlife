package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;

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
        RefreshService refreshService=new RefreshService(new CallBackHandlerImpl(null,null));
        String tokenRefresh= SecurityContext.getTokenRefresh();
        RefreshResponse refresh=refreshService.refresh(tokenRefresh);
        if(refresh==null){
            return null;
        }
        SessionManager session=new SessionManager(context);
        session.saveToken(refresh.getTokenAccess(),refresh.getTokenRefresh());
        SecurityContext.setTokenRefresh(tokenRefresh);
        String tokenAccess=refresh.getTokenAccess();
        SecurityContext.setTokenAccess(tokenAccess);
        return response.request().newBuilder().addHeader("Authorization", "Bearer " + tokenAccess).build();
    }
}
