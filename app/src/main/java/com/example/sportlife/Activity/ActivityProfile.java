package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.Activity.CreateActivity;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ProfileInfoService;
import com.example.sportlife.R;

public class ActivityProfile extends CreateActivity {



    @Override
    protected int getIdLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getIdView() {
        return R.id.activityProfile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIController uiController = new UIController(this, null);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        ProfileInfoService service=new ProfileInfoService();
        service.info(callBack);
        Button back =findViewById(R.id.btnBack);

        back.setOnClickListener(v -> {
            callBack.onSuccess(null);//назад
        });

    }






}