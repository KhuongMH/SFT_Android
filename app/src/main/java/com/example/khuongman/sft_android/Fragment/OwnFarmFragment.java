package com.example.khuongman.sft_android.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Event.LeftMenuClickEvent;
import com.example.khuongman.sft_android.R;

public class OwnFarmFragment extends Fragment {

    public OwnFarmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ownfarm, container, false);
        view.findViewById(R.id.ll_bottom_2).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.CHUP_HINH_RAO_BAN));
        return view;
    }
}
