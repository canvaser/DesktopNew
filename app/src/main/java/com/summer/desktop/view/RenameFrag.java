package com.summer.desktop.view;

//by summer on 2017-05-26.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.imp.Onfinish;
import com.summer.desktop.util.FragList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenameFrag extends BaseFrag {

    @BindView(R.id.txt)
    EditText editText;

    @BindView(R.id.delete)
    ImageView delete;

    Onfinish onfinish;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_rename, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.delete})
    public void onClick(View v) {
        FragList.getInstance().removeTop(getActivity());
        if (onfinish != null) {
            onfinish.finished(editText.getText().toString());
        }
    }

    public void setOnfinish(Onfinish onfinish) {
        this.onfinish = onfinish;
    }
}
