package com.example.khuongman.sft_android.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.khuongman.sft_android.Event.MenuClickEvent;
import com.example.khuongman.sft_android.R;

/**
 * Created by Man Huynh Khuong on 28/05/16.
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.ll_top_1).setOnClickListener(new MenuClickEvent(0));
        view.findViewById(R.id.ll_top_2).setOnClickListener(new MenuClickEvent(1));
        view.findViewById(R.id.ll_middle_1).setOnClickListener(new MenuClickEvent(2));
        view.findViewById(R.id.ll_middle_2).setOnClickListener(new MenuClickEvent(3));
        view.findViewById(R.id.ll_bottom_1).setOnClickListener(new MenuClickEvent(4));
        view.findViewById(R.id.ll_bottom_2).setOnClickListener(new MenuClickEvent(5));

        return view;
    }
}
