package com.example.sportlife.AndroidBackGround.Security;

import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public  class SecurityContext {
    @Getter
    @Setter
    private static String tokenAccess;
    @Getter
    @Setter
    private static String tokenRefresh;
    @Getter
    @Setter
    private static String name;
    private static SecurityContext context;
    public static SecurityContext createContext() {
        if(context==null){
            context=new SecurityContext();
        }
        return context;
    }
}
