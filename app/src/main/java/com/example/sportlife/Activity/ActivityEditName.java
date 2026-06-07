package com.example.sportlife.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ProfileService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityEditName extends ActivityCreate {
    @Override
    protected int getIdLayout() {
        return R.layout.edit_dialog;
    }

    @Override
    protected int getIdView() {
        return R.id.editDialog;
    }
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View view = getLayoutInflater().inflate(R.layout.edit_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            );
        }
        dialog.show();
        Button no = dialog.findViewById(R.id.btnNo);
        Button yes = dialog.findViewById(R.id.btnYes);
        EditText name = dialog.findViewById(R.id.tvName);
        TextView errorName = dialog.findViewById(R.id.errorName);
        name.requestFocus();
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        );
        no.setOnClickListener(v -> dialog.dismiss());
        yes.setOnClickListener(v -> {
            String newName = name.getText().toString().trim();
            if (!newName.isEmpty()) {
                dialog.dismiss();
            } else {
                errorName.setText("Введите имя");
                errorName.setVisibility(View.VISIBLE);
            }
        });
    }
}
