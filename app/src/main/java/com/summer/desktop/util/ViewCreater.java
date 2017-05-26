package com.summer.desktop.util;

//by summer on 2017-05-25.

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.summer.desktop.R;
import com.summer.desktop.bean.gson.ImageNote;
import com.summer.desktop.bean.gson.NoteDetail;
import com.summer.desktop.bean.gson.TxtNote;

import java.util.ArrayList;

public class ViewCreater {

    public static Gson gson = new Gson();

    public static View create(Context context,ArrayList<NoteDetail> data) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        if (data == null) {
            return linearLayout;
        }

        for (int i = 0; i < data.size(); i++) {
            switch (data.get(i).getType()) {
                case NoteDetail.IMAGE:
                    ImageView imageView = new ImageView(context);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    ImageNote imageNote = gson.fromJson(data.get(i).getData(),ImageNote.class);
                    Glide.with(context).asBitmap().load(imageNote.getSrc()).into(imageView);
                    //ImageLoader.getInstance().displayImage(imageNote.getSrc(),imageView);
                    linearLayout.addView(imageView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setTag(R.id.position, i);
                    break;
                case NoteDetail.TXT:
                    EditText textView = new EditText(context);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    TxtNote txtNote = gson.fromJson(data.get(i).getData(),TxtNote.class);
                    textView.setText(txtNote.getTxt());
                    textView.setTag(R.id.position, i);
                    break;
            }
        }
        return linearLayout;

    }
}
