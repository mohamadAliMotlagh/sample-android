package com.dmond.android.registration;

import android.app.Activity;

/**
 * Created by hosein on 1/21/17.
 */

public interface registerPresenter {
    void validatePhoneNumber(Activity activity,String phoneNumber);
    void onDestroy();
}
