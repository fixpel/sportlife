package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Client.TranslateClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.UpdateExpertsService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityLevel extends ActivityCreate {
    private String experts;
    private Button btnNovice, btnExperienced, btnPro;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_level;
    }

    @Override
    protected int getIdView() {
        return R.id.activityLevel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnNovice = findViewById(R.id.btnNovice);
        btnExperienced = findViewById(R.id.btnExperienced);
        btnPro = findViewById(R.id.btnPro);
        Button back = findViewById(R.id.btnBack);
        Button save = findViewById(R.id.btnSave);
        TextView error = findViewById(R.id.errorLevel);

        List<TextView> textViews = new ArrayList<>();
        textViews.add(error);

        UIController uiController = new UIController(this, textViews);
        ErrorController errorController = new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController, errorController);
        UpdateExpertsService service = new UpdateExpertsService();
        SessionManager session=new SessionManager(getApplicationContext());


        View.OnClickListener levelClickListener = v -> {

            btnNovice.setSelected(false);
            btnExperienced.setSelected(false);
            btnPro.setSelected(false);

            v.setSelected(true);

            if (v.getId() == R.id.btnNovice) {
                experts = btnNovice.getText().toString();
            } else if (v.getId() == R.id.btnExperienced) {
                experts = btnExperienced.getText().toString();
            } else if (v.getId() == R.id.btnPro) {
                experts = btnPro.getText().toString();
            }
        };

        btnNovice.setOnClickListener(levelClickListener);
        btnExperienced.setOnClickListener(levelClickListener);
        btnPro.setOnClickListener(levelClickListener);
        back.setOnClickListener(v -> {
            callBack.onSuccess(ActivityHome.class);
        });
        save.setOnClickListener(v -> {
            if(experts == null && session.getExperts()!=null){
                experts=session.getExperts();
            }
            service.updateExperts(TranslateClient.unTranslateLevel(this,experts), callBack,session);
        });
    }
}