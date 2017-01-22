package com.dmond.android.mapStuff;

import android.content.Context;
import android.location.Location;

/**
 * Created by hosein on 1/21/17.
 */

public interface mapIntractor {

interface onLoadMapFinishedListener{
    void mapLoadSuccess();
    void mapLoadError();
    void getCourrentLocation(Location location);
}
    void loadMap(onLoadMapFinishedListener listener, Context context);
    void onres(Context context);
    void onstart(Context context);
    void oncreate(Context context);

}
