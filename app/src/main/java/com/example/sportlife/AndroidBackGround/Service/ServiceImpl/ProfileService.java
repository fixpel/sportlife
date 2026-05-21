package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.app.AlertDialog;

import com.example.sportlife.Activity.ActivityProfile;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.UpdateEmployeeRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindAvatarResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ProfileResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.UpdateEmployeeResponse;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileService {
    public void info(CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.infoProfile().enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.profile(response.body());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public void updateName(String name, CallBackHandler callBack, SessionManager session){
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        UpdateEmployeeRequest request=new UpdateEmployeeRequest(name,null);
        apiRepository.update(request).enqueue(new Callback<UpdateEmployeeResponse>() {
            @Override
            public void onResponse(Call<UpdateEmployeeResponse> call, Response<UpdateEmployeeResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String accessToken=response.body().getAccessToken();
                    String refreshToken=response.body().getRefreshToken();
                    session.saveToken(accessToken,refreshToken);
                    session.saveName(name);
                    callBack.onSuccess(ActivityProfile.class);
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<UpdateEmployeeResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public void updateAvatar(String avatar,CallBackHandler callBack){
        UpdateEmployeeRequest request=new UpdateEmployeeRequest(null,avatar);
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        apiRepository.update(request).enqueue(new Callback<UpdateEmployeeResponse>() {
            @Override
            public void onResponse(Call<UpdateEmployeeResponse> call, Response<UpdateEmployeeResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.onTools("аватар обновлен");
                    callBack.onSuccess(ActivityProfile.class);
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<UpdateEmployeeResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public void findAvatars(CallBackHandler callBack, AlertDialog dialog){
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        apiRepository.findAvatars().enqueue(new Callback<FindAvatarResponse>() {
            @Override
            public void onResponse(Call<FindAvatarResponse> call, Response<FindAvatarResponse> response) {
                if(response.body()!=null&&response.isSuccessful()){
                    callBack.findAvatars(response.body(),dialog);
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<FindAvatarResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
}
