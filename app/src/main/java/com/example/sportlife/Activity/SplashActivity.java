package com.example.sportlife.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SportLife_Splash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_splash);
        SecurityContext context = new SecurityContext();
        SessionManager session=new SessionManager(getApplicationContext(),context);
        UIController uiController=new UIController(this,null);
        CallBackHandlerImpl callBack=new CallBackHandlerImpl(uiController);
        if(session.getRefreshToken()==null){
            callBack.onSuccess(MainActivity.class);
        }else{
            callBack.onSuccess(ActivityHome.class);
        }
    }

}
