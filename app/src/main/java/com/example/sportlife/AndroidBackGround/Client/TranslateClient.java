package com.example.sportlife.AndroidBackGround.Client;

import android.content.Context;
import android.widget.Switch;

import com.example.sportlife.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class  TranslateClient {
    public static List<String> translateMuscles(
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
    public static List<String> unTranslateInventories(List<String> request,String Language ) throws IOException {
        List<String> response=new ArrayList<>();
        if(Language.equals("ru")){
            return request;
        }else{
            Map<String,String> dictionaryReq=SessionContext.getDictionary("Inventory_"+Language+".json");
            List<String> ids=request.stream().map(dictionaryReq::get).collect(Collectors.toList());
            Map<String,String> dictionary=SessionContext.getDictionary("Inventory_ru.json");
            response=ids.stream().map(dictionary::get).collect(Collectors.toList());
        }
        return response;
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
    public static String translateString(String inventory, String type, String Language) throws IOException {
        if(Language.equals("ru")){
            return inventory;
        }
        Map<String ,String> dictionary=SessionContext.getDictionary(type+"_"+Language+".json");
        return dictionary.get(inventory);
    }
    public static List<String> translateInventories(List<String> inventories,String type,String Language) throws IOException {
        if(Language.equals("ru")){
            return inventories;
        }
        Map<String,String> dictionary=SessionContext.getDictionary(type+"_"+Language+".json");
        return inventories.stream().map(dictionary::get).collect(Collectors.toList());
    }
}
