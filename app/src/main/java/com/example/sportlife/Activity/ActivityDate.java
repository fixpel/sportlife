package com.example.sportlife.Activity;

import android.os.Bundle;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;

public class ActivityDate extends ActivityCreate {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_date;
    }

    @Override
    protected int getIdView() {
        return R.id.activitySchedule;
    }
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
    }
}
