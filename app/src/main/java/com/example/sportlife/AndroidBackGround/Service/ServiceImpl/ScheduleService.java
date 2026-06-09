package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.util.Log;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.ScheduleRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindScheduleResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ScheduleResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleService {
    public void findSchedule(CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.findSchedule().enqueue(new Callback<FindScheduleResponse>() {
            @Override
            public void onResponse(Call<FindScheduleResponse> call, Response<FindScheduleResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.findSchedule(response.body());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<FindScheduleResponse> call, Throwable t) {
                callBack.onTools(t.getMessage(),t.getClass().getSimpleName());
            }
        });
    }
    public  static void createSchedule(CallBackHandler callBack, String name, String time){
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        ScheduleRequest request=new ScheduleRequest(name,time);
        apiRepository.crateSchedule(request).enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.onTools(response.body().getMessage(),"Message");
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                callBack.onTools(t.getMessage(),t.getClass().getSimpleName());
            }
        });
    }
    public static void deleteSchedule(CallBackHandler callBack, String name, String time){
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        ScheduleRequest request=new ScheduleRequest(name,time);
        apiRepository.deleteSchedule(request).enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.onTools(response.body().getMessage(),"Message");
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                callBack.onTools(t.getMessage(),t.getClass().getSimpleName());
            }
        });
    }
}
