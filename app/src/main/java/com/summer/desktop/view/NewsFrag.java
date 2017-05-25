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

import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.util.FragList;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFrag extends BaseFrag implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.recycle)
    RecyclerView recyclerView;


    Random random = new Random();

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
        recyclerView.setAdapter(new NewsAdapter(getContext()));
        ((NewsAdapter)recyclerView.getAdapter()).setOnClickListener(this);
        ((NewsAdapter) recyclerView.getAdapter()).setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (random.nextBoolean()) {
            TxtFrags txtFarg = new TxtFrags();
            FragList.getInstance().add(getActivity(), txtFarg);
            return;
        }
        MainFrag mainFrag = new MainFrag();
        FragList.getInstance().add(getActivity(),mainFrag);
    }

    @Override
    public boolean onLongClick(View v) {
        BoomFrag boomFrag = new BoomFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, boomFrag);
        transaction.commit();
        return true;
    }

    public static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> implements View.OnClickListener, View.OnLongClickListener {

        Context context;

        View.OnClickListener onClickListener;

        View.OnLongClickListener onLongClickListener;

        Random random = new Random();

        public NewsAdapter(Context context){
            this.context = context;
        }

        @Override
        public NewsAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_news,parent,false));
        }

        @Override
        public void onBindViewHolder(NewsAdapter.NewsHolder holder, int position) {
            holder.itemView.setOnClickListener(this);
            holder.itemView.setOnLongClickListener(this);
            holder.itemView.setTag(R.id.data,position);
            holder.textView.setText(holder.textView.getText().toString()+1000000*random.nextFloat());
        }

        @Override
        public int getItemCount() {
            return 30;
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


            public NewsHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }
}
