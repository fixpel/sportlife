package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.FavouritesRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ExerciseCardResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FavouritesResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesService {
    @Setter
    @Getter
    private static int totalPage;
    @Setter
    @Getter
    private static List<ExerciseCardResponse.Exercise> favourites;
    public void create(String name, CallBackHandler callBack){
        FavouritesRequest request=new FavouritesRequest(name);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.createFavourites(request).enqueue(new Callback<FavouritesResponse>() {
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
    public void findFavourites(CallBackHandler callBack,int page){
        ApiRepository apiRepository=RetrofitClient.getApiRepository();
        apiRepository.findFavourites(10,page).enqueue(new Callback<ExerciseCardResponse>() {
            @Override
            public void onResponse(Call<ExerciseCardResponse> call, Response<ExerciseCardResponse> response) {
                if(response.body()!=null&&response.isSuccessful()){
                    setTotalPage(response.body().getTotalPage());
                    callBack.findFavourites(response.body());
                    setFavourites(response.body().getExercises());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<ExerciseCardResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public void findFavourite(CallBackHandler callBack,String name){
        ExerciseCardResponse.Exercise exercise=favourites.stream().filter(e->
                e.getName().equals(name)).findFirst().orElse(null);
        if(exercise==null){
            callBack.onTools("объект не найден");
        }else{
            callBack.findExercise(exercise);
        }
    }
}
