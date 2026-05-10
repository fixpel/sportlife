package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CallBackHandlerImpl implements CallBackHandler {
    private final UIController uiController;

    @Override
    public void onSuccess(Class<? extends Activity> activity) {// осуществляет переход на другой экран!
        uiController.openNextScreen(activity);
    }

    @Override
    public void onError(ErrorResponse error) {//выводит ошибку, как красную подсветку с текстом !
        uiController.ErrorAdvice(error);
    }

    @Override
    public void onNetworkError(String t) {
        uiController.errorService(t);
    }

    @Override
    public void findTop(FindTopResponse response) {
        uiController.findTop(response);
    }

    @Override
    public void findInventory(FindInventoryResponse response) {
        uiController.findInventory(response);
    }

    @Override
    public void findExercise(SearchResponse response) {
        uiController.findExercises(response);
    }
}
