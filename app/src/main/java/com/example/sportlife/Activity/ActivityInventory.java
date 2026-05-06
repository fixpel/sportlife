package com.example.sportlife.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

import org.jspecify.annotations.NonNull;

public class ActivityInventory extends CreateActivity {

    private TextView tvPageNumber;
    private int currentPage = 1;
    private int maxPages = 2;


    @Override
    protected int getIdLayout() {
        return R.layout.activity_equipment_selection;
    }

    @Override
    protected int getIdView() {
        return R.id.activityInventory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FindTopService findTopService = new FindTopService();
        UIController uiController = new UIController(this, null);
        CallBackHandler callBack = new CallBackHandlerImpl(uiController);
        findTopService.findTop(callBack);

        Button back=this.findViewById(R.id.btnBack);
        Button save=this.findViewById(R.id.btnSave);
// ПИРИВЕТ ДИИИМААА
        back.setOnClickListener(v->{
            callBack.onSuccess(null);//назад
        });
        save.setOnClickListener(v->{
            callBack.onSuccess(null);//сохранить
        });

    }
}
