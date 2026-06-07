package com.example.sportlife.AndroidBackGround.Client;

import android.content.Context;

import com.example.sportlife.R;

import java.util.List;
import java.util.stream.Collectors;

public final class  TranslateClient {
    public static List<String> translateMuscle(
            Context context,
            String prefix,
            List<Long> ids
    ) {
        return ids.stream()
                .map(id -> {
                    int resId = context.getResources().getIdentifier(
                            prefix + "_" + id,
                            "string",
                            context.getPackageName()
                    );

                    return context.getString(resId);
                })
                .collect(Collectors.toList());
    }
    public static String translateLevel(Context context,String string){
        String level="";
        switch (string){
            case "новичок":
                level= context.getString(R.string.level_novice);
                break;
            case "опытный":
                level= context.getString(R.string.level_experienced);
                break;
            case "профи":
                level= context.getString(R.string.level_pro);
                break;
        }
        return level;
    }
}
