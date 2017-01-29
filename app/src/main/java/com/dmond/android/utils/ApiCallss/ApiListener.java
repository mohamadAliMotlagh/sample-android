package com.dmond.android.utils.ApiCallss;

import com.google.gson.JsonObject;

/**
 * Created by hosein on 1/22/17.
 */

public interface ApiListener {
    void onSuccess(String methodName, JsonObject data);
    void onFailure(String methodName, String message);
}
