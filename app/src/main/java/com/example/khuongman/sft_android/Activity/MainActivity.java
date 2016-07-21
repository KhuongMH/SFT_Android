package com.example.khuongman.sft_android.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.khuongman.sft_android.Adapter.NavigationAdapter;
import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Classes.Food;
import com.example.khuongman.sft_android.Classes.FoodCategory;
import com.example.khuongman.sft_android.Classes.GPSTracker;
import com.example.khuongman.sft_android.Classes.LayoutIDWithTitle;
import com.example.khuongman.sft_android.Fragment.MainFragment;
import com.example.khuongman.sft_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import uk.co.ribot.easyadapter.EasyAdapter;

public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    ImageView iv_navigation;
    ListView lv_navigation;
    public static DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    EasyAdapter<LayoutIDWithTitle> navigationAdapter;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFoodData();
        iv_navigation = (ImageView) findViewById(R.id.iv_navigation);
        lv_navigation = (ListView) findViewById(R.id.lv_left_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Constant.FRAME_ID = R.id.fragment_skeleton;

        ft = getFragmentManager().beginTransaction();
        ft.replace(Constant.FRAME_ID, new MainFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        initNavigation();
    }

    public void loadFoodData() {
        if (checkPermissionWifiConnection()) {
            acceptedPermissionWifi();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    0);
        }
    }

    // return true when "Network State" Permission is existed
    public boolean checkPermissionWifiConnection() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_NETWORK_STATE);
    }

    public void acceptedPermissionWifi() {
        if (existWifiConnection()) {
            getFoodDataFromWebService();
        } else {
            getFoodDataFromLocal();
        }
    }

    public boolean existWifiConnection() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            }
        }
        return false;
    }

    public void getFoodDataFromWebService() {
        Log.d("FKKKKKKKKKKKKKKKKKK","DMMM");
    }

    public void getFoodDataFromLocal() {
        if (checkPermissionReadExternal()) {
            acceptedPermissionReadExternal();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }
    }

    // return true when "Read External" Permission is existed
    public boolean checkPermissionReadExternal() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public void acceptedPermissionReadExternal() {
        String result = "";
        try {
            InputStream is = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/food.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONArray array = new JSONArray(new String(buffer, "UTF-8"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                FoodCategory category = new FoodCategory();
                category.setTitle(jsonObject.getString("title"));
                category.setUrl(jsonObject.getString("url"));
                JSONArray jsonArray = jsonObject.getJSONArray("listFood");
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject obj = jsonArray.getJSONObject(j);
                    Food food = new Food();
                    food.setName(obj.getString("name"));
                    food.setImage(obj.getString("imageURL"));
                    food.setPrice(obj.getString("price"));
                    food.setDifPrice(obj.getInt("differencePrice"));
                    food.setIsNew(obj.getBoolean("isNew"));
                    category.getFoodList().add(food);
                }
                Constant.CATEGORIES.add(category);
            }
            Log.d("JJJJJJJJJJJJJ", "" + Constant.CATEGORIES.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length >= 1) {
                switch (permissions[0]) {
                    case Manifest.permission.ACCESS_NETWORK_STATE: {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            acceptedPermissionWifi();
                        } else {
                            getFoodDataFromLocal();
                        }
                        break;
                    }

                    case Manifest.permission.READ_EXTERNAL_STORAGE: {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            acceptedPermissionReadExternal();
                        } else {
                        }
                        break;
                    }
                }
            }
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, findViewById(R.id.ll_right_drawer));
    }

    private void initNavigation() {
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,
                R.drawable.ic_launcher, R.string.app_name, R.string.app_name);
        iv_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        List<LayoutIDWithTitle> list = new ArrayList<>();
        list.add(new LayoutIDWithTitle(Constant.KIEN_THUC_NONG_NGHIEP, "Kiến Thức Nông Nghiệp"));
        list.add(new LayoutIDWithTitle(Constant.GIA_CA_THI_TRUONG, "Giá Cả Thị Trường"));
        list.add(new LayoutIDWithTitle(Constant.BUON_BAN_NONG_SAN, "Buôn Bán Nông Sản"));
        list.add(new LayoutIDWithTitle(Constant.HOI_DAP_THAC_MAC, "Hỏi Đáp - Thắc Mắc"));
        list.add(new LayoutIDWithTitle(Constant.KHU_VUON_CUA_BAN, "Khu vườn Của Bạn"));
        list.add(new LayoutIDWithTitle(Constant.GRAB_DI_CHO, "Grab Đi Chợ"));
        navigationAdapter = new EasyAdapter<>(this, NavigationAdapter.class, list);
        lv_navigation.setAdapter(navigationAdapter);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
            Constant.CURRENT_FRAGMENT = -1;
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, findViewById(R.id.ll_right_drawer));
        }
    }
}
