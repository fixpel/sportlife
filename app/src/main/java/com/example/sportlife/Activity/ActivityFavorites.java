package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.Activity.CreateActivity;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FavouritesService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityFavorites extends CreateActivity {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    protected int getIdView() {
        return R.id.activityFavorites;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int page=0;
        List<TextView> textViews=new ArrayList<>();
        textViews.add(findViewById(R.id.favorites));
        UIController uiController = new UIController(this,textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        FavouritesService service=new FavouritesService();
        service.findFavourites(callBack,page);
        ImageView next=findViewById(R.id.btnNext);
        ImageView prev=findViewById(R.id.btnPrev);
        prev.setVisibility(View.GONE);
        Button back = this.findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            callBack.onSuccess(ActivityHome.class);//назад
        });
        TextView tvPage=findViewById(R.id.tvPageNumber);
        next.setOnClickListener(v->{
            int pageint=Integer.parseInt(tvPage.getText().toString());
            if(pageint< FavouritesService.getTotalPage()){
                pageint=pageint+1;
                tvPage.setText(Integer.toString(pageint));
                next.setVisibility(TextView.VISIBLE);
               service.findFavourites(callBack,pageint-1);
            }
            if(pageint==FavouritesService.getTotalPage()){
                next.setVisibility(TextView.GONE);
            }
        });
        prev.setOnClickListener(v-> {
            int pageint = Integer.parseInt(tvPage.getText().toString());
            if (pageint != 0) {
                pageint = pageint - 1;
                tvPage.setText(Integer.toString(pageint));
                prev.setVisibility(TextView.VISIBLE);
                service.findFavourites(callBack,pageint-1);
            }
            if (pageint == 0) {
                prev.setVisibility(TextView.GONE);
            }
        });
    }
}