package com.dmond.android.registration;
import android.app.Activity;
import android.util.Log;

import com.dmond.android.ApiInterface;
import com.dmond.android.ApiListener;
import com.dmond.android.ApiResponses;
import com.dmond.android.myStatics;
import com.dmond.android.utils.myshared;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by hosein on 1/21/17.
 */

public class registerIntractorImpl implements registerIntractor  {
    @Override
    public void register(final Activity activity, String phoneNumber, onRegsiterFinishedListener listener) {

        final myshared ms=new myshared(activity);






        ApiInterface wikinService = ServiceGenerator.createService(ApiInterface.class, myStatics.baseUri, "admin", "admin");
        Call<cUser> call = wikinService.postWithFormParams();
        call.enqueue(new Callback<cUser>() {
            @Override
            public void onResponse(Call<cUser> call, Response<cUser> response) {


                if (response.isSuccessful()) {
                    StringBuilder cookie=new StringBuilder();
                    String cxrf = null;


                    for (int i=0; i<response.headers().size();i++)
                    {
                        if ("Set-Cookie".equalsIgnoreCase(response.headers().name(i))){
                           //

                            if(cookie.length()>0)
                                cookie.append(";").append(response.headers().value(i));
                            else
                                cookie.append(response.headers().value(i));
                            if(response.headers().value(i).startsWith("XSRF-TOKEN")){

                                Log.d("tokens",String.valueOf(cookie)+"   ******  "+cxrf);
                                cxrf=(response.headers().value(i).substring(response.headers().value(i).indexOf("=")+1,response.headers().value(i).indexOf(";")));
                            }
                        }
                    }

                    ms.setCookie(String.valueOf(cookie));
                    ms.setXsrf(cxrf);

                    Log.d("tokens",String.valueOf(cookie)+"     "+cxrf);



//                    new ApiResponses()
//                            .setCall("signUp", publicServiceGenerator.createService(ApiInterface.class,activity).postWithFormParams(),registerIntractorImpl.this);
//                    //new Utils().exit(( context));
                }
                else{
                    Log.d("allresponsess"," "+response.code()  );
                }
            }

            @Override
            public void onFailure(Call<cUser> call, Throwable throwable) {
                Log.d("teesssssst",throwable.toString());
            }
        });

    }



}


