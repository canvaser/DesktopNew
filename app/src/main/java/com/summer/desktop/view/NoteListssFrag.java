package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.bean.gson.Note;

import java.util.ArrayList;
import java.util.Random;

public class NoteListssFrag extends BaseFrag{




    @BindView(R.id.container)
    MainViewPager container;

    Handler handler = new Handler();

    Random random = new Random();

    ArrayList<Note> notes;


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
       }
        container.setAdapter(new NoteListsAdapter(getChildFragmentManager(),notes));
    }



}
