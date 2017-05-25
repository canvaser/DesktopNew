package com.summer.desktop.bean;

//by summer on 2017-05-25.

import com.summer.desktop.base.BaseBean;

import java.util.ArrayList;

public class GsonNoteBean extends BaseBean {

    ArrayList<NoteBean> data = new ArrayList<>();

    public ArrayList<NoteBean> getData() {
        return data;
    }

    public void setData(ArrayList<NoteBean> data) {
        this.data = data;
    }
}
