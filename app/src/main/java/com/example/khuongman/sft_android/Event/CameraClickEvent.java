package com.example.khuongman.sft_android.Event;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.khuongman.sft_android.Activity.MainActivity;
import com.example.khuongman.sft_android.Classes.Constant;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by khuong.man on 7/26/2016.
 */
public class CameraClickEvent implements View.OnClickListener{

    MainActivity mainActivity;

    public CameraClickEvent(Context context) {
        this.mainActivity = (MainActivity) context;
    }

    @Override
    public void onClick(View v) {
        if (!checkPermissionWriteExternal() && !checkPermissionCamera()) {
            ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    0);
        } else {
            acceptedPermission();
        }
    }

    // return true when "Write External" Permission is existed
    public boolean checkPermissionWriteExternal() {
        return ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(mainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    // return true when "Camera" Permission is existed
    public boolean checkPermissionCamera() {
        return ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(mainActivity,
                Manifest.permission.CAMERA);
    }

    public void acceptedPermission() {
        String fileName = "" + Constant.PHOTO_COUNT++;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Constant.uriPhotoExternalStorage(Constant.APP_NAME, fileName));
        mainActivity.startActivityForResult(cameraIntent, Constant.TAKE_PHOTO_CODE);
    }

}
