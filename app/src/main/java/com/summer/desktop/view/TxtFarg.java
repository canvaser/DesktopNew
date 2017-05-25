package com.summer.desktop.view;

//by summer on 2017-05-24.

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.bean.NoteBean;
import com.summer.desktop.imp.Onfinish;
import com.summer.desktop.util.IntentUtil;
import com.summer.desktop.util.LogUtil;
import com.summer.desktop.util.SPUtil;
import com.summer.desktop.util.ViewCreater;
import com.summer.desktop.util.XmlUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TxtFarg extends BaseFrag implements View.OnLongClickListener {


    Handler handler = new Handler();

    @BindView(R.id.txtroot)
    LinearLayout root;

    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    EditText ed;

    ArrayList<NoteBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_txt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        String s = "<desktop>" +
                "<image>1233</image>" +
                "<txt>How do I ufdddddddddddddddse Glide?</txt>" +
                "<image>http://pic62.nipic.com/file/20150319/12632424_132215178296_2.jpg</image>" +
                "<txt>How do I use Glide?</txt>" +
                "<image>http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg</image>" +
                "<txt>@OverridepublicViewgetView(intposition,Viewrecycled,ViewGroupcontainer){finalImageViewmyImageView;if(recycled==null){myImageView=(ImageView)inflater.inflate(R.layout.my_image_view,container,false);}else{myImageView=(ImageView)recycled;}Stringurl=myUrls.get(position);GlideApp.with(myFragment).load(url).centerCrop()..into(myImageView);returnmyImageView;}</txt>" +
                "<image>http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg</image>" +
                "<txt>@OverridepublicViewgetView(intposition,Viewrecycled,ViewGroupcontainer){finalImageViewmyImageView;if(recycled==null){myImageView=(ImageView)inflater.inflate(R.layout.my_image_view,container,false);}else{myImageView=(ImageView)recycled;}Stringurl=myUrls.get(position);GlideApp.with(myFragment).load(url).centerCrop()..into(myImageView);returnmyImageView;}</txt>" +
                "<image>http://pic.58pic.com/58pic/13/85/85/73T58PIC9aj_1024.jpg</image>" +
                "<txt>How do I use Glide?</txt>" +
                "<image>http://pic41.nipic.com/20140518/18521768_133448822000_2.jpg</image>" +
                "<txt>How do I use Glide?</txt>" +
                "<link>http://blog.csdn.net/bear_huangzhen/article/details/24477327</link>" +
                "<link>http://blog.csdn.net/bear_huangzhen/article/details/24477s327</link>" +
                "<link>http://blog.csdn.net/bear_huangzhen/article/details/244773dd27</link>" +
                "<txt>ffff</txt>" +
                "<txt>Version 3 on the 3.0 branch is a stable public release used in multiple open source projects at Google including in the Android Camera app and in the 2014 Google IO app. Version 4 is currently under development on the master branch.\n" +
                "</desktop>";

        String ss = SPUtil.getInstance().init(getContext()).getStr("desktop");
        if (ss.equals("")) {
            SPUtil.getInstance().saveStr("desktop", s);
        }
        ss = SPUtil.getInstance().getStr("desktop");
        LogUtil.E(ss);
        new XmlUtil().analysis(ss, new Onfinish() {
            @Override
            public void finished(Object o) {
                list = (ArrayList<NoteBean>) o;
                root.addView(ViewCreater.create(getContext(), list), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ViewGroup view1 = (ViewGroup) root.getChildAt(0);
                for (int i = 0; i < view1.getChildCount(); i++) {
                    view1.getChildAt(i).setOnLongClickListener(TxtFarg.this);
                    if (view1.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) view1.getChildAt(i);
                        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (hasFocus) {
                                    ed = (EditText) v;
                                }
                            }
                        });

                    }
                }
            }
        });

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                            IntentUtil.getInstance().photoShowFromphone(TxtFarg.this, 0);
                        }
                    });
            builder.rotateText(true);
            builder.rotateImage(true);
            bmb.addBuilder(builder);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        BoomFrag boomFrag = new BoomFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, boomFrag);
        transaction.commit();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String s = "<desktop>";
        for (int i = 0; i < list.size(); i++) {
            String item = "<" + list.get(i).getTagName() + ">" + list.get(i).getTxt() + "</" + list.get(i).getTagName() + ">";
            s += item;
        }
        s += "</desktop>";
        SPUtil.getInstance().saveStr("desktop", s);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (ed != null && list.size() > 0) {
            int i = (int) ed.getTag(R.id.position);
            list.add(i, new NoteBean(NoteBean.IMAGE, data.getDataString(), null));
        }
    }
}
