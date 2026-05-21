package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.Activity.ActivityHome;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
public class AuthService {
    private final SessionManager session;
    public void auth(String name,String password, CallBackHandler callBack){
        AuthRequest authRequest=new AuthRequest(name,password);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.auth(authRequest).enqueue(new retrofit2.Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String tokenAccess = response.body().getAccessToken();
                    String tokenRefresh = response.body().getRefreshToken();
                    session.saveName(name);
                    session.saveToken(tokenAccess, tokenRefresh);
                    callBack.onSuccess(ActivityHome.class);
                }else{
                    callBack.onError(response);
                }
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
}
