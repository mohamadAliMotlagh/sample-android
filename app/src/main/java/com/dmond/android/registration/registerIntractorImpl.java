package com.dmond.android.registration;
import android.app.Activity;
import android.util.Log;

import com.dmond.android.utils.ApiCallss.ApiInterface;
import com.dmond.android.utils.ApiCallss.ApiListener;
import com.dmond.android.utils.ApiCallss.ApiResponses;
import com.dmond.android.utils.ApiCallss.publicServiceGenerator;
import com.dmond.android.utils.myshared;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Created by hosein on 1/21/17.
 */

public class RegisterIntractorImpl implements RegisterIntractor,ApiListener {
    @Override
    public void register(final Activity activity, String phoneNumber, onRegsiterFinishedListener listener) {

         myshared ms = new myshared(activity);

        //my retrofit
//        new ApiResponses()
//                .setCall("UserMe", publicServiceGenerator.createService(ApiInterface.class,activity).drivers(35.724183,51.387901),RegisterIntractorImpl.this);


    }


    @Override
    public void onSuccess(String methodName, JsonObject data) {
        Log.d("UserMe","sssss");
        if (methodName.equals("UserMe")){
            Gson gson=new Gson();
            Log.d("UserMe",gson.toJson(data));
        }

    }

    @Override
    public void onFailure(String methodName, String message) {
        Log.d("UserMe",message);
    }
}


