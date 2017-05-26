package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.bean.gson.Note;
import com.summer.desktop.main.MainAct;
import com.summer.desktop.util.TitleUtil;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListssFrag extends BaseFrag{




    @BindView(R.id.container)
    MainViewPager container;

    Handler handler = new Handler();

    Random random = new Random();

    ArrayList<Note> notes;

    int position = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
       if(getArguments()!=null && getArguments().getSerializable("data")!=null){
           notes = (ArrayList<Note>) getArguments().getSerializable("data");
           position = getArguments().getInt("position");
       }
        container.setAdapter(new NoteListsAdapter(getChildFragmentManager(),notes));
        container.setCurrentItem(position);
        TitleUtil.getInstance().getName().add(notes.get(position).getName());
        ((MainAct) getActivity()).setTitle();
        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TitleUtil.getInstance().getName().remove(TitleUtil.getInstance().getName().size() - 1);
                TitleUtil.getInstance().getName().add(notes.get(position).getName());
                ((MainAct) getActivity()).setTitle();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TitleUtil.getInstance().getName().remove(TitleUtil.getInstance().getName().get(TitleUtil.getInstance().getName().size() - 1));
    }
}
