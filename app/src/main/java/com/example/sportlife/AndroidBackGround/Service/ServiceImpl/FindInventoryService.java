package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class FindInventoryService {
    private   int totalPage;
    public void findInventory(int page,CallBackHandler callBack){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        ErrorController errorController=new ErrorController();
        apiRepository.findInventory(10,page).enqueue(new Callback<FindInventoryResponse>() {
            @Override
            public void onResponse(Call<FindInventoryResponse> call, Response<FindInventoryResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    setTotalPage(response.body().getTotalPage());
                    callBack.findInventory(response.body());
                }else{
                    ErrorResponse errorResponse=errorController.parseError(response);
                    callBack.onError(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<FindInventoryResponse> call, Throwable t) {
                callBack.onNetworkError(t.getMessage());
            }
        });
    }
}
