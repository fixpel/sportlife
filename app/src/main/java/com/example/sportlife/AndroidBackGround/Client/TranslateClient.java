package com.example.sportlife.AndroidBackGround.Client;

import android.content.Context;

import com.example.sportlife.R;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class  TranslateClient {
    public static List<String> translateMuscles(
            Context context,
            String prefix,
            List<String> ids
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
    public static List<String> translateInventories(List<String> inventories,String type,String Language) throws IOException {
        Map<String,String> dictionary=SessionContext.getDictionary(type+"/"+type+"_"+Language+".json");
        return inventories.stream().map(dictionary::get).collect(Collectors.toList());
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
    public static String unTranslateLevel(Context context,String string){
        String level=null;
        if(context.getString(R.string.level_novice).equals(string)){
            level="новичок";
        }else if(context.getString(R.string.level_experienced).equals(string)){
            level="опытный";
        }else if(context.getString(R.string.level_pro).equals(string)){
            level="профи";
        }
        return level;
    }
    public static String translateString(String string, String type, String Language) throws IOException {
        Map<String ,String> dictionary=SessionContext.getDictionary(type+"/"+type+"_"+Language+".json");
        return dictionary.get(string);
    }
}
