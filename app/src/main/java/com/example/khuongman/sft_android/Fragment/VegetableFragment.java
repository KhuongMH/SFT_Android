package com.example.khuongman.sft_android.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.khuongman.sft_android.Adapter.FoodAdapter;
import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Classes.Food;
import com.example.khuongman.sft_android.R;

import uk.co.ribot.easyadapter.EasyAdapter;

/**
 * Created by khuong.man on 7/20/2016.
 */
public class VegetableFragment extends Fragment {

    GridView gv_vegetable;
    EasyAdapter<Food> vegeAdapter;

    public VegetableFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vegetable, container, false);
        gv_vegetable = (GridView) view.findViewById(R.id.gv_vegetable);
        vegeAdapter = new EasyAdapter<Food>(getActivity(), FoodAdapter.class, Constant.CATEGORIES.get(0).getFoodList());
        gv_vegetable.setAdapter(vegeAdapter);
        return view;
    }
}