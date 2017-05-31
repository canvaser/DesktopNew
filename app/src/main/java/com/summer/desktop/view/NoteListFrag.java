package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.bean.gson.GsonNoteBean;
import com.summer.desktop.bean.gson.Note;
import com.summer.desktop.imp.Onfinish;
import com.summer.desktop.util.FragList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class NoteListFrag extends BaseFrag implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.recycle)
    RecyclerView recyclerView;

    @BindView(R.id.notelist)
    View listView;


    Random random = new Random();

    ArrayList<Note> notes = new ArrayList<>();

    Note parentNote;

    Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_news,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new LinearItemDecoration(getContext(),1));
        listView.setOnLongClickListener(this);
        parentNote = (Note) getArguments().getSerializable("data");
        getData();
    }

    public void getData() {
        if (parentNote != null) {
            BmobQuery<Note> query = new BmobQuery<Note>();
            //查询playerName叫“比目”的数据
            query.addWhereEqualTo("parentId", parentNote.getObjectId());
            //返回50条数据，如果不加上这条语句，默认返回10条数据
            query.setLimit(50);
            //执行查询方法
            query.findObjects(new FindListener<Note>() {
                @Override
                public void done(List<Note> object, BmobException e) {
                    notes = (ArrayList<Note>) object;
                    recyclerView.setAdapter(new NewsAdapter(getContext(), notes));
                    ((NewsAdapter) recyclerView.getAdapter()).setOnClickListener(NoteListFrag.this);
                    ((NewsAdapter) recyclerView.getAdapter()).setOnLongClickListener(NoteListFrag.this);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        NoteListssFrag noteListssFrag = new NoteListssFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", notes);
        bundle.putInt("position", (Integer) v.getTag(R.id.position));
        noteListssFrag.setArguments(bundle);
        FragList.getInstance().add(getActivity(), noteListssFrag);


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
                int index = (int) o;
                final Note[] note = new Note[1];
                switch (index) {
                    case 0:
                        //note
                        note[0] = new Note(Note.NOTE, "新建笔记" + random.nextFloat() * 100);
                        note[0].setData(gson.toJson(new GsonNoteBean()));
                        note[0].setParentId(parentNote.getObjectId());
                        note[0].save(new SaveListener<String>() {

                            @Override
                            public void done(String objectId, BmobException e) {
                                getData();
                            }
                        });
                        break;
                    case 1:
                        note[0] = new Note(Note.NOTEBOOK, "新建笔记本" + random.nextFloat() * 100);
                        note[0].setParentId(parentNote.getObjectId());
                        note[0].save(new SaveListener<String>() {

                            @Override
                            public void done(String objectId, BmobException e) {
                                getData();
                            }
                        });
                        //notebook
                        break;
                    case 2:
                        //delete
                        if (v.getTag(R.id.data) == null) {
                            break;
                        }
                        note[0] = new Note();
                        note[0].setObjectId(((Note) (v.getTag(R.id.data))).getObjectId());
                        note[0].delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                getData();
                            }
                        });
                        break;
                    case 3:
                        //rename
                        if (v.getTag(R.id.data) == null) {
                            break;
                        }
                        RenameFrag renameFrag = new RenameFrag();
                        FragList.getInstance().add(getActivity(), renameFrag);
                        renameFrag.setOnfinish(new Onfinish() {
                            @Override
                            public void finished(Object o) {
                                String s = (String) o;
                                if (s.equals("")) {
                                    return;
                                }
                                note[0] = new Note();
                                note[0].setObjectId(((Note) (v.getTag(R.id.data))).getObjectId());
                                note[0].setName(s);
                                note[0].update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        getData();
                                    }
                                });
                            }
                        });
                        break;
                }
                if (index > 2) {

                } else {

                }
            }
        });
        return true;
    }

    public static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> implements View.OnClickListener, View.OnLongClickListener {

        Context context;

        View.OnClickListener onClickListener;

        View.OnLongClickListener onLongClickListener;

        Random random = new Random();

        ArrayList<Note> notes;

        public NewsAdapter(Context context,ArrayList<Note> notes){
            this.context = context;
            this.notes = notes;
        }

        @Override
        public NewsAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_news,parent,false));
        }

        @Override
        public void onBindViewHolder(NewsAdapter.NewsHolder holder, int position) {
            holder.itemView.setOnClickListener(this);
            holder.itemView.setOnLongClickListener(this);
            holder.itemView.setTag(R.id.position, position);
            holder.itemView.setTag(R.id.data,notes.get(position));
            holder.textView.setText(notes.get(position).getType() + "  " + notes.get(position).getName());
            holder.dateTV.setText(notes.get(position).getUpdatedAt() + "  " + notes.get(position).getCreatedAt());
        }

        @Override
        public int getItemCount() {
            return notes==null?0:notes.size();
        }

        @Override
        public void onClick(View v) {
            if(onClickListener!=null){
                onClickListener.onClick(v);
            }
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
            this.onLongClickListener = onLongClickListener;
        }

        @Override
        public boolean onLongClick(View v) {
            if (onLongClickListener != null) {
                onLongClickListener.onLongClick(v);
            }
            return true;
        }

        public static class NewsHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.textView)
            TextView textView;

            @BindView(R.id.date)
            TextView dateTV;

            public NewsHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }
}
