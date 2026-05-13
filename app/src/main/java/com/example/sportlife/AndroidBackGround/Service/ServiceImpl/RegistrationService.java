package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.Activity.ActivityHome;
import com.example.sportlife.Activity.ActivityLogin;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Client.SessionContext;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Request.RegistrationRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.RegistrationResponse;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;


@RequiredArgsConstructor
public class RegistrationService {
    public void registration(String name, String password, CallBackHandler callBack){
        SessionManager session=new SessionManager(SessionContext.getAppContext());
        RegistrationRequest request=new RegistrationRequest(name,password);
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        apiRepository.registration(request).enqueue(new retrofit2.Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String tokenAccess =response.body().getAccessToken();
                    String tokenRefresh=response.body().getRefreshToken();
                    SecurityContext.createContext();
                    SecurityContext.setName(name);
                    session.saveToken(tokenAccess,tokenRefresh);
                    callBack.onSuccess(ActivityHome.class);
                }else{
                    callBack.onError(response);
                }
            }
            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                callBack.onTools(t.getMessage());
            }
        });
    ;}
}
