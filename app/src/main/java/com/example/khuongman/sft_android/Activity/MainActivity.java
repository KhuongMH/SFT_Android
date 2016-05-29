package com.example.khuongman.sft_android.Activity;

import android.app.FragmentTransaction;
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
import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Classes.LayoutIDWithTitle;
import com.example.khuongman.sft_android.Fragment.MainFragment;
import com.example.khuongman.sft_android.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.ribot.easyadapter.EasyAdapter;

public class MainActivity extends FragmentActivity {

    ImageView iv_navigation;
    ListView lv_navigation;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    EasyAdapter<LayoutIDWithTitle> navigationAdapter;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_navigation = (ImageView) findViewById(R.id.iv_navigation);
        lv_navigation = (ListView)findViewById(R.id.lv_left_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Constant.FRAME_ID = R.id.fragment_skeleton;
        ft = getFragmentManager().beginTransaction();
        ft.replace(Constant.FRAME_ID, new MainFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        initNavigation();
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
        list.add(new LayoutIDWithTitle(Constant.KIEN_THUC_NONG_NGHIEP,"Kiến Thức Nông Nghiệp"));
        list.add(new LayoutIDWithTitle(Constant.GIA_CA_THI_TRUONG,"Giá Cả Thị Trường"));
        list.add(new LayoutIDWithTitle(Constant.BUON_BAN_NONG_SAN,"Buôn Bán Nông Sản"));
        list.add(new LayoutIDWithTitle(Constant.HOI_DAP_THAC_MAC,"Hỏi Đáp - Thắc Mắc"));
        list.add(new LayoutIDWithTitle(Constant.KHU_VUON_CUA_BAN,"Khu vườn Của Bạn"));
        list.add(new LayoutIDWithTitle(Constant.GRAB_DI_CHO,"Grab Đi Chợ"));
        navigationAdapter = new EasyAdapter<>(this, NavigationAdapter.class,list);
        lv_navigation.setAdapter(navigationAdapter);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount()==0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            findViewById(R.id.lv_right_navigation).setVisibility(View.GONE);
        }
    }
}
