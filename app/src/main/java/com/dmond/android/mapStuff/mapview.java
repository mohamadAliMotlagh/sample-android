package com.dmond.android.mapStuff;

import android.location.Location;

/**
 * Created by hosein on 1/21/17.
 */

public interface MapView {
    void mapReady();
    void updateLocation(Location location);

}
