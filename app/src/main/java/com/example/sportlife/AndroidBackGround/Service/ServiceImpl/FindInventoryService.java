package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import androidx.fragment.app.strictmode.GetTargetFragmentRequestCodeUsageViolation;

import com.example.sportlife.Activity.ActivityInventory;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindInventoryService {
    @Setter
    @Getter
    private static int totalPage;
    public void findInventory(int page,CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.findInventory(10,page).enqueue(new Callback<FindInventoryResponse>() {
            @Override
            public void onResponse(Call<FindInventoryResponse> call, Response<FindInventoryResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    setTotalPage(response.body().getTotalPage());
                    callBack.findInventory(response.body());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<FindInventoryResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
}
