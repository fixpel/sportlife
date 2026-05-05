package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.SearchRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.util.List;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class SearchService {
    private static List<String> muscles;
    private static List<String> items;
    public void search(CallBackHandler callBack, int page){
        SearchRequest request=new SearchRequest(muscles,items);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.search(request,10,page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){

                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }
    public List<String> getMuscles(){
        return muscles;
    }
    public List<String> getItems(){
        return items;
    }
    public void setMuscles(List<String> muscles){
        SearchService.muscles =muscles;
    }
    public void setItems(List<String> items){
        SearchService.items =items;
    }
}
