package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;

public interface CallBackHandler {
    public void onSuccess(Class<? extends Activity> activity);
    public void onError(ErrorResponse error);
    void onNetworkError(Throwable t);
    void findTop(FindTopResponse response);
}
