package com.summer.desktop.util;

//by summer on 2017-05-25.

import android.util.Xml;

import com.summer.desktop.bean.NoteBean;
import com.summer.desktop.imp.Onfinish;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class XmlUtil {

    public void analysis(String xml, Onfinish onfinish) {
        XmlPullParser parser = Xml.newPullParser();
        LogUtil.E("fdfdfdfd");
        try {
            parser.setInput(new ByteArrayInputStream(xml.getBytes("UTF-8")), "UTF-8");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int type = 0;
        try {
            type = parser.getEventType();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        ArrayList<NoteBean> noteBeen = new ArrayList<>();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.TEXT:
                    try {
                        String txt = parser.getText();
                        type = parser.nextTag();
                        String tagName = parser.getName();
                        LogUtil.E("----------------------");
                        noteBeen.add(new NoteBean(tagName, txt, "<" + tagName + ">" + txt + "</" + tagName + ">"));
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            try {
                type = parser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        onfinish.finished(noteBeen);
    }
}
