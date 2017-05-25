package com.summer.desktop.bean.gson;

import com.summer.desktop.base.BaseBean;

/**
 * Created by summer on 2017/5/25 23:46.
 */

public class ImageNote extends BaseBean {

    private String src;

    public ImageNote(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
