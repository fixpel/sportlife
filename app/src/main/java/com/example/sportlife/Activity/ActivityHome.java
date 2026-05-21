package com.example.sportlife.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

public class ActivityHome extends CreateActivity {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_top;
    }

    @Override
    protected int getIdView() {
        return R.id.activityTop;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView name=findViewById(R.id.welcome);
        SessionManager sesssion=new SessionManager(getApplicationContext());
        name.setText("Добро пожаловать "+sesssion.getName());
        FindTopService findTopService=new FindTopService();
        UIController uiController=new UIController(this,null);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,errorController);
        findTopService.findTop(callBack);
        Button search=findViewById(R.id.btnSearch);
        Button history=findViewById(R.id.btnHistory);
        ImageView profile=findViewById(R.id.btnProfile);
        search.setOnClickListener(v->{
            callBack.onSuccess(ActivityLevel.class);//активность поиска.
        });
        history.setOnClickListener(v->{
            callBack.onSuccess(ActivityFavorites.class);//активность истории
        });
        profile.setOnClickListener(v->callBack.onSuccess(ActivityProfile.class));
    }
}
