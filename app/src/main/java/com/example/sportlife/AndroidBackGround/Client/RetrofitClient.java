package com.example.sportlife.AndroidBackGround.Client;

import com.example.sportlife.AndroidBackGround.Security.AuthenticatorRefresh;
import com.example.sportlife.AndroidBackGround.Security.SecurityInterceptor;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final OkHttpClient httpClient= new OkHttpClient.Builder()
            .addInterceptor(new SecurityInterceptor())
            .authenticator(new AuthenticatorRefresh())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(3,TimeUnit.MINUTES)
            .readTimeout(3,TimeUnit.MINUTES)
            .build();
    private static final Retrofit retrofit=new Retrofit.Builder().baseUrl("https://localhost:49182/api/").client(httpClient).addConverterFactory(GsonConverterFactory.create()).build();
    public static ApiRepository getApiRepository(){
        return retrofit.create(ApiRepository.class);
    }
}
