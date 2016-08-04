package com.example.khuongman.sft_android.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khuongman.sft_android.Activity.MainActivity;
import com.example.khuongman.sft_android.Classes.Food;
import com.example.khuongman.sft_android.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by khuong.man on 7/26/2016.
 */
public class ChosenPhotoAdapter extends RecyclerView.Adapter<ChosenPhotoAdapter.MyViewHolder> {
    List<String> chosenPhotos;
    MainActivity mainActivity;

    public ChosenPhotoAdapter(Context context, List<String> chosenPhotos) {
        mainActivity = (MainActivity) context;
        this.chosenPhotos = chosenPhotos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_camera_sell_photo, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String photo = chosenPhotos.get(position);
        Picasso.with(mainActivity).load(Uri.fromFile(new File(photo))).resize(400,300).into(holder.iv_photo);
    }

    @Override
    public int getItemCount() {
        return chosenPhotos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_photo;
        public MyViewHolder(View view) {
            super(view);
            iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        }
    }
}
