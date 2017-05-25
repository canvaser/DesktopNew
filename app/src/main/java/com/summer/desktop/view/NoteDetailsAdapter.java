package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class NoteDetailsAdapter extends FragmentStatePagerAdapter {


    public NoteDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new NoteDetailFrag();
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//    }

    @Override
    public int getCount() {
        return 1;
    }
}
