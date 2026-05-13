package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;

import retrofit2.Response;

public interface CallBackHandler {
    void onSuccess(Class<? extends Activity> activity);
    void onError(Response<?> response);
    void onTools(String t);
    void findTop(FindTopResponse response);
    void findInventory(FindInventoryResponse response);
    void findExercise(SearchResponse response);
    void onUnAuth();
    void onCreateFavourite(String name);
    void onDeleteFavourite(String name);
}
