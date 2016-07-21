package com.example.khuongman.sft_android.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.khuongman.sft_android.Classes.GPSTracker;
import com.example.khuongman.sft_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        drawOnMap();
    }

    public void drawOnMap() {
        if (checkPermissionLocation()) {
            getLocationAndDrawOnMap();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
    }

    public boolean checkPermissionLocation() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }


    public void getLocationAndDrawOnMap() {
        GPSTracker gpsTracker = new GPSTracker(GrabActivity.this);
        LatLng location;
        if (gpsTracker.getLocation() != null) {
            location = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        } else {
            location = new LatLng(10.786362, 106.691618);
        }
        LatLng[] list = new LatLng[2];
        list[0] = location;
        list[1] = new LatLng(10.800329, 106.682823);
        new GetAndDrawRouteWithTwoLocation().execute(list);
        gm.addMarker(new MarkerOptions().position(location).title("Hello world"));
        gm.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()), 15));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length >= 1) {
                switch (permissions[0]) {
                    case Manifest.permission.ACCESS_FINE_LOCATION: {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            getLocationAndDrawOnMap();
                        } else {
                            finish();
                        }
                        break;
                    }
                }
            }
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class GetAndDrawRouteWithTwoLocation extends AsyncTask<LatLng, Void, List<LatLng>> {

        @Override
        protected List<LatLng> doInBackground(LatLng... params) {
            LatLng current = params[0];
            LatLng des = params[1];
            List<LatLng> total = new ArrayList<>();
            List<LatLng> list;
            LatLng anoDes = new LatLng(10.801111, 106.683608);
            String urlRoute = "https://maps.googleapis.com/maps/api/directions/json?origin="
                    + current.latitude + "," + current.longitude + "&destination="
                    + des.latitude + "," + des.longitude + "&waypoints=" + anoDes.latitude + "," + anoDes.longitude +"&mode=driving";
//            String urlRoute = "https://maps.googleapis.com/maps/api/directions/json?origin="
//                    + current.latitude + "," + current.longitude + "&destination="
//                    + des.latitude + "," + des.longitude + "&mode=driving";
            try {
                Log.d("KHUONGGGG", urlRoute);
                URL url = new URL(urlRoute);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(100000);
                urlConnection.setConnectTimeout(30000);
                InputStream inStream = urlConnection.getInputStream();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
                String temp, response = "";
                while ((temp = bReader.readLine()) != null) {
                    response += temp;
                }

                String tmp = new JSONObject(response).getJSONArray("routes").getJSONObject(0)
                        .getJSONObject("overview_polyline").getString("points");
                tmp = StringEscapeUtils.unescapeJava(tmp);
                Log.d("KHUONGGGGGG", tmp);
                list = PolyUtil.decode(tmp);

                Log.d("KHUONGGGGGG", "list = " + list.size());
                for (LatLng l : list) {
                    total.add(l);
                }
//                JSONArray j = new JSONObject(response).getJSONArray("routes").getJSONObject(0)
//                        .getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
//                for (int i = 0; i < j.length(); i++) {
//                    String tmp = j.getJSONObject(i).getJSONObject("polyline").getString("points");
//                    tmp = StringEscapeUtils.unescapeJava(tmp);
//                    Log.d("KHUONGGGGGG", tmp);
//                    list = PolyUtil.decode(tmp);
//                    for (LatLng l : list) {
//                        total.add(l);
//                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return total;
        }

        @Override
        protected void onPostExecute(List<LatLng> s) {
            Log.d("KHUONGGGGGG", "s = " + s.size());
            PolylineOptions polyLineOptions = new PolylineOptions();
            polyLineOptions.addAll(s);
            polyLineOptions.width(2);
            polyLineOptions.color(Color.BLUE);
            gm.addPolyline(polyLineOptions);
        }
    }
}
