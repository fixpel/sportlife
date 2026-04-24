package com.example.sportlife.AndroidBackGround.Security;

import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import lombok.Data;

@Data
public  class SecurityContext {
    private static String tokenAccess;
    private static String tokenRefresh;
    private static SecurityContext context;
    public static SecurityContext createContext() {
        if(context==null){
            context=new SecurityContext();
        }
        return context;
    }

    public void setTokenAccess(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }

    public String getTokenAccess() {
        return tokenAccess;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }
}
