package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.R;

import org.jspecify.annotations.NonNull;

public class ActivityHome extends CreateActivity {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_top;
    }

    @Override
    protected int getIdView() {
        return R.id.activityTop;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView name=findViewById(R.id.welcome);
        name.setText(SecurityContext.getName());
        FindTopService findTopService=new FindTopService();
        UIController uiController=new UIController(this,null);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController);
        findTopService.findTop(callBack);
        Button search=this.findViewById(R.id.btnSearch);
        Button history=this.findViewById(R.id.btnHistory);
        search.setOnClickListener(v->{
            callBack.onSuccess(ActivityLevel.class);//активность поиска.
        });
        history.setOnClickListener(v->{
            callBack.onSuccess(null);//активность истории
        });
    }
}
