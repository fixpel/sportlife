package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.versionedparcelable.NonParcelField;

import com.example.sportlife.Activity.MainActivity;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Client.SessionContext;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Request.RefreshRequest;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import java.io.IOException;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class SecurityInterceptor implements Interceptor {
    private final Context context;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        if (request.header("Authorization") != null) {
            return chain.proceed(request);
        }
        String path = request.url().encodedPath();

        if (path.contains("/auth")
                || path.contains("/refresh")
                || path.contains("/register")
                || path.contains("/top")
                || path.contains("/splash")) {

            return chain.proceed(request);
        }
        SessionManager session = new SessionManager(context);

        String token = session.getAccessToken();

        if (token == null || token.trim().isEmpty()) {
            return chain.proceed(request);
        }
        Request newRequest = request.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(newRequest);
    }
}
