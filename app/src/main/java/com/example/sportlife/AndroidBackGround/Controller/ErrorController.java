package com.example.sportlife.AndroidBackGround.Controller;

import android.util.Log;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import retrofit2.Response;

public class ErrorController {
    public ErrorResponse parseError(Response<?> response,CallBackHandler callBack){
        try {
            if (response.errorBody() == null) {
                return null;
            }
            String json = response.errorBody().string();
            Gson gson = new Gson();
            return gson.fromJson(json, ErrorResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
