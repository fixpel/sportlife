package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class SecurityInterceptor implements Interceptor {
    private final Context context;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        SessionManager session = new SessionManager(context);
        Request request = chain.request();
        if (request.header("Authorization") != null) {
            return chain.proceed(request);
        }
        String path = request.url().encodedPath();
        Log.d("[INTERCEPTOR]","[INTERCEPTOR_1]");
        Log.d("[INTERCEPTOR_BODY_2]",request.url().toString());
        if (path.contains("/auth")
                || path.contains("/refresh")
                || path.contains("/create")
                || path.contains("/top")
                || path.contains("/splash")) {
            return chain.proceed(request);
        }
        Log.d("[INTERCEPTOR]","[INTERCEPTOR_2]");
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
