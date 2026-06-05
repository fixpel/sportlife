package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.UiContext;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;

public class ActivityEditTheme extends CreateActivity{
    @Override
    protected int getIdLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected int getIdView() {
        return R.id.activitySettings;
    }
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Button back=findViewById(R.id.btnBack);
        Button editTheme=findViewById(R.id.btnToggleTheme);

        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
        SessionManager session=new SessionManager(getApplicationContext());

        editTheme.setOnClickListener(v->{
            if(session.getTheme().equals("Dark")){
                 session.saveTheme("Light");
                 recreate();
            }else{
                session.saveTheme("Dark");
                recreate();
            }
        });
        back.setOnClickListener(v->{callBack.onSuccess(ActivityHome.class);});


    }
}
