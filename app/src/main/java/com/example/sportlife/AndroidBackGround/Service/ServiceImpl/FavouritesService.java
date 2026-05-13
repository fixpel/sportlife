package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.FavouritesRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.FavouritesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesService {
    public void create(String name){
        FavouritesRequest request=new FavouritesRequest(name);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.crateFavourites(request).enqueue(new Callback<FavouritesResponse>() {
            @Override
            public void onResponse(Call<FavouritesResponse> call, Response<FavouritesResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){

                }
            }

            @Override
            public void onFailure(Call<FavouritesResponse> call, Throwable t) {

            }
        });
    }
    public void delete(){
    }
}
