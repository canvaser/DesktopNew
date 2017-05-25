package com.summer.desktop.bean.gson;

import com.summer.desktop.base.BaseBean;

/**
 * Created by summer on 2017/5/25 23:43.
 */

public class NoteDetail extends BaseBean{

    public static final String IMAGE = "image";

    public static final String TXT = "txt";


    private String type;

    private String data;

    public NoteDetail(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
