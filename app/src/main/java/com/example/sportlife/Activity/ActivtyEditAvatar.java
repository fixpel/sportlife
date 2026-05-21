package com.example.sportlife.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;

public class ActivtyEditAvatar extends CreateActivity{
    @Override
    protected int getIdLayout() {
        return R.layout.edit_avatar;
    }

    @Override
    protected int getIdView() {
        return R.id.editAvatar;
    }
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        View view=getLayoutInflater().inflate(R.layout.edit_avatar,null);
        AlertDialog dialog=new AlertDialog.Builder(this).setView(view).create();
        dialog.show();
        Button no=dialog.findViewById(R.id.btnNo);
        Button yes=dialog.findViewById(R.id.btnYes);
        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
        no.setOnClickListener(v->callBack.onSuccess(ActivityProfile.class));

    }
}
