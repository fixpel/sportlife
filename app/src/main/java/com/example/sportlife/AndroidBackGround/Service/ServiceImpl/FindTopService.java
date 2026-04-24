package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.app.Activity;

import com.example.sportlife.Activity.ActivityHome;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiredArgsConstructor
public class FindTopService {
    public void findTop(CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.findTop().enqueue(new Callback<FindTopResponse>() {
            @Override
            public void onResponse(Call<FindTopResponse> call, Response<FindTopResponse> response) {
                if(response.isSuccessful()&&response.body()!=null) {
                    callBack.findTop(response.body());//будет выводить объекты на экран
                }else{
                    ErrorController errorController=new ErrorController();
                    ErrorResponse errorResponse;
                    errorResponse=errorController.parseError(response);
                    callBack.onError(errorResponse);
                }
            }
            @Override
            public void onFailure(Call<FindTopResponse> call, Throwable t) {
                callBack.onNetworkError(t);
            }
        });
    }
}
