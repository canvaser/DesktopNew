package com.summer.desktop.bean.gson;

//by summer on 2017-05-26.

import com.summer.desktop.base.BaseBean;

public class LinkNote extends BaseBean {

    private String link;

    public LinkNote() {
    }

    public LinkNote(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
