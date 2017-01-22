package com.dmond.android.mapStuff;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by hosein on 1/21/17.
 */

public interface mapPresenter {
    void makeMap(GoogleMap googleMap);
    void onDestroy();
    void onPause();
    void onStart();
    void onResume();
    void oncreat();

}
