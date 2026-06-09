package com.example.sportlife.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ScheduleService;
import com.example.sportlife.R;
import com.google.android.material.button.MaterialButton;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class ActivityDate extends ActivityCreate {
    ScheduleService service=new ScheduleService();
    @Override
    protected int getIdLayout() {
        return R.layout.activity_date;
    }

    @Override
    protected int getIdView() {
        return R.id.activitySchedule;
    }
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        List<TextView> textViews=new ArrayList<>();
        textViews.add(findViewById(R.id.errorSchedule));
        UIController uiController=new UIController(this,textViews);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController,new ErrorController());
        service.findSchedule(callBack);
        findViewById(R.id.btnBack).setOnClickListener(v->callBack.onSuccess(ActivityProfile.class));
        MaterialButton monday=findViewById(R.id.btnAddMonday);
        MaterialButton tuesday=findViewById(R.id.btnAddTuesday);
        MaterialButton wednesday=findViewById(R.id.btnAddWednesday);
        MaterialButton thursday=findViewById(R.id.btnAddThursday);
        MaterialButton friday=findViewById(R.id.btnAddFriday);
        MaterialButton saturday=findViewById(R.id.btnAddSaturday);
        MaterialButton sunday=findViewById(R.id.btnAddSunDay);
        monday.setOnClickListener(v->{onDayClick("MONDAY",callBack);});
        tuesday.setOnClickListener(v->{onDayClick("TUESDAY",callBack);});
        wednesday.setOnClickListener(v->{onDayClick("WEDNESDAY",callBack);});
        thursday.setOnClickListener(v->{onDayClick("THURSDAY",callBack);});
        friday.setOnClickListener(v->{onDayClick("FRIDAY",callBack);});
        saturday.setOnClickListener(v->{onDayClick("SATURDAY",callBack);});
        sunday.setOnClickListener(v->{onDayClick("SUNDAY",callBack);});
    }
    public void onDayClick(String name,CallBackHandler callBack){
        AlertDialog dialog=new AlertDialog.Builder(this).setView(R.layout.edit_date).create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            );
        }
        dialog.show();
        TextView error=dialog.findViewById(R.id.errorSchedule);
        List<TextView> textViews=new ArrayList<>();
        textViews.add(error);
        UIController uiControllerD=new UIController(this,textViews);
        CallBackHandler callBackD= new CallBackHandlerImpl(uiControllerD,new ErrorController());
        TimePicker timePicker = dialog.findViewById(R.id.numberPicker);
        timePicker.setIs24HourView(true);
        Button save=dialog.findViewById(R.id.btnSave);
        save.setOnClickListener(v->{
            TimePicker picker=dialog.findViewById(R.id.numberPicker);
            int minute=picker.getMinute();
            int hour=picker.getHour();
            LocalTime time1 = LocalTime.of(hour,minute);
            String time=time1.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            service.createSchedule(callBackD,name,time);
            callBack.onSuccess(ActivityDate.class);
        });
    }
}
