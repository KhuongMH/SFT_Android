package com.example.khuongman.sft_android.Classes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static String APP_NAME = "AgriTech";
    public static int FRAME_ID = 0;
    public static String FRAME_TAG = "GG ";
    public static int FRAGMENT_COUNT = 0;
    public static int CURRENT_FRAGMENT = -1;
    public static int LOCATION_PERMISSION = 0;
    public static int WAIT_PERMISSION = 0;
    public static List<FoodCategory> CATEGORIES = new ArrayList<>();

    //LEFT NAVIGATION
    public final static int KIEN_THUC_NONG_NGHIEP = 0;
    public final static int GIA_CA_THI_TRUONG = 1;
    public final static int BUON_BAN_NONG_SAN = 2;
    public final static int HOI_DAP_THAC_MAC = 3;
    public final static int VUON_CUA_BAN = 4;
    public final static int GRAB_DI_CHO = 5;

    //RIGHT NAVIGATION - BUON_BAN_NONG_SAN
    public final static int BAN_BUON = 6;
    public final static int BAN_LE = 7;
    public final static int TRAO_DOI = 8;
    public final static int DINH_VI_MUA = 9;
    public final static int DINH_VI_BAN = 10;
    public final static int GUI_YEU_CAU = 11;

    //RIGHT NAVIGATION - GIA_CA
    public final static int RAU_CU_QUA = 12;
    public final static int TRAI_CAY = 13;
    public final static int THUC_PHAM_TUOI_SONG = 14;
    public final static int THUC_PHAM_DONG_LANH = 15;
    public final static int THUC_PHAM_SAY_KHO = 16;
    public final static int THUC_PHAM_DONG_HOP = 17;

    //RIGHT NAVIGATION - KHU_VUON_CUA_BAN
    public final static int TONG_QUAN = 18;
    public final static int THEM_CAY_TRONG = 19;
    public final static int NHAC_NHO = 20;
    public final static int GOI_Y = 21;
    public final static int XOM_VUON = 22;
//    public final static int ALBUM_ANH = 23;
    public final static int CHUP_HINH_RAO_BAN = 23;

    //RIGHT NAVIGATION - GRAB_DI_CHO
    public final static int TIM_NGUOI_DI_CHO = 24;
    public final static int THONG_TIN_NGUOI_DI_CHO = 25;
    public final static int GUI_YEU_CAU_DI_CHO = 26;
    public final static int DANH_GIA = 27;

    //CAMERA_SELL_FRAGMENT Variables
    public static int PHOTO_COUNT = 1;
    public static int TAKE_PHOTO_CODE = 8998;
    public static int RESULT_LOAD_IMG = 9889;
    public static String CURRENT_PHOTO_PATH = "";
    public static List<String> CHOSEN_PHOTOS = new ArrayList<>();
    public static Uri uriPhotoExternalStorage(String folderName, String fileName) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM" + folderName;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String file = path + "/" + fileName + ".jpg";
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {
        }
        Constant.CURRENT_PHOTO_PATH = newfile.getAbsolutePath();
        return Uri.fromFile(newfile);
    }

    public static void acceptedPermissionReadExternal(Activity activity) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
}
