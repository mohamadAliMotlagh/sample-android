package com.dmond.android.utils;

/**
 * Created by hosein on 6/9/16.
 */

import android.content.Context;
import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class myshared {
    private SharedPreferences UserInfo;

    private Context context;




    public myshared(Context con) {
        context=con;
        UserInfo=con.getSharedPreferences(
                "Clogin", 0);
    }





    public void setCookie(String Cookie) {
        SharedPreferences.Editor edit = UserInfo.edit();
        edit.putString("Cookie", Cookie);
        edit.commit();
    }
    public String getCookie(){
        return UserInfo.getString("Cookie","");
    }

    public void setXsrf(String Xsrf) {
        SharedPreferences.Editor edit = UserInfo.edit();
        edit.putString("Xsrf", Xsrf);
        edit.commit();
    }
    public String getXsrf(){
        return UserInfo.getString("Xsrf","");
    }











}
