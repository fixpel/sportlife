package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.Activity.CreateActivity;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityResult extends CreateActivity {

    private RecyclerView recyclerView;


    @Override
    protected int getIdLayout() {
        return R.layout.activity_result;
    }

    @Override
    protected int getIdView() {
        return R.id.activityResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TextView> textViews=new ArrayList<>();
        textViews.add(findViewById(R.id.result));
        UIController uiController = new UIController(this, textViews);
        CallBackHandler callBack = new CallBackHandlerImpl(uiController);
        Button back = this.findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            callBack.onSuccess(ActivityInventory.class);//назад
        });
    }


}