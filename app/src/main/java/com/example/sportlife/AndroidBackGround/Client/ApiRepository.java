package com.example.sportlife.AndroidBackGround.Client;

import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRepository {
    @POST("Employee/auth")
    Call<AuthResponse> auth(@Body AuthRequest request);
}
