package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFrag extends BaseFrag{




    @BindView(R.id.container)
    MainViewPager container;

    Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        container.setAdapter(new ContainAdapter(getChildFragmentManager()));
    }



}
