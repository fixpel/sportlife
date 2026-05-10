package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;

public interface CallBackHandler {
    void onSuccess(Class<? extends Activity> activity);
    void onError(ErrorResponse error);
    void onNetworkError(String t);
    void findTop(FindTopResponse response);
    void findInventory(FindInventoryResponse response);
    void findExercise(SearchResponse response);
}
