package com.example.sportlife.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ActivityEditName extends CreateActivity{
    @Override
    protected int getIdLayout() {
        return R.layout.edit_dialog;
    }

    @Override
    protected int getIdView() {
        return R.id.editDialog;
    }
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        View view=getLayoutInflater().inflate(R.layout.edit_dialog,null);
        AlertDialog dialog=new AlertDialog.Builder(this).setView(view).create();
        dialog.show();
        Button no=dialog.findViewById(R.id.btnNo);
        Button yes=dialog.findViewById(R.id.btnYes);
        EditText name=dialog.findViewById(R.id.tvName);
        TextView errorName=dialog.findViewById(R.id.errorName);
        List<TextView> textViews =new ArrayList<>();
        textViews.add(errorName);
        UIController uiController=new UIController(this,textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,errorController);
        SessionManager session= new SessionManager(getApplicationContext());
        ProfileService service=new ProfileService();
        no.setOnClickListener(v->callBack.onSuccess(ActivityProfile.class));
        yes.setOnClickListener(v->service.updateName(name.getText().toString(),callBack,session));
    }
}
