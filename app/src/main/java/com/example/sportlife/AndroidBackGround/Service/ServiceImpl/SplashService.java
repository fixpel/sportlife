package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Response.SplashResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashService {
    public void splash(CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.splash().enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.onTools(response.body().getMessage());
                }else{
                    try {
                        callBack.onTools(response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
}
