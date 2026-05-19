package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ExerciseCardResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ProfileResponse;

import retrofit2.Response;

public interface CallBackHandler {
    void onSuccess(Class<? extends Activity> activity);
    void onError(Response<?> response);
    void onTools(String t);
    void findTop(FindTopResponse response);
    void findInventory(FindInventoryResponse response);
    void findExercises(ExerciseCardResponse response);
    void findExercise(ExerciseCardResponse.Exercise exercise);
    void onUnAuth();
    void onCreateFavourite(String name);
    void onDeleteFavourite(String name);
    void findFavourites(ExerciseCardResponse response);
    void profile(ProfileResponse response);
}
