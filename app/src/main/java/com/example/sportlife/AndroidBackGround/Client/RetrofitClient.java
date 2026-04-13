package com.example.sportlife.AndroidBackGround.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:49182/api/").addConverterFactory(GsonConverterFactory.create()).build();
    public static ApiRepository getApiRepository(){
        return retrofit.create(ApiRepository.class);
    }
}
