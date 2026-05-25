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
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FindTopService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.UpdateExpertsService;
import com.example.sportlife.R;
import org.jspecify.annotations.NonNull;
import java.util.ArrayList;
import java.util.List;

public class ActivityLevel extends CreateActivity {
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
        TextView expert = findViewById(R.id.expert);

        List<TextView> textViews = new ArrayList<>();
        textViews.add(expert);

        UIController uiController = new UIController(this, textViews);
        ErrorController errorController = new ErrorController();
        CallBackHandler callBack = new CallBackHandlerImpl(uiController, errorController);
        UpdateExpertsService service = new UpdateExpertsService();


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
            service.updateExperts(experts, callBack);
        });
    }
}