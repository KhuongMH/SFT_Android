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

import org.apache.commons.lang.StringEscapeUtils;

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
    List<LatLng> total = new ArrayList<>();

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        0);
            }

        } else {
            Log.d("KHUONGGGGG", "REGISTERED");
            GPSTracker gpsTracker = new GPSTracker(GrabActivity.this);
            LatLng location;
            if (gpsTracker.getLocation() != null) {
                location = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            } else {
                location = new LatLng(10.786362, 106.691618);
            }
//            new GetAndDrawRouteWithTwoLocation().execute(location);
            String tmp = "kp|`Akd_jSIfCKjEKtCErAE`AEjAGbAKjC_@vH[`J?^OxDMbDKtCGz@Ad@KpCQdCKlCGfBCt@M|DElAEx@CtAGfAK|BQlGI|B?NGvAEpAAR";
            List<LatLng> list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "on|`Acg_jSABA@A@ABs@jA";
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "u{|`Agv_jSh@ZlD|C|AbB^d@JRPXTd@F`@Rh@";
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "}r|`Awe`jS?RM^cAjBi@jA_@r@kAbCo@jA";
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "ol|`Akp`jSGNQd@e@fAc@z@w@lAe@v@IRA?";
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "srz`A{mcjSSXWVWXQREDCBGDa@h@ONMNMNg@l@g@l@g@dAaBhDqA|BmB~DcArBg@~@aFhKaAlB_AfBCHEFQ^{D|HQ\\\\w@xAi@jAYj@kG`My@bB_@v@w@dBWb@";
            tmp = StringEscapeUtils.unescapeJava(tmp);
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "{oy`AuxdjSAFAB?B?D?BBBBFZ\\\\LN@B@B?B?FALiF|Fe@f@_ElEmBtBiBxBcClCgAlAc@f@}BbCoAtAa@`@";
            tmp = StringEscapeUtils.unescapeJava(tmp);
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "wyy`AmbejSrApAVZhBjBFD|AvA";
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }

            tmp = "kvy`AcfejSaAhAi@j@";
            list = decodePoly(tmp);
            for (LatLng l : list) {
                total.add(l);
            }
            for (int i = 1; i < total.size(); i++) {
                gm.addPolyline((new PolylineOptions())
                        .add(total.get(i-1), total.get(i)).width(6).color(Color.BLUE)
                        .visible(true));
            }
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

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    public class GetAndDrawRouteWithTwoLocation extends AsyncTask<LatLng, Void, String> {

        @Override
        protected String doInBackground(LatLng... params) {
            LatLng current = params[0];
            LatLng des = new LatLng(10.802466, 106.640635);
            String urlRoute = "https://maps.googleapis.com/maps/api/directions/json?origin="
                    + current.latitude + "," + current.longitude + "&destination="
                    + des.latitude + "," + des.longitude + "&units=metric&mode=walking";
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
                Log.d("KHUONGGGG", response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
