package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.media3.exoplayer.ExoPlayer;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

import lombok.Getter;
import lombok.Setter;

public class ActivityExerciseDetail extends CreateActivity {
    @Setter
    public static String nameExercise;
    @Setter
    @Getter
    public static ExoPlayer player;
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(player!=null){
            player.release();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }
    @Override
    protected int getIdLayout() {
        return R.layout.activity_exercise_detail;
    }

    @Override
    protected int getIdView() {
        return R.id.activityExerciseDetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FindTopService findTopService = new FindTopService();
        UIController uiController = new UIController(this, null);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        findTopService.findTop(callBack);



        Button back = this.findViewById(R.id.btnBack);

        back.setOnClickListener(v -> {
            callBack.onSuccess(null);//назад
        });

    }






}