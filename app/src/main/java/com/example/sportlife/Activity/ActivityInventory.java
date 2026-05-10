package com.example.sportlife.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindInventoryService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

public  class ActivityInventory extends CreateActivity {
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
        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController);
        FindInventoryService service=new FindInventoryService();
        Button back=findViewById(R.id.btnBack);
        Button save=findViewById(R.id.btnSave);
        ImageView next=findViewById(R.id.btnNext);
        ImageView prev=findViewById(R.id.btnPrev);
        TextView page=findViewById(R.id.tvPageNumber);
        next.setOnClickListener(v->{
            int pageint=Integer.parseInt(page.getText().toString());
            if(pageint<service.getTotalPage()){
                pageint=pageint+1;
                page.setText(Integer.toString(pageint));
                next.setVisibility(TextView.VISIBLE);
                service.findInventory(pageint-1,callBack);
            }
            if(pageint==service.getTotalPage()){
                next.setVisibility(TextView.GONE);
            }

        });
        prev.setOnClickListener(v->{
            int pageint=Integer.parseInt(page.getText().toString());
            if(pageint!=1){
                pageint=pageint-1;
                page.setText(Integer.toString(pageint));
                prev.setVisibility(TextView.VISIBLE);
                service.findInventory(pageint-1,callBack);
            }
            if(pageint==1){
                prev.setVisibility(TextView.GONE);
            }
        });
        back.setOnClickListener(v->{
            callBack.onSuccess(ActivityMuscle.class);//назад
        });
        save.setOnClickListener(v->{
            SearchService.setItems(SearchService.getItems(),callBack);
            callBack.onSuccess(ActivityResult.class);//сохранить
        });
    }




}
