package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindScheduleResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

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

            }
        });
    }
}
