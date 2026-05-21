package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.Activity.ActivityProfile;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.UpdateEmployeeRequest;
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
    public void updateName(String name,CallBackHandler callBack,SessionManager session){
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
    public void findAvatars(CallBackHandler callBack){

    }
}
