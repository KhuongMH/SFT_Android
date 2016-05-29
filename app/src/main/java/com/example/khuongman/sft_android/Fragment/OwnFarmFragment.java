package com.example.khuongman.sft_android.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khuongman.sft_android.R;

/**
 * Created by Man Huynh Khuong on 28/05/16.
 */
public class OwnFarmFragment extends Fragment {

    public OwnFarmFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grab, container, false);
        return view;
    }
}
