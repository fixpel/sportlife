package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

import java.io.IOException;

import lombok.Setter;

public class ActivityResultDetail extends ActivityCreate {
    @Setter
    public static String idExercise;
    @Override
    protected int getIdLayout() {
        return R.layout.activity_result_detail;
    }

    @Override
    protected int getIdView() {
        return R.id.activityExerciseDetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIController uiController = new UIController(this, null);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        try {
            SearchService.findExercise(callBack,idExercise);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Button back = this.findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            callBack.onSuccess(ActivityResult.class);
        });

    }






}