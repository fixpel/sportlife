package com.example.sportlife.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindInventoryService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public  class ActivityInventory extends CreateActivity {
    int numberPage=0;
    @Override
    protected int getIdLayout() {
        return R.layout.activity_equipment_selection;
    }

    @Override
    protected int getIdView() {
        return R.id.activityEquipment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TextView> textViews=new ArrayList<>();
        textViews.add(findViewById(R.id.inventory));
        UIController uiController=new UIController(this,textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,errorController);
        FindInventoryService service=new FindInventoryService();
        Button back=findViewById(R.id.btnBack);
        Button save=findViewById(R.id.btnSave);
        ImageView next=findViewById(R.id.btnNext);
        ImageView prev=findViewById(R.id.btnPrev);
        prev.setVisibility(TextView.GONE);
        TextView page=findViewById(R.id.tvPageNumber);
        page.setText(Integer.toString(numberPage+1));
        service.findInventory(numberPage,callBack);
        if(numberPage+1==service.getTotalPage()){
            next.setVisibility(TextView.GONE);;
        }
        next.setOnClickListener(v->{
            int pageInt=Integer.parseInt(page.getText().toString());
            if(pageInt==service.getTotalPage()){
                next.setVisibility(TextView.GONE);
            }
            if(pageInt<service.getTotalPage()){
                pageInt=pageInt+1;
                page.setText(Integer.toString(pageInt));
                prev.setVisibility(TextView.VISIBLE);
                service.findInventory(pageInt-1,callBack);
            }

        });
        prev.setOnClickListener(v->{
            int pageint=Integer.parseInt(page.getText().toString());
            if(pageint==1){
                prev.setVisibility(TextView.GONE);
            }
            if(pageint!=1){
                pageint=pageint-1;
                page.setText(Integer.toString(pageint));
                next.setVisibility(TextView.VISIBLE);
                service.findInventory(pageint-1,callBack);
            }
        });
        back.setOnClickListener(v->{
            SearchService.getMuscles().clear();
            callBack.onSuccess(ActivityMuscle.class);//назад
        });
        save.setOnClickListener(v->{
            if(SearchService.setItems(SearchService.getItems(),callBack)){
                callBack.onSuccess(ActivityResult.class);
            }
        });
    }




}
