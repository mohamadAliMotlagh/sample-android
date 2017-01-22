package com.dmond.android.registration;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;

import com.dmond.android.myStatics;
import com.dmond.android.utils.myshared;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hosein on 1/22/17.
 */

public class publicServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {

        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, Context cont) {
      final myshared ms=new myshared(cont);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(myStatics.baseUri);



            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Cookie", ms.getCookie())
                            .header("X-XSRF-TOKEN", ms.getXsrf())
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });


        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(serviceClass);
    }
}
