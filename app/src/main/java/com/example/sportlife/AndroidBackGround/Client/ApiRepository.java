package com.example.sportlife.AndroidBackGround.Client;

import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.ExpertsRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.FavouritesRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.RefreshRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.RegistrationRequest;
import com.example.sportlife.AndroidBackGround.Dto.Request.SearchRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FavouritesResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindInventoryResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.RefreshResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.RegistrationResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SearchResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.SplashResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.UpdateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRepository {
    @POST("Employee/auth")
    Call<AuthResponse> auth(@Body AuthRequest request);
    @POST("Employee/create")
    Call<RegistrationResponse> registration(@Body RegistrationRequest request);
    @POST("Employee/refresh")
    Call<RefreshResponse> refresh(@Body RefreshRequest request);
    @GET("Employee/top")
    Call<FindTopResponse> findTop();
    @POST("Employee/experts")
    Call<UpdateResponse> updateExperts(@Body ExpertsRequest request);

    @POST("Exercise/search")
    Call<SearchResponse> search(@Body SearchRequest request, @Query("size") int size,@Query("page") int page );

    @GET("Infentory/info")
    Call<FindInventoryResponse> findInventory(@Query("size") int size,@Query("page") int page);

    @POST("Favourites/create")
    Call<FavouritesResponse> crateFavourites(@Body FavouritesRequest request);
    @POST("Favourites/delete")
    Call<FavouritesResponse> deleteFavourites(@Body FavouritesRequest request);
    @GET("Employee/splash")
    Call<SplashResponse> splash();
}
