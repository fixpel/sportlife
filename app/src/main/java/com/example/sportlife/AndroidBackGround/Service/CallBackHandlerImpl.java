package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.Activity.MainActivity;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

@RequiredArgsConstructor
public class CallBackHandlerImpl implements CallBackHandler {
    private final UIController uiController;
    private final ErrorController errorController;

    @Override
    public void onSuccess(Class<? extends Activity> activity) {// осуществляет переход на другой экран!
        uiController.openNextScreen(activity);
    }

    @Override
    public void onError(Response<?> response) {//выводит ошибку, как красную подсветку с текстом !
        this.onTools("1");
        uiController.ErrorAdvice(errorController.parseError(response));
    }

    @Override
    public void onTools(String t) {
        uiController.errorService(t);
    }

    @Override
    public void findTop(FindTopResponse response) {
        this.onTools("2");
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

    @Override
    public void onUnAuth() {
        uiController.openNextScreen(MainActivity.class);
    }
}
