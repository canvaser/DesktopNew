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

import com.google.gson.Gson;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.bean.gson.GsonNoteBean;
import com.summer.desktop.bean.gson.ImageNote;
import com.summer.desktop.bean.gson.Note;
import com.summer.desktop.bean.gson.NoteDetail;
import com.summer.desktop.bean.gson.TxtNote;
import com.summer.desktop.imp.Onfinish;
import com.summer.desktop.util.IntentUtil;
import com.summer.desktop.util.LogUtil;
import com.summer.desktop.util.ViewCreater;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class NoteDetailFrag extends BaseFrag implements View.OnLongClickListener {


    Handler handler = new Handler();

    @BindView(R.id.txtroot)
    LinearLayout root;

    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    EditText ed;

    Gson gson = new Gson();

    GsonNoteBean bean;

    Note note;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_txt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        note = (Note) getArguments().getSerializable("data");

        bean = gson.fromJson(note.getData(), GsonNoteBean.class);
        if (bean.getData() == null) {
            bean.setData(new ArrayList<NoteDetail>());
        }

        getData();
        String[] strings = new String[]{"图片", "文字", "文件", "链接", "", "", "", "", ""};
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalText(strings[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                            switch (index) {
                                case 0:
                                    IntentUtil.getInstance().photoShowFromphone(NoteDetailFrag.this, 0);
                                    break;
                                case 1:
                                    bean.getData().add(new NoteDetail(NoteDetail.TXT, gson.toJson(new TxtNote("new\\n"))));
                                    getData();
                                    break;
                                case 3:
                                    //bean.getData().add(new NoteDetail(NoteDetail.LINK,gson.toJson(new LinkNote("new\\n"))));
//                                    getData();
                                    break;
                            }
                        }
                    });
            builder.rotateText(true);
            builder.rotateImage(true);
            bmb.addBuilder(builder);
        }
    }

    public void getData() {
        root.removeAllViews();
        root.addView(ViewCreater.create(getContext(), bean.getData()), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ViewGroup view1 = (ViewGroup) root.getChildAt(0);
        for (int i = 0; i < view1.getChildCount(); i++) {
            view1.getChildAt(i).setOnLongClickListener(NoteDetailFrag.this);
            view1.getChildAt(i).setTag(R.id.position, i);
            view1.getChildAt(i).setTag(R.id.data, bean.getData().get(i));
            if (view1.getChildAt(i) instanceof EditText) {
                final EditText editText = (EditText) view1.getChildAt(i);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            ed = (EditText) v;
                        } else {
                            int i = (int) v.getTag(R.id.position);
                            EditText editText = (EditText) v;
                            TxtNote txtNote = gson.fromJson(bean.getData().get(i).getData(), TxtNote.class);
                            txtNote.setTxt(editText.getText().toString());
                            bean.getData().get(i).setData(gson.toJson(txtNote));
                        }
                    }
                });

            }
        }
    }

    @Override
    public boolean onLongClick(final View v) {
        BoomFrag boomFrag = new BoomFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, boomFrag);
        transaction.commit();
        boomFrag.setOnfinish(new Onfinish() {
            @Override
            public void finished(Object o) {
                NoteDetail noteDetail = (NoteDetail) v.getTag(R.id.data);
                int i = (int) v.getTag(R.id.position);
                bean.getData().add(i, new NoteDetail(NoteDetail.TXT, gson.toJson(new TxtNote("new\\n"))));
                getData();
            }
        });
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        int[] ints = new int[]{0};
        update(bean.getData(), ints, new Onfinish() {
            @Override
            public void finished(Object o) {
                LogUtil.E("finish");
            }
        });
    }

    public void update(final ArrayList<NoteDetail> noteDetails, final int[] i, final Onfinish onfinish) {
        if (noteDetails == null || noteDetails.size() == 0) {
            return;
        }
        if (i[0] == 0) {
            Note n = new Note();
            n.setData(gson.toJson(bean));
            note.setData(gson.toJson(bean));
            n.update(note.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    onfinish.finished(i);
                }
            });
        }
        if (noteDetails.size() <= i[0] && i[0] != 0) {
            Note n = new Note();
            n.setData(gson.toJson(bean));
            note.setData(gson.toJson(bean));
            n.update(note.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    onfinish.finished(i);
                }
            });
            return;
        }
        if (noteDetails.get(i[0]).getType().equals(NoteDetail.IMAGE)) {
            final ImageNote imageNote = gson.fromJson(bean.getData().get(i[0]).getData(), ImageNote.class);
            if (imageNote.getSrc().startsWith("file://")) {
                java.io.File file = new java.io.File(imageNote.getSrc().substring("file://".length(), imageNote.getSrc().length()));
                file.exists();
                final BmobFile bmobFile = new BmobFile(file);

                bmobFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            imageNote.setSrc(bmobFile.getFileUrl());
                            noteDetails.get(i[0]).setData(gson.toJson(imageNote));
                        }
                        i[0]++;
                        update(noteDetails, i, onfinish);
                    }

                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                    }
                });
            } else {
                i[0]++;
                update(noteDetails, i, onfinish);
            }
        } else {
            i[0]++;
            update(noteDetails, i, onfinish);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (ed != null && bean.getData()!=null && bean.getData().size() > 0) {
            int i = (int) ed.getTag(R.id.position);
            bean.getData().add(i,new NoteDetail(NoteDetail.IMAGE,gson.toJson(new ImageNote(data.getDataString()))));
        } else {
            bean.getData().add(new NoteDetail(NoteDetail.IMAGE, gson.toJson(new ImageNote(data.getDataString()))));
        }



        root.removeAllViews();
        root.addView(ViewCreater.create(getContext(), bean.getData()), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ViewGroup view1 = (ViewGroup) root.getChildAt(0);
        for (int i = 0; i < view1.getChildCount(); i++) {
            view1.getChildAt(i).setOnLongClickListener(NoteDetailFrag.this);
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
}
