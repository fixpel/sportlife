package com.example.sportlife.AndroidBackGround.Security;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportlife.Activity.ActivityHome;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

@RequiredArgsConstructor
public class AuthenticatorRefresh implements Authenticator {
    private final RefreshService refreshService;
    private final SessionManager session;
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) {
        String tokenRefresh=SecurityContext.createContext().getTokenRefresh();
        RefreshResponse refresh=refreshService.refresh(tokenRefresh);
        session.saveToken(refresh.getTokenAccess(),refresh.getTokenRefresh());
        String tokenAccess=refresh.getTokenAccess();
        return response.request().newBuilder().addHeader("Authorization", "Bearer " + tokenAccess).build();
    }
}
