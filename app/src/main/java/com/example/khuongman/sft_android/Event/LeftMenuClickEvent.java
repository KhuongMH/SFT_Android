package com.example.khuongman.sft_android.Event;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.example.khuongman.sft_android.Activity.GrabActivity;
import com.example.khuongman.sft_android.Activity.MainActivity;
import com.example.khuongman.sft_android.Adapter.NavigationAdapter;
import com.example.khuongman.sft_android.Classes.Constant;
import com.example.khuongman.sft_android.Classes.LayoutIDWithTitle;
import com.example.khuongman.sft_android.Fragment.Knowledge.VegetableFragment;
import com.example.khuongman.sft_android.Fragment.KnowledgeFragment;
import com.example.khuongman.sft_android.Fragment.OwnFarm.CameraSellFragment;
import com.example.khuongman.sft_android.Fragment.OwnFarmFragment;
import com.example.khuongman.sft_android.Fragment.TradeFragment;
import com.example.khuongman.sft_android.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.ribot.easyadapter.EasyAdapter;

public class LeftMenuClickEvent implements View.OnClickListener {
    int layoutID;
    FragmentTransaction ft;
    MainActivity mainActivity;


    public LeftMenuClickEvent(Context context, int layoutID) {
        this.mainActivity = (MainActivity) context;
        this.layoutID = layoutID;
    }

    @Override
    public void onClick(View view) {
        if (Constant.CURRENT_FRAGMENT == layoutID) {
            ((DrawerLayout) mainActivity.findViewById(R.id.drawer_layout))
                    .closeDrawer(Gravity.LEFT);
            return;
        }
        Constant.CURRENT_FRAGMENT = layoutID;
        MainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, mainActivity.findViewById(R.id.ll_right_drawer));
        ListView rightNavigation = (ListView) mainActivity.findViewById(R.id.lv_right_navigation);
        List<LayoutIDWithTitle> list = new ArrayList<>();
        EasyAdapter<LayoutIDWithTitle> adapter = new EasyAdapter<>(mainActivity, NavigationAdapter.class, list);

        ((DrawerLayout) mainActivity.findViewById(R.id.drawer_layout))
                .closeDrawer(Gravity.LEFT);
        ft = mainActivity.getFragmentManager().beginTransaction();
        rightNavigation.setAdapter(adapter);
        ((DrawerLayout) mainActivity.findViewById(R.id.drawer_layout))
                .closeDrawer(Gravity.LEFT);
        switch (layoutID) {
            case Constant.KIEN_THUC_NONG_NGHIEP: {
                break;
            }
            case Constant.GIA_CA_THI_TRUONG: {
                ft.replace(Constant.FRAME_ID, new KnowledgeFragment());
                list.clear();
                list.add(new LayoutIDWithTitle(Constant.RAU_CU_QUA, "Rau Củ Quả"));
                list.add(new LayoutIDWithTitle(Constant.TRAI_CAY, "Trái Cây"));
                list.add(new LayoutIDWithTitle(Constant.THUC_PHAM_TUOI_SONG, "Thực Phẩm Tươi Sống"));
                list.add(new LayoutIDWithTitle(Constant.THUC_PHAM_DONG_LANH, "Thực Phẩm Đông Lạnh"));
                list.add(new LayoutIDWithTitle(Constant.THUC_PHAM_SAY_KHO, "Thực Phẩm Sấy Khô"));
                list.add(new LayoutIDWithTitle(Constant.THUC_PHAM_DONG_HOP, "Thực Phẩm Đóng Hộp"));
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
                break;
            }
            case Constant.BUON_BAN_NONG_SAN: {
                ft.replace(Constant.FRAME_ID, new TradeFragment());
                list.clear();
                list.add(new LayoutIDWithTitle(Constant.BAN_BUON, "Bán Buôn"));
                list.add(new LayoutIDWithTitle(Constant.BAN_LE, "Bán lẻ"));
                list.add(new LayoutIDWithTitle(Constant.TRAO_DOI, "Trao Đổi"));
                list.add(new LayoutIDWithTitle(Constant.DINH_VI_MUA, "Định Vị Mua"));
                list.add(new LayoutIDWithTitle(Constant.DINH_VI_BAN, "Định Vị Bán"));
                list.add(new LayoutIDWithTitle(Constant.GUI_YEU_CAU, "Gửi Yêu Cầu"));
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
                break;
            }
            case Constant.HOI_DAP_THAC_MAC: {
                break;
            }
            case Constant.VUON_CUA_BAN: {
                ft.replace(Constant.FRAME_ID, new OwnFarmFragment());
                list.clear();
                list.add(new LayoutIDWithTitle(Constant.TONG_QUAN, "Tổng Quan"));
                list.add(new LayoutIDWithTitle(Constant.THEM_CAY_TRONG, "Thêm Cây Trồng"));
                list.add(new LayoutIDWithTitle(Constant.NHAC_NHO, "Nhắc Nhở"));
                list.add(new LayoutIDWithTitle(Constant.GOI_Y, "Gợi Ý"));
                list.add(new LayoutIDWithTitle(Constant.XOM_VUON, "Xóm Vườn"));
                list.add(new LayoutIDWithTitle(Constant.CHUP_HINH_RAO_BAN, "Chụp Hình Rao Bán"));
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
                break;
            }
            case Constant.GRAB_DI_CHO: {
                Intent intent = new Intent(mainActivity, GrabActivity.class);
                mainActivity.startActivity(intent);
                return;
            }
            case Constant.RAU_CU_QUA: {
                ft.replace(Constant.FRAME_ID, new VegetableFragment());
            }
            case Constant.CHUP_HINH_RAO_BAN: {
                ft.replace(Constant.FRAME_ID, new CameraSellFragment());
            }
        }
        ft.addToBackStack("ST " + Constant.FRAGMENT_COUNT++);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        rightNavigation.setVisibility(View.VISIBLE);

    }

}
