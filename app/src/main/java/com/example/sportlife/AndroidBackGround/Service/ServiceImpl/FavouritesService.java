package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.FavouritesRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.FavouritesResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesService {
    public void create(String name, CallBackHandler callBack){
        FavouritesRequest request=new FavouritesRequest(name);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.crateFavourites(request).enqueue(new Callback<FavouritesResponse>() {
            @Override
            public void onResponse(Call<FavouritesResponse> call, Response<FavouritesResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.onTools(response.body().getMessage());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<FavouritesResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public void delete(String name,CallBackHandler callBack){
        FavouritesRequest request=new FavouritesRequest(name);
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        apiRepository.deleteFavourites(request).enqueue(new Callback<FavouritesResponse>() {
            @Override
            public void onResponse(Call<FavouritesResponse> call, Response<FavouritesResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.onTools(response.body().getMessage());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<FavouritesResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
}
