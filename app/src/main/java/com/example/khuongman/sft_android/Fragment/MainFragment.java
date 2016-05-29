package com.example.khuongman.sft_android.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Event.MenuClickEvent;
import com.example.khuongman.sft_android.R;

/**
 * Created by Man Huynh Khuong on 28/05/16.
 */
public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.ll_top_1).setOnClickListener(new MenuClickEvent(getContext(),
                Constant.KIEN_THUC_NONG_NGHIEP));
        view.findViewById(R.id.ll_top_2).setOnClickListener(new MenuClickEvent(getContext(),
                Constant.GIA_CA_THI_TRUONG));
        view.findViewById(R.id.ll_middle_1).setOnClickListener(new MenuClickEvent(getContext(),
                Constant.BUON_BAN_NONG_SAN));
        view.findViewById(R.id.ll_middle_2).setOnClickListener(new MenuClickEvent(getContext(),
                Constant.HOI_DAP_THAC_MAC));
        view.findViewById(R.id.ll_bottom_1).setOnClickListener(new MenuClickEvent(getContext(),
                Constant.KHU_VUON_CUA_BAN));
        view.findViewById(R.id.ll_bottom_2).setOnClickListener(new MenuClickEvent(getContext(),
                Constant.GRAB_DI_CHO));

        return view;
    }
}
