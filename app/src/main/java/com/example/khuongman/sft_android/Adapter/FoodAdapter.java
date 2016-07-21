package com.example.khuongman.sft_android.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khuongman.sft_android.Classes.Food;
import com.example.khuongman.sft_android.R;
import com.squareup.picasso.Picasso;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.fragment_food_item)
public class FoodAdapter extends ItemViewHolder<Food> {
    @ViewId(R.id.iv_food_img)
    ImageView iv_food_img;
    @ViewId(R.id.tv_food_name)
    TextView tv_food_name;
    @ViewId(R.id.tv_food_price)
    TextView tv_food_price;

    public FoodAdapter(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Food food, PositionInfo positionInfo) {
        Picasso.with(getContext()).load(food.getImage()).into(iv_food_img);
        tv_food_name.setText(food.getName());
        tv_food_price.setText(food.getPrice());
    }
}