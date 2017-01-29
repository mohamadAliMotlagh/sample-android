package com.dmond.android.registration;

import android.app.Activity;
import android.content.Context;

/**
 * Created by hosein on 1/21/17.
 */

public interface RegisterIntractor {

    interface onRegsiterFinishedListener
    {
        void phoneNumberError();
        void onSuccess();
    }

    void register(Activity activity, String phoneNumber, onRegsiterFinishedListener listener);

}
