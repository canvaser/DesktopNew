package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.summer.desktop.bean.gson.Note;

import java.util.ArrayList;

public class NoteListsAdapter extends FragmentStatePagerAdapter{

    ArrayList<Note> notes;

    public NoteListsAdapter(FragmentManager fm,ArrayList<Note> notes) {
        super(fm);
        this.notes = notes;
    }

    @Override
    public Fragment getItem(int position) {
        NoteListFrag noteListFrag = new NoteListFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",notes.get(position));
        noteListFrag.setArguments(bundle);
        return noteListFrag;
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
        return notes==null?0:notes.size();
    }
}
