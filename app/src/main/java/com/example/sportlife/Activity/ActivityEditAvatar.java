package com.example.sportlife.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ProfileService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

public class ActivityEditAvatar extends CreateActivity{
    @Setter
    public static String nameAvatar;
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
        List<TextView> textViews=new ArrayList<>();
        textViews.add(dialog.findViewById(R.id.Title));
        UIController uiController=new UIController(this,textViews);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
        ProfileService service=new ProfileService();
        service.findAvatars(callBack,dialog);
        Button no=dialog.findViewById(R.id.btnNo);
        Button yes=dialog.findViewById(R.id.btnYes);
        no.setOnClickListener(v->callBack.onSuccess(ActivityProfile.class));
        yes.setOnClickListener(v->{service.updateAvatar(nameAvatar,callBack);});
    }
}
