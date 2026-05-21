package com.example.sportlife.Activity;

import android.os.Bundle;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SplashService;
import com.example.sportlife.R;

public class SplashActivity extends CreateActivity {

    @Override
    protected int getIdLayout() {
        return R.layout.acivity_splash;
    }

    @Override
    protected int getIdView() {
        return R.id.android_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager session=new SessionManager(getApplicationContext());
        UIController uiController=new UIController(this,null);
        ErrorController errorController=new ErrorController();
        CallBackHandlerImpl callBack=new CallBackHandlerImpl(uiController,errorController);
        SplashService service=new SplashService();
        if(session.getAccessToken()==null||session.getRefreshToken()==null){
            service.splash(callBack);
            callBack.onSuccess(MainActivity.class);
        }else{
            service.splash(callBack);
            callBack.onSuccess(ActivityHome.class);
        }

    }

}
