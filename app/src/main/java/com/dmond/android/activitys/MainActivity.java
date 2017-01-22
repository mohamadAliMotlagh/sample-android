package com.dmond.android.activitys;

import android.Manifest;

import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.location.Location;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dmond.android.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private boolean mRequestingLocationUpdates = false;
    private double currentLatitude;
    private double currentLongitude;
    private GoogleMap map;
    private long lat, lng;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 1; // 10 meters
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkPlayServices()) {


            buildGoogleApiClient();

            createLocationRequest();


        }

    }


    private void togglePeriodicLocationUpdates() {
        if (!mRequestingLocationUpdates) {

            mRequestingLocationUpdates = true;

            // Starting the location updates
            startLocationUpdates();


        } else {
            // Changing the button text


            mRequestingLocationUpdates = false;

            // Stopping the location updates
            stopLocationUpdates();

            Log.d("sssssss", "Periodic location updates stopped!");
        }
    }

    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, (com.google.android.gms.location.LocationListener) MainActivity.this);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
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

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();

        // Resuming the periodic location updates
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }


    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        mLastLocation = location;

        Toast.makeText(getApplicationContext(), "Location changed!",
                Toast.LENGTH_SHORT).show();

        // Displaying the new location on UI
        displayLocation();
        int x = 0;
        updateMap(currentLatitude, currentLongitude, x);
        Toast.makeText(this, "Latitude: " + currentLatitude + ", Longitude: " + currentLongitude, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);

        displayLocation();

        if (mRequestingLocationUpdates) {

            startLocationUpdates();

        }
        togglePeriodicLocationUpdates();


    }

    private Location mLastLocation;

    private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            Toast.makeText(getApplicationContext(), latitude + "####" + longitude, Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }


    public void updateMap(double currentLatitude, double currentLongitude, int x) {
        LatLng sydney = new LatLng(currentLatitude, currentLongitude);


        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(currentLatitude, currentLongitude)).zoom(12).build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //  Marker marker = map.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Title")
//                .snippet("Snippet"));
//
//
//        marker.showInfoWindow();
//        map.addMarker(new MarkerOptions().position(sydney).title("hosein")).showInfoWindow();
////        map.addMarker(marker);
////        MapStyleManager styleManager = MapStyleManager.attachToMap(this, map);
////        styleManager.addStyle(0, R.raw.map_style_silver_sparse);
////        styleManager.addStyle(10, R.raw.map_style_silver_sparse);
////        styleManager.addStyle(12, R.raw.map_style_silver_sparse);
//        CameraUpdate camereUpdate = CameraUpdateFactory.newLatLng(sydney);
//        map.moveCamera(camereUpdate);
//        map.moveCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(currentLatitude, currentLongitude);

        //  Marker marker = map.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Title")
//                .snippet("Snippet"));
//
//
//        marker.showInfoWindow();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions().position(sydney).title("hosein")).showInfoWindow();
//        map.addMarker(marker);
//        MapStyleManager styleManager = MapStyleManager.attachToMap(this, map);
//        styleManager.addStyle(0, R.raw.map_style_silver_sparse);
//        styleManager.addStyle(10, R.raw.map_style_silver_sparse);
//        styleManager.addStyle(12, R.raw.map_style_silver_sparse);
        CameraUpdate camereUpdate = CameraUpdateFactory.newLatLng(sydney);
        map.moveCamera(camereUpdate);
        map.moveCamera(CameraUpdateFactory.zoomTo(17));
        // Add a marker in Sydney and move the camera

    }
}
