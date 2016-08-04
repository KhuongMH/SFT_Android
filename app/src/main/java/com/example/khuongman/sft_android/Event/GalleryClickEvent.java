package com.example.khuongman.sft_android.Event;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.khuongman.sft_android.Activity.MainActivity;
import com.example.khuongman.sft_android.Classes.Constant;

/**
 * Created by khuong.man on 7/28/2016.
 */
public class GalleryClickEvent implements View.OnClickListener{

    MainActivity mainActivity;

    public GalleryClickEvent(Context context) {
        this.mainActivity = (MainActivity) context;
    }

    @Override
    public void onClick(View v) {
        if (!checkPermissionReadExternal()) {
            ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    0);
        } else {
            Constant.acceptedPermissionReadExternal(mainActivity);
        }
    }

    // return true when "Write External" Permission is existed
    public boolean checkPermissionReadExternal() {
        return ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(mainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }



}