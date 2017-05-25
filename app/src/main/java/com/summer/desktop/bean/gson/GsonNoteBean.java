package com.summer.desktop.bean.gson;

//by summer on 2017-05-25.

import com.summer.desktop.base.BaseBean;

import java.util.ArrayList;

public class GsonNoteBean extends BaseBean {

    ArrayList<NoteDetail> data = new ArrayList<>();

    public ArrayList<NoteDetail> getData() {
        return data;
    }

    public void setData(ArrayList<NoteDetail> data) {
        this.data = data;
    }
}
