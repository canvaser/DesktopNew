package com.summer.desktop.util;

//by summer on 2017-05-25.

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.summer.desktop.R;
import com.summer.desktop.bean.NoteBean;

import java.util.ArrayList;

public class ViewCreater {

    public static View create(Context context, ArrayList<NoteBean> list) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        if (list == null) {
            return linearLayout;
        }

        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getTagName()) {
                case NoteBean.IMAGE:
                    ImageView imageView = new ImageView(context);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Glide.with(context).asBitmap().load(list.get(i).getTxt()).into(imageView);
                    linearLayout.addView(imageView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setTag(list.get(i).getHtml());
                    imageView.setTag(R.id.position, i);
                    break;
                case NoteBean.TXT:
                    EditText textView = new EditText(context);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setText(list.get(i).getTxt());
                    textView.setTag(list.get(i).getHtml());
                    textView.setTag(R.id.position, i);
                    break;
            }
        }
        return linearLayout;

    }
}
