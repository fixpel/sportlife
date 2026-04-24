package com.example.sportlife.AndroidBackGround.Controller;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public  class UIController {
    private final Activity activity;
    private final List<EditText> editTexts;
    public void openNextScreen(Class<? extends Activity> to){
        Intent intent=new Intent(activity, to);
        activity.startActivity(intent);
        activity.finish();
    }
    public void ErrorAdvice(ErrorResponse error){
        editTexts.forEach(e->e.setError(null));
        editTexts.forEach(e->e.setError(error.getErrors().get(e.getTag().toString()).toString()));
    }
    public void ErrorService(String message){
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
    }
    public void findTop(FindTopResponse response){//выводит объекты на экран, активности пока нет.

    }
}
