package com.dmond.android.registration;

import android.app.Activity;

/**
 * Created by hosein on 1/21/17.
 */

public class registerPresenterImpl implements registerPresenter,registerIntractor.onRegsiterFinishedListener {
   private registerView registerview;
   private registerIntractor registerintractor;

    public registerPresenterImpl(registerView registerview){
        this.registerview=registerview;
        this.registerintractor=new registerIntractorImpl();

    }


    @Override
    public void validatePhoneNumber(Activity activity,String phoneNumber) {
        if(registerview!=null){
            registerview.showProgress();
        }

             registerintractor.register(activity,phoneNumber,this);

    }

    @Override
    public void onDestroy() {
        registerview=null;
    }

    @Override
    public void phoneNumberError() {

    if (registerview!=null) {
        registerview.setPhoneNumberError();
        registerview.hideProgress();
    }
    }

    @Override
    public void onSuccess() {
        if (registerview!=null) {
            registerview.hideProgress();
            registerview.navigateToHome();
        }
    }
}
