package com.dmond.android.mapStuff;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dmond.android.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by hosein on 1/21/17.
 */

public class MapActivity extends AppCompatActivity implements MapView,OnMapReadyCallback {



    private MapPresenter mappresenter;
    private Location location;


    @Override
    protected void onStart() {
        super.onStart();
        mappresenter.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
        mappresenter.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappresenter=new MapPresenterImpl(this,MapActivity.this);
        mappresenter.oncreat();
    }


    @Override
    public void mapReady() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    @Override
    public void updateLocation(Location location) {

        Toast.makeText(getApplicationContext(),"ok_location_updated",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
       mappresenter.makeMap(googleMap);

    }
}
