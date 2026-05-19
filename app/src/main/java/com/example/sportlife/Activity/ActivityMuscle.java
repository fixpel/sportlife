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
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.SearchService;
import com.example.sportlife.R;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ActivityMuscle extends CreateActivity {
    int currentPage = 1;
    List<String> muscles=new ArrayList<>();

    @Override
    protected int getIdLayout() {
        return R.layout.activity_muscle_selection;
    }

    @Override
    protected int getIdView() {
        return R.id.activityMusc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        UIController uiController=new UIController(this,null);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,errorController);


        findViewById(R.id.btnPrev).setOnClickListener(v -> switchPage(-1));
        findViewById(R.id.btnNext).setOnClickListener(v -> switchPage(1));


        Button back=findViewById(R.id.btnBack);
        Button save=findViewById(R.id.btnSave);

        back.setOnClickListener(v->{
            SearchService.getMuscles().clear();
            callBack.onSuccess(ActivityLevel.class);//назад
        });
        save.setOnClickListener(v->{
            if(SearchService.setMuscles(muscles, callBack)){
                callBack.onSuccess(ActivityInventory.class);
            }
        });

        setupZoneButtons();
    }


    private void setupZoneButtons() {
        // Кнопки для передней части (стр. 1)
        setupZoneButton(R.id.zoneBiceps);
        setupZoneButton(R.id.zoneDeltaFront);
        setupZoneButton(R.id.zoneGrud);
        setupZoneButton(R.id.zonePress);
        setupZoneButton(R.id.zoneKosaya);
        setupZoneButton(R.id.zoneBrah);
        setupZoneButton(R.id.zonePramayaZhivota);
        setupZoneButton(R.id.zoneQuadriceps);
        setupZoneButton(R.id.zonePlech);
        setupZoneButton(R.id.zoneKardio);

        // Кнопки для задней части (стр. 2)
        setupZoneButton(R.id.zoneTrapezius);

        setupZoneButton(R.id.zoneTriceps);
        setupZoneButton(R.id.zoneJagodichnye);
        setupZoneButton(R.id.zone4glav);
        setupZoneButton(R.id.zoneBricepsBedra);
        setupZoneButton(R.id.zoneIkry);
        setupZoneButton(R.id.zoneShiro);



    }

    private void setupZoneButton(int buttonId) {
        Button zoneButton = findViewById(buttonId);
        if (zoneButton != null) {
            zoneButton.setOnClickListener(v -> {
                // Переключаем состояние кнопки
                boolean isSelected = !zoneButton.isSelected();
                if(isSelected){
                    muscles.add(zoneButton.getText().toString());
                }else{
                    muscles.remove(zoneButton.getText().toString());
                }
                zoneButton.setSelected(isSelected);

                // Можно добавить логирование или сохранение
                // Например: selectedMuscles.put(getMuscleName(buttonId), isSelected);
            });
        }
    }


    private void switchPage(int direction){
        currentPage += direction;
        if (currentPage < 1) currentPage = 1;
        if (currentPage > 2) currentPage = 2;
        ImageView imgFront = findViewById(R.id.imgFront);
        ImageView imgBack = findViewById(R.id.imgBack);
        TextView tvPageNumber = findViewById(R.id.tvPageNumber);

        tvPageNumber.setText(String.valueOf(currentPage));

        if (currentPage == 1) {
            imgFront.setVisibility(View.VISIBLE);
            imgBack.setVisibility(View.GONE);

            showZonesForPage(1);
        } else {
            imgFront.setVisibility(View.GONE);
            imgBack.setVisibility(View.VISIBLE);

            showZonesForPage(2);
        }
    }


    private void showZonesForPage(int page) {
        // Зоны ПЕРЕДНЕЙ части
        findViewById(R.id.zoneBiceps).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneDeltaFront).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneGrud).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zonePress).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneKosaya).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zonePramayaZhivota).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneQuadriceps).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zonePlech).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneKardio).setVisibility(page == 1 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneBrah).setVisibility(page == 1 ? View.VISIBLE : View.GONE);

        // Зоны ЗАДНЕЙ части
        findViewById(R.id.zoneTrapezius).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneTriceps).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneJagodichnye).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zone4glav).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneBricepsBedra).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneIkry).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.zoneShiro).setVisibility(page == 2 ? View.VISIBLE : View.GONE);
    }
}