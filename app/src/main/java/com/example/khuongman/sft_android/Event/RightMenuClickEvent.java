package com.example.khuongman.sft_android.Event;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.example.khuongman.sft_android.Activity.MainActivity;
import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Fragment.KnowledgeFragment;
import com.example.khuongman.sft_android.R;

public class RightMenuClickEvent implements View.OnClickListener {
    int layoutID;
    FragmentTransaction ft;
    Context context;

    public RightMenuClickEvent(Context context, int layoutID) {
        this.context = context;
        this.layoutID = layoutID;
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) context;

        ((DrawerLayout) mainActivity.findViewById(R.id.drawer_layout))
                .closeDrawer(Gravity.LEFT);
        ft = mainActivity.getFragmentManager().beginTransaction();
        switch (layoutID) {
            case Constant.KIEN_THUC_NONG_NGHIEP: {
                ((DrawerLayout) mainActivity.findViewById(R.id.drawer_layout))
                        .closeDrawer(Gravity.LEFT);
                ft.replace(Constant.FRAME_ID, new KnowledgeFragment());
                break;
            }
        }
        ft.addToBackStack("ST " + Constant.FRAGMENT_COUNT++);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
