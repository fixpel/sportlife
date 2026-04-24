package com.example.sportlife.AndroidBackGround.Client;

import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.RefreshRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.RegistrationRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRepository {
    @POST("Employee/auth")
    Call<AuthResponse> auth(@Body AuthRequest request);
    @POST("Employee/create")
    Call<RegistrationResponse> registration(@Body RegistrationRequest request);
    @POST("Employee/refresh")
    Call<RefreshResponse> refresh(@Body RefreshRequest request);
    @GET("Employee/top")
    Call<FindTopResponse> findTop();
}
