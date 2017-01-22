package com.dmond.android;

import android.util.Log;

import com.dmond.android.registration.cUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hosein on 1/22/17.
 */

public class ApiResponses {
    public void setCall(final String methodName, Call<JsonObject> call, final ApiListener serverListener) {

        Log.i("==UrlServer", call.request().url().toString());

        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                // String stateResponse = new ErrorHandling().ErrorCode(response.body());

                    if (response.isSuccessful()) {
                        Gson gson=new Gson();
                        gson.toJson(response.body());
                        Log.i("==GetServer", gson.toJson(response.body()));
                        serverListener.onSuccess(methodName, response.body());
                    } else {

                        Log.i("==CodeServer", String.valueOf(response.code()));
                        Log.i("==MessageServer", String.valueOf(response.errorBody()));
                        serverListener.onFailure(methodName, String.valueOf(response.errorBody()));
                    }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                serverListener.onFailure(methodName, "errorr");
            }

        });
    }


}
