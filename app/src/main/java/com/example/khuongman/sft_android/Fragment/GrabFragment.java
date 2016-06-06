package com.example.khuongman.sft_android.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khuongman.sft_android.Activity.MainActivity;
import com.example.khuongman.sft_android.Classes.GPSTracker;
import com.example.khuongman.sft_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Man Huynh Khuong on 28/05/16.
 */
public class GrabFragment extends Fragment {

    GoogleMap map;
    View view;
    Activity activity;

    public GrabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_grab, container, false);
        activity = getActivity();
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            GPSTracker gps = new GPSTracker(activity);
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setZoomGesturesEnabled(true);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(gps.getLatitude(), gps.getLongitude()), 13));
//            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(gps.getLatitude(), gps.getLongitude())).title("");
//            map.addMarker(markerOptions);
        }



        return view;
    }

    public void requestLocationPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
    }
}
