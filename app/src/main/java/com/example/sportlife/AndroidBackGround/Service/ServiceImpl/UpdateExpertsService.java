package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.util.Log;

import com.example.sportlife.Activity.ActivityMuscle;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Request.ExpertsRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.UpdateResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateExpertsService {
    public void updateExperts(String experts, CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        ExpertsRequest request=new ExpertsRequest(experts);
        apiRepository.updateExperts(request).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if(response.isSuccessful()&&response.body()!=null) {
                    callBack.onSuccess(ActivityMuscle.class);
                }else{
                    callBack.onError(response);
                }
            }
            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
}
