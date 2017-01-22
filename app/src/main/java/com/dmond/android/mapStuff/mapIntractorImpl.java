package com.dmond.android.mapStuff;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by hosein on 1/21/17.
 */

public class mapIntractorImpl implements mapIntractor, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private boolean mRequestingLocationUpdates = false;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private onLoadMapFinishedListener listener;
    private static int UPDATE_INTERVAL = 10000; // 10 sec

  private   Context context;

    protected void startLocationUpdates(Context context) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }


    @Override
    public void loadMap(onLoadMapFinishedListener listener, Context context) {
        this.listener = listener;
        this.context = context;



    }

    @Override
    public void onres(Context context) {
        // Resuming the periodic location updates
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates(context);
        }
    }

    @Override
    public void onstart(Context context) {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void oncreate(Context context) {
        buildGoogleApiClient(context);

        createLocationRequest();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        listener.mapLoadSuccess();
      LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);


        if (mRequestingLocationUpdates) {

            startLocationUpdates(context);

        }
        togglePeriodicLocationUpdates(context);
        Log.d("ssssss", "onconected");
//        mLastLocation = LocationServices.FusedLocationApi
//                .getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.d("ssssss", "onConnectionSuspended");
        listener.mapLoadError();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        listener.mapLoadError();
        Log.d("ssssss", "onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location)

    {


        // Displaying the new location on UI
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);
        int x = 0;
       // updateMap(currentLatitude, currentLongitude, x);
       // Toast.makeText(this, "Latitude: " + currentLatitude + ", Longitude: " + currentLongitude, Toast.LENGTH_LONG).show();

        Log.d("ssssss","onLocationChanged");
        listener.getCourrentLocation(mLastLocation);
    }


    private void togglePeriodicLocationUpdates(Context context) {
        if (!mRequestingLocationUpdates) {

            mRequestingLocationUpdates = true;

            // Starting the location updates
            startLocationUpdates(context);


        } else {
            // Changing the button text


            mRequestingLocationUpdates = false;

            // Stopping the location updates
            stopLocationUpdates(context);

            Log.d("sssssss", "Periodic location updates stopped!");
        }
    }

    protected void startLocationUpdatess(Context context) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates(Context context) {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, (com.google.android.gms.location.LocationListener) context);
    }

    protected synchronized void buildGoogleApiClient(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    protected void createLocationRequest() {
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL);        // 10 seconds, in milliseconds
        // 1 second, in milliseconds

//        mLocationRequest = new LocationRequest();
//        mLocationRequest=LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(UPDATE_INTERVAL);
//        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
//        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);

    }

//    private boolean checkPlayServices() {
//        int resultCode = GooglePlayServicesUtil
//                .isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
//                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
//            } else {
//                Toast.makeText(getApplicationContext(),
//                        "This device is not supported.", Toast.LENGTH_LONG)
//                        .show();
//                finish();
//            }
//            return false;
//        }
//        return true;
//    }


}
