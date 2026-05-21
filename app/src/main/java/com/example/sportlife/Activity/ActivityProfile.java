package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ProfileService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

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
        List<TextView> textViews=new ArrayList<>();
        textViews.add(findViewById(R.id.tvName));
        textViews.add(findViewById(R.id.tvExpertise));
        textViews.add(findViewById(R.id.tvActivity));
        textViews.add(findViewById(R.id.tvRating));
        Button back =findViewById(R.id.btnBack);
        ImageView edit=findViewById(R.id.imageButton);
        UIController uiController = new UIController(this, textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        ProfileService service=new ProfileService();
        service.info(callBack);
        back.setOnClickListener(v -> {
            callBack.onSuccess(ActivityHome.class);//назад
        });
        edit.setOnClickListener(v->callBack.onSuccess(ActivityEdit.class));
    }






}