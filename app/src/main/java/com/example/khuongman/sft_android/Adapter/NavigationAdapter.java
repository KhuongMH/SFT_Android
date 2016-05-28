package com.example.khuongman.sft_android.Adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khuongman.sft_android.R;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by khuong.man on 5/20/2016.
 */
@LayoutId(R.layout.item_navigation)
public class NavigationAdapter extends ItemViewHolder<String> {
    @ViewId(R.id.tv_navigation)
    TextView tv_navigation;
    @ViewId(R.id.tv_arrow)
    TextView tv_arrow;

    public NavigationAdapter(View view) {
        super(view);
    }

    @Override
    public void onSetValues(String s, PositionInfo positionInfo) {
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/BLKCHCRY.TTF");
        tv_arrow.setTypeface(face);
        tv_navigation.setText(s);
        tv_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),null);
                getContext().startActivity(intent);
            }
        });
    }
}
