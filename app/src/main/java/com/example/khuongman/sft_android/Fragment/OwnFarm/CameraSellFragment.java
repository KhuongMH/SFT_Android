package com.example.khuongman.sft_android.Fragment.OwnFarm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.support.v7.widget.RecyclerView;

import com.example.khuongman.sft_android.Adapter.ChosenPhotoAdapter;
import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Event.CameraClickEvent;
import com.example.khuongman.sft_android.Event.GalleryClickEvent;
import com.example.khuongman.sft_android.R;
import uk.co.ribot.easyadapter.EasyAdapter;

/**
 * Created by khuong.man on 7/26/2016.
 */
public class CameraSellFragment extends Fragment {

    ImageView iv_camera, iv_gallery;
    RecyclerView rv_photo;
    Spinner spiner_category, spiner_transport;
    EditText et_name, et_price_le, et_price_si, et_kg_si;
    AutoCompleteTextView actv_donvi;

    public static ChosenPhotoAdapter chosenPhotoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_sell, container, false);
        initComponent(view);
        iv_camera.setOnClickListener(new CameraClickEvent(getActivity()));
        iv_gallery.setOnClickListener(new GalleryClickEvent(getActivity()));

        chosenPhotoAdapter = new ChosenPhotoAdapter(getActivity(), Constant.CHOSEN_PHOTOS);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_photo.setLayoutManager(layoutManager);
        rv_photo.setAdapter(chosenPhotoAdapter);
        return view;
    }

    public void initComponent(View view) {
        iv_camera = (ImageView) view.findViewById(R.id.iv_camera);
        iv_gallery = (ImageView) view.findViewById(R.id.iv_gallery);
        rv_photo = (RecyclerView) view.findViewById(R.id.rv_photo);
        spiner_category = (Spinner) view.findViewById(R.id.spiner_category);
        spiner_transport = (Spinner) view.findViewById(R.id.spiner_transport);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_price_le = (EditText) view.findViewById(R.id.et_price_le);
        et_price_si = (EditText) view.findViewById(R.id.et_price_si);
        et_kg_si = (EditText) view.findViewById(R.id.et_kg_si);
        actv_donvi = (AutoCompleteTextView) view.findViewById(R.id.actv_donvi);
    }
}