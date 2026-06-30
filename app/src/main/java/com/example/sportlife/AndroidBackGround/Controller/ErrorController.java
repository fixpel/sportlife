package com.example.sportlife.AndroidBackGround.Controller;

import android.util.Log;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.google.gson.Gson;

import retrofit2.Response;

public class ErrorController {
    public ErrorResponse parseError(Response response){
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
    public  Response filterError(Response response, CallBackHandler callBack){
        if(!response.isSuccessful()||response.body()==null){
            if(response.code()==500){
                callBack.onTools(parseError(response).getErrors().get("500").toString(),"ApiException");
                return null;
            }
            callBack.onError(response);
            return null;
        }
        return response;
    }
}
