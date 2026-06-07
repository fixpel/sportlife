package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityResult extends ActivityCreate {
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
        int page=0;
        textViews.add(findViewById(R.id.errorResult));
        UIController uiController = new UIController(this, textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        Button back = this.findViewById(R.id.btnBack);
        ImageView next=findViewById(R.id.btnNext);
        ImageView prev=findViewById(R.id.btnPrev);
        prev.setVisibility(View.GONE);
        try {
            SearchService.search(callBack,page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextView tvPage=findViewById(R.id.tvPageNumber);
        next.setOnClickListener(v->{
            int pageint=Integer.parseInt(tvPage.getText().toString());
            if(pageint<SearchService.getTotalPage()){
                pageint=pageint+1;
                tvPage.setText(Integer.toString(pageint));
                next.setVisibility(TextView.VISIBLE);
                try {
                    SearchService.search(callBack,pageint-1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pageint==SearchService.getTotalPage()){
                next.setVisibility(TextView.GONE);
            }
            if(pageint!=1){
                prev.setVisibility(TextView.VISIBLE);
            }
            if(pageint==1){
                prev.setVisibility(TextView.GONE);
            }
        });
        prev.setOnClickListener(v->{
            int pageint=Integer.parseInt(tvPage.getText().toString());
            if(pageint!=1){
                pageint=pageint-1;
                tvPage.setText(Integer.toString(pageint));
                prev.setVisibility(TextView.VISIBLE);
                try {
                    SearchService.search(callBack,pageint-1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pageint==1){
                prev.setVisibility(TextView.GONE);
            }
            if(pageint<SearchService.getTotalPage()){
                next.setVisibility(TextView.VISIBLE);
            }
            if(pageint==SearchService.getTotalPage()){
                next.setVisibility(TextView.GONE);
            }

        });
        back.setOnClickListener(v -> {
            SearchService.getItems().clear();
            callBack.onSuccess(ActivityInventory.class);
        });
    }


}