package com.example.khuongman.sft_android.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Classes.GPSTracker;
import com.example.khuongman.sft_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by khuong.man on 6/7/2016.
 */
public class GrabActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    GoogleMap gm;
    public int flag = 0;

    String API_GOOGLE_GET_ROUTE = "https://maps.googleapis.com/maps/api/directions/json?origin=???&destination=???&units=metric&mode=walking";
    String API_GOOGLE_GET_DISTANCE = "https://maps.googleapis.com/maps/api/distancematrix/json?origin=???&destination=???";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab);
        gm = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        requestLocationPermission();
    }

    public void requestLocationPermission() {

        Log.d("KHUONGG", "SSSSSSSSSSS");
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        0);
            }

        } else {
            Log.d("KHUONGGGGG", "REGISTERED");
            GPSTracker gpsTracker = new GPSTracker(GrabActivity.this);
            LatLng location = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            gm.addMarker(new MarkerOptions().position(location).title("Hello world"));
            gm.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("Khuongggg", "GGGGGG");
        if (requestCode == 0) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //OK
                GPSTracker gpsTracker = new GPSTracker(GrabActivity.this);
                gm.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()), 15));
            } else {
                //NOT OK
                finish();
            }
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
