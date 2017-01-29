package com.dmond.android.utils.ApiCallss;


import com.dmond.android.registration.POJO.cUser;
import com.dmond.api.model.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hosein on 1/18/17.
 */

public interface ApiInterface {


    @Headers({"Content-Type: application/json",})
    @POST("transport/public/registrations")
    Call<Void> Register(@Body User user);


    //loginProcess
    @GET("user")
    @Headers({"Content-Type: application/json"})
    Call<cUser> postWithFormParams();


    //userMe
    @GET("transport/user/me")
    @Headers({"Content-Type: application/json"})
    Call<JsonObject> UserMe();



    //drivers
    @GET("transport/drivers")
    @Headers({"Content-Type: application/json"})
    Call<JsonObject> drivers(
          @Query("latitud") double latitud,
          @Query("longitud") double longitud);


}
