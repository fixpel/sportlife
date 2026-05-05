package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.AuthService;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CreateActivity {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getIdView() {
        return R.id.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button appCompatButton = findViewById(R.id.btn_login);
        EditText editTextName = findViewById(R.id.et_name);
        EditText editTextPassword = findViewById(R.id.et_password);
        TextView noneAcount = findViewById(R.id.none_account);
        List<TextView> editTexts=new ArrayList<>();
        editTexts.add(editTextPassword);
        editTexts.add(editTextName);
        UIController uiController=new UIController(this,editTexts);
        SecurityContext context=new SecurityContext();
        SessionManager session=new SessionManager(getApplicationContext(),context);
        AuthService authService =new AuthService(session);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController);

        noneAcount.setOnClickListener(v->{callBack.onSuccess(ActivityLogin.class);});
        appCompatButton.setOnClickListener(v -> authService.auth(editTextName.getText().toString(),editTextPassword.getText().toString(),callBack));


    }
}