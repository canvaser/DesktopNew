package com.summer.desktop.main;

//by summer on 2017-05-23.


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.summer.desktop.R;
import com.summer.desktop.base.BaseAct;
import com.summer.desktop.util.FragList;
import com.summer.desktop.view.BottomItemView;
import com.summer.desktop.view.MainFrag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAct extends BaseAct implements View.OnClickListener{

    @BindView(R.id.viewpager)
    BottomItemView viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewPager.setAdapter(new BottomItemView.MenuAdapter(this));
        ((BottomItemView.MenuAdapter)viewPager.getAdapter()).setOnClickListener(this);
        MainFrag mainFrag = new MainFrag();
        Bundle bundle = new Bundle();
        bundle.putBoolean("first",true);
        mainFrag.setArguments(bundle);
        FragList.getInstance().add(this,mainFrag);

    }

    long aLong =0;

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag(R.id.data);
        if((position-2)>=0){
            if((position-2)==viewPager.getCurrentItem()){
               if((System.currentTimeMillis()-aLong)<1000){
                   Toast.makeText(getApplicationContext(),"double kill",Toast.LENGTH_SHORT).show();
                   FragList.getInstance().removeTop(this);
                   aLong=0;
               }
               aLong = System.currentTimeMillis();
            }
            viewPager.setCurrentItem((position-2));
        }
    }
}
