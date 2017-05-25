package com.summer.desktop.base;

//by summer on 2017-05-23.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseFrag extends Fragment{

    protected FragmentActivity activity;

    protected Fragment fragment;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        fragment = this;
    }
}
