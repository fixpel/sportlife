package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FavouritesService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ActivityFavouriteDetails extends CreateActivity{
    @Setter
    public static String nameExercise;
    @Override
    protected int getIdLayout() {
        return R.layout.activity_favourite_detailse;
    }
    @Override
    protected int getIdView() {
        return R.id.activityFavouriteDetail;
    }
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        List<TextView> textViews=new ArrayList<>();
        UIController uiController=new UIController(this,textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,errorController);
        FavouritesService service=new FavouritesService();
        service.findFavourite(callBack,nameExercise);
        Button back=findViewById(R.id.btnBack);
        back.setOnClickListener(v->callBack.onSuccess(ActivityFavorites.class));
    }
}
