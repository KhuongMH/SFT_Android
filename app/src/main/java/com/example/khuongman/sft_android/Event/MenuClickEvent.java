package com.example.khuongman.sft_android.Event;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.khuongman.sft_android.R;

/**
 * Created by Man Huynh Khuong on 28/05/16.
 */
public class MenuClickEvent implements View.OnClickListener{

    int layoutID;

    public MenuClickEvent(int layoutID){
        this.layoutID = layoutID;
    }

    @Override
    public void onClick(View view) {
        switch (layoutID) {
            case 0: {
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
        }
    }
}
