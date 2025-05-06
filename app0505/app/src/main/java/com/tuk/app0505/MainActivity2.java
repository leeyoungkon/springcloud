package com.tuk.app0505;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    static final LatLng SEOUL = new LatLng(37.5665, 126.9780);
    static final LatLng INCHEON = new LatLng(37.4000, 126.6100);
    private GoogleMap gMap;
    MapFragment mapFragment;
    Marker seoul, incheon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//     map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        gMap = map;
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, MODE_PRIVATE);

        gMap.setMyLocationEnabled(true);
        gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240), 15));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setOnMapClickListener(mapListener);
        gMap.setOnMyLocationChangeListener(myListener);
        seoul = gMap.addMarker(new MarkerOptions().position(SEOUL)
                .title("서울"));
        incheon = gMap.addMarker(new MarkerOptions()
                .position(INCHEON)
                .title("인천")
                .snippet("인천은 항구")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.map_icon)));

        // Move the camera instantly to hamburg with a zoom of 15.
        //gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));

        // Zoom in, animating the camera.
        gMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng myPosition = new LatLng(latitude, longitude);
            gMap.addMarker(new MarkerOptions().position(myPosition).title("요기"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    GoogleMap.OnMarkerClickListener listener = new GoogleMap.OnMarkerClickListener() {

        @Override
        public boolean onMarkerClick(Marker marker) {
            // TODO Auto-generated method stub
            if (marker.equals(seoul))
                Toast.makeText(MainActivity2.this, "여기는 서울입니다", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity2.this, "여기는 인천입니다", Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    GoogleMap.OnMapClickListener mapListener = new GoogleMap.OnMapClickListener() {

        @Override
        public void onMapClick(LatLng point) {
            Toast.makeText(MainActivity2.this, point.latitude+":"+point.longitude, Toast.LENGTH_SHORT).show();

        }
    };

    GoogleMap.OnMyLocationChangeListener myListener = new GoogleMap.OnMyLocationChangeListener() {

        @Override
        public void onMyLocationChange(Location location) {
            Toast.makeText(MainActivity2.this, location.getLatitude()+":"+location.getLongitude(), Toast.LENGTH_SHORT).show();

        }
    };


}
