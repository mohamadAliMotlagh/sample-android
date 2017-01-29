package com.dmond.android.mapStuff;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by hosein on 1/21/17.
 */

public class MapPresenterImpl implements MapPresenter,MapIntractor.onLoadMapFinishedListener{

    private GoogleApiClient mGoogleApiClient;
    private MapIntractor mapIntractor;
    private MapView mapview;
    private Context context;
  public MapPresenterImpl(MapView mapview, Context context){
      this.context=context;
      this.mapview=mapview;
      this.mapIntractor=new MapIntractorImpl();
  }

    @Override
    public void makeMap(GoogleMap googleMap) {

        GoogleMap map = googleMap;
        LatLng sydney = new LatLng(122.083, 38.4388);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

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
        map.moveCamera(CameraUpdateFactory.zoomTo(12));

    }

    @Override
    public void onStart() {
        mapIntractor.onstart(context);
    }

    @Override
    public void onResume() {
    mapIntractor.onres(context);
        mapIntractor.loadMap(MapPresenterImpl.this,context);


    }

    @Override
    public void oncreat() {
        mapIntractor.oncreate(context);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void mapLoadSuccess() {
mapview.mapReady();
    }

    @Override
    public void mapLoadError() {

    }

    @Override
    public void getCourrentLocation(Location location) {
mapview.updateLocation(location);
    }
}
