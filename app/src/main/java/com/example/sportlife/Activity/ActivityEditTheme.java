package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ActivityEditTheme extends ActivityCreate {
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
        SwitchMaterial editTheme=findViewById(R.id.switchTheme);
        SwitchMaterial editLanguage=findViewById(R.id.switchLanguage);

        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
        SessionManager session=new SessionManager(getApplicationContext());

        editTheme.setChecked(session.getTheme().equals("Light"));
        editLanguage.setChecked(session.getLanguage().equals("en"));

        editTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (session.getTheme().equals("Dark")) {
                session.saveTheme("Light");
            } else {
                session.saveTheme("Dark");
            }
            buttonView.postDelayed(() -> {
                recreate();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }, 190);
        });
        editLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(session.getLanguage().equals("ru")){
                session.saveLanguage("en");
            }else{
                session.saveLanguage("ru");
            }
            buttonView.postDelayed(()->{
                recreate();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            },190);
        });
        back.setOnClickListener(v->{callBack.onSuccess(ActivityHome.class);});


    }
}
