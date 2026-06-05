package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ProfileService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class ActivitySettings extends CreateActivity {



    @Override
    protected int getIdLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected int getIdView() {
        return R.id.activitySettings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button editTheme=findViewById(R.id.btnToggleTheme);
        Button back=findViewById(R.id.btnBack);

        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
        SessionManager session=new SessionManager(getApplicationContext());

        back.setOnClickListener(v->{
            callBack.onSuccess(ActivityHome.class);
        });
        editTheme.setOnClickListener(v->{
            if(session.getTheme().equals("Dark")){
                session.saveTheme("Light");
                setTheme(R.style.LightTheme);
            }else{
                session.saveTheme("Dark");
                setTheme(R.style.BaseTheme);
            }
        });
    }






}