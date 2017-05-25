package com.summer.desktop.view;

//by summer on 2017-05-24.

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoomFrag extends BaseFrag implements View.OnClickListener {


    @BindView(R.id.circle_menu)
    CircleMenu circleMenu;

    @BindView(R.id.boomfrag)
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_boom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round)
                .addSubMenu(Color.parseColor("#258CFF"), R.mipmap.ic_launcher_round)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.ic_launcher_round)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.ic_launcher_round)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.ic_launcher_round)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.ic_launcher_round)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {

                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
                transaction.remove(BoomFrag.this);
                transaction.commit();
            }

        });
        circleMenu.openMenu();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_right_out);
        transaction.remove(BoomFrag.this);
        transaction.commit();
    }
}
