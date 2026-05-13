package com.example.sportlife.Activity;

import android.os.Bundle;
import android.view.View;
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
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RegistrationService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

public class ActivityLogin extends CreateActivity {
    @Override
    protected int getIdLayout(){
        return R.layout.activity_login;
    }
    @Override
    protected int getIdView(){
        return R.id.activity_login;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RegistrationService registrationService;
        EditText editTextName;
        EditText editTextPassword;

        TextView tv_have_account = findViewById(R.id.tv_have_account);
        editTextName=findViewById(R.id.et_name);
        editTextPassword=findViewById(R.id.et_password);
        AppCompatButton appCompatButton = findViewById(R.id.btn_register);
        List<TextView> editTexts=new ArrayList<>();
        editTexts.add(editTextName);
        editTexts.add(editTextPassword);
        UIController uiController=new UIController(this,editTexts);
        ErrorController errorController=new ErrorController();
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,errorController);
        registrationService=new RegistrationService();
        tv_have_account = findViewById(R.id.tv_have_account);
        tv_have_account.setOnClickListener(v-> {
            callBack.onSuccess(MainActivity.class);
        });
        appCompatButton.setOnClickListener(v->registrationService.registration(editTextName.getText().toString(),editTextPassword.getText().toString(),callBack));

    }
}