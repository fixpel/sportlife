package com.example.sportlife.AndroidBackGround.Security;

import androidx.annotation.NonNull;
import androidx.versionedparcelable.NonParcelField;

import com.example.sportlife.Activity.MainActivity;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
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

public class SecurityInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        try {

            Request request = chain.request();
            String path = request.url().encodedPath();
            if (path.contains("/auth")
                    || path.contains("/refresh")
                    || path.contains("/register")||path.contains("/top")) {
                return chain.proceed(request);
            }
            String token = null;
            try {
                token = SecurityContext.getTokenAccess();
            } catch (Exception ignored) {}
            if (token == null || token.trim().isEmpty()) {
                return chain.proceed(request);
            }
            Request newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            return chain.proceed(newRequest);

        } catch (Exception e) {
            e.printStackTrace();
            return chain.proceed(chain.request());
        }
    }
}
