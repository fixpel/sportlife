package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.Activity.ActivityHome;
import com.example.sportlife.Activity.ActivityLogin;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
public class AuthService {
    private final SessionManager session;
    public void auth(String name,String password, CallBackHandler callback){
        ErrorController errorController=new ErrorController();
        AuthRequest authRequest=new AuthRequest(name,password);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.auth(authRequest).enqueue(new retrofit2.Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String tokenAccess=response.body().getTokenAccess();
                    String tokenRefresh=response.body().getTokenRefresh();
                    SecurityContext.setName(name);
                    session.saveToken(tokenAccess,tokenRefresh);
                    callback.onSuccess(ActivityHome.class);
                }else{
                    ErrorResponse errorResponse;
                    errorResponse = errorController.parseError(response);
                    callback.onError(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callback.onNetworkError(t.getMessage());
            }
        });
    }
}
