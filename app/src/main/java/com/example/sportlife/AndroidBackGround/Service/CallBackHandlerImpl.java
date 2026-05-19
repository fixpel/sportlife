package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.Activity.ActivityExerciseDetail;
import com.example.sportlife.Activity.ActivityFavouriteDetails;
import com.example.sportlife.Activity.MainActivity;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ExerciseCardResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ProfileResponse;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.FavouritesService;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

@RequiredArgsConstructor
public class CallBackHandlerImpl implements CallBackHandler {
    private final UIController uiController;
    private final ErrorController errorController;

    @Override
    public void onSuccess(Class<? extends Activity> activity) {
        uiController.openNextScreen(activity);
    }

    @Override
    public void onError(Response<?> response) {
        ErrorResponse error=errorController.parseError(response,this);
        uiController.ErrorAdvice(error);
    }

    @Override
    public void onTools(String t) {
        uiController.errorService(t);
    }

    @Override
    public void findTop(FindTopResponse response) {
        uiController.findTop(response);
    }

    @Override
    public void findInventory(FindInventoryResponse response) {
        uiController.findInventory(response);
    }

    @Override
    public void findExercises(ExerciseCardResponse response) {
        uiController.findExercises(response,this, ActivityExerciseDetail.class);
    }

    @Override
    public void findExercise(ExerciseCardResponse.Exercise exercise) {
        uiController.findExercise(exercise,this);
    }

    @Override
    public void onUnAuth() {
        uiController.openNextScreen(MainActivity.class);
    }

    @Override
    public void onCreateFavourite(String name) {
        FavouritesService service=new FavouritesService();
        service.create(name,this);
    }

    @Override
    public void onDeleteFavourite(String name) {
        FavouritesService service=new FavouritesService();
        service.delete(name, this);
    }

    @Override
    public void findFavourites(ExerciseCardResponse response) {
        uiController.findExercises(response,this, ActivityFavouriteDetails.class);
    }

    @Override
    public void profile(ProfileResponse response) {
        uiController.profile(response);
    }
}
