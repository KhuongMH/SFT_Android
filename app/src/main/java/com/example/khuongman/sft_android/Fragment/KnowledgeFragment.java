package com.example.khuongman.sft_android.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Event.LeftMenuClickEvent;
import com.example.khuongman.sft_android.R;

/**
 * Created by Man Huynh Khuong on 28/05/16.
 */
public class KnowledgeFragment extends Fragment{

    public KnowledgeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);
        view.findViewById(R.id.ll_top_1).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.RAU_CU_QUA));
        view.findViewById(R.id.ll_top_2).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.TRAI_CAY));
        view.findViewById(R.id.ll_middle_1).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.THUC_PHAM_TUOI_SONG));
        view.findViewById(R.id.ll_middle_2).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.THUC_PHAM_DONG_LANH));
        view.findViewById(R.id.ll_bottom_1).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.THUC_PHAM_SAY_KHO));
        view.findViewById(R.id.ll_bottom_2).setOnClickListener(new LeftMenuClickEvent(getActivity(),
                Constant.THUC_PHAM_DONG_HOP));
        return view;
    }
}
