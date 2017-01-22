package com.dmond.android;


import com.dmond.android.registration.cUser;
import com.dmond.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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




}
