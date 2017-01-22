package com.dmond.android;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by hosein on 1/21/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }


    public static void cToastShort(Context c,String s){
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }
    public static void cToastLong(Context c,String s){
        Toast.makeText(c,s,Toast.LENGTH_LONG).show();
    }
}
