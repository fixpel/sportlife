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
    public void search(CallBackHandler callBack, int page){
        SearchRequest request=new SearchRequest(muscles,items);
        ErrorController errorController=new ErrorController();
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.search(request,10,page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    callBack.findExercise(response.body());
                }else{
                    ErrorResponse errorResponse=errorController.parseError(response);
                    callBack.onError(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                callBack.onNetworkError(t.getMessage());
            }
        });
    }
    public static void setMuscles(List<String> muscles, CallBackHandler callBack){
        if(muscles.isEmpty()){
            callBack.onNetworkError("");
        }else {
            SearchService.muscles = muscles;
        }
    }
    public static void setItems(List<String> items, CallBackHandler callBack){
        if(items.isEmpty()){
            callBack.onNetworkError("");
        }else {
            SearchService.items = items;
        }
    }
}
