package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Request.SearchRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class SearchService {
    @Getter
    private static List<String> muscles;
    @Getter
    private static List<String> items;
    @Getter
    private static List<SearchResponse.Exercise> exercises;
    public void search(CallBackHandler callBack, int page){
        SearchRequest request=new SearchRequest(muscles,items);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.search(request,10,page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    exercises=response.body().getExercises();
                    callBack.findExercise(response.body());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public static void setMuscles(List<String> muscles, CallBackHandler callBack){
        if(muscles.isEmpty()){
            callBack.onTools("");
        }else {
            SearchService.muscles = muscles;
        }
    }
    public static void setItems(List<String> items, CallBackHandler callBack){
        if(items.isEmpty()){
            callBack.onTools("");
        }else {
            SearchService.items = items;
        }
    }
}
