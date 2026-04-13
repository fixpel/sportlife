package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.EditText;

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
import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.AuthServiceImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.CallBackHandlerImpl;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;

@RequiredArgsConstructor
public class MainActivity extends AppCompatActivity {
    AppCompatButton appCompatButton;
    EditText editTextName;
    EditText editTextPassword;
    ApiRepository apiRepository;
    ErrorController errorController;
    AuthServiceImpl authServiceImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        appCompatButton = findViewById(R.id.btn_register);
        editTextName = findViewById(R.id.et_name);
        editTextPassword = findViewById(R.id.et_password);
        List<EditText> editTexts=new ArrayList<>();
        editTexts.add(editTextPassword);
        editTexts.add(editTextName);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         apiRepository =
                RetrofitClient.getApiRepository();
        errorController=new ErrorController();
        authServiceImpl=new AuthServiceImpl(apiRepository,errorController);
        UIController uiController=new UIController(this,editTexts);
        appCompatButton.setOnClickListener(v -> authServiceImpl.auth(editTextName.getText().toString(),editTextPassword.getText().toString(), new CallBackHandlerImpl(uiController)));
    }
}