package com.example.khuongman.sft_android.Activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.khuongman.sft_android.Adapter.NavigationAdapter;
import com.example.khuongman.sft_android.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.ribot.easyadapter.EasyAdapter;

public class MainActivity extends FragmentActivity {

    ImageView iv_navigation;
    ListView lv_navigation;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    EasyAdapter<String> navigationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_navigation = (ImageView) findViewById(R.id.iv_navigation);
        lv_navigation = (ListView)findViewById(R.id.lv_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        initNavigation();
//        addItemToNavigation();
    }
//
//    private void addItemToNavigation() {
//        List<String> list = new ArrayList<>();
//        list.add("Công Thức ");
//    }

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
        List<String> list = new ArrayList<>();
        list.add("Kiến Thức Nông Nghiệp");
        list.add("Giá Cả Thị Trường");
        list.add("Giao Dịch Nông Sản");
        list.add("Hỏi Đáp - Thắc Mắc");
        list.add("Cây Trồng Của Bạn");
        list.add("Grab Đi Chợ");
        navigationAdapter = new EasyAdapter<>(getApplicationContext(), NavigationAdapter.class,list);
        lv_navigation.setAdapter(navigationAdapter);
    }

}
