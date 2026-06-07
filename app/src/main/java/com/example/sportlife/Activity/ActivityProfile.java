package com.example.sportlife.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ProfileService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityProfile extends ActivityCreate {



    @Override
    protected int getIdLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getIdView() {
        return R.id.activityProfile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TextView> textViews=new ArrayList<>();
        textViews.add(findViewById(R.id.tvName));
        textViews.add(findViewById(R.id.tvExpertise));
        textViews.add(findViewById(R.id.tvActivity));
        textViews.add(findViewById(R.id.tvRating));
        Button back =findViewById(R.id.btnBack);
        ImageView edit=findViewById(R.id.imageButton);
        ImageView editAvatar=findViewById(R.id.btnChangeAvatar);
        UIController uiController = new UIController(this, textViews);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController,errorController);
        ProfileService service=new ProfileService();
        service.info(callBack);
        back.setOnClickListener(v -> {
            callBack.onSuccess(ActivityHome.class);
        });
        edit.setOnClickListener(v->{
            View view=getLayoutInflater().inflate(R.layout.edit_dialog,null);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(view)
                    .create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);

            if (dialog.getWindow() != null) {
                dialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                );
            }
            dialog.show();
            Button no = dialog.findViewById(R.id.btnNo);
            Button yes = dialog.findViewById(R.id.btnYes);
            EditText name = dialog.findViewById(R.id.tvName);
            TextView errorName = dialog.findViewById(R.id.errorName);
            name.requestFocus();
            no.setOnClickListener(n -> dialog.dismiss());
            yes.setOnClickListener(y -> {
                String newName = name.getText().toString().trim();
                if (!newName.isEmpty()) {
                    dialog.dismiss();
                } else {
                    errorName.setText("Введите имя");
                    errorName.setVisibility(View.VISIBLE);
                }
            });
        });
        editAvatar.setOnClickListener(v->callBack.onSuccess(ActivityEditAvatar.class));
    }






}