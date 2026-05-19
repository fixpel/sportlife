package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import androidx.annotation.NonNull;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.SearchRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ExerciseCardResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class SearchService {
    @Getter
    private static List<String> muscles=new ArrayList<>();
    @Getter
    private static List<String> items=new ArrayList<>();
    @Getter
    private static List<ExerciseCardResponse.Exercise> exercises;
    @Getter
    private static int totalPage;
    public static void search(CallBackHandler callBack, int page){
        SearchRequest request=new SearchRequest(muscles,items);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        callBack.onTools(items.toString());
        apiRepository.search(request,10,page).enqueue(new Callback<ExerciseCardResponse>() {
            @Override
            public void onResponse(@NonNull Call<ExerciseCardResponse> call, @NonNull Response<ExerciseCardResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    exercises=response.body().getExercises();
                    totalPage=response.body().getTotalPage();
                    callBack.findExercises(response.body());
                }else{
                    callBack.onError(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ExerciseCardResponse> call, @NonNull Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    }
    public static Boolean setMuscles(List<String> muscles, CallBackHandler callBack){
        if(muscles.isEmpty()){
            callBack.onTools("");
            return false;
        }else {
            SearchService.muscles = muscles;
            return true;
        }
    }
    public static Boolean setItems(List<String> items, CallBackHandler callBack){
        if(items.isEmpty()){
            callBack.onTools("");
            return false;
        }else {
            return true;
        }
    }
    public static void findExercise(CallBackHandler callBack,String name){
        ExerciseCardResponse.Exercise exercise=exercises.stream().filter(e->
            e.getName().equals(name)).findFirst().orElse(null);
        if(exercise==null){
            callBack.onTools("объект не найден");
        }else{
            callBack.findExercise(exercise);
        }
    }
}
