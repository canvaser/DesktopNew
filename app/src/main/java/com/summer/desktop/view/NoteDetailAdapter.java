package com.summer.desktop.view;

//by summer on 2017-05-27.

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.summer.desktop.R;
import com.summer.desktop.bean.gson.ImageNote;
import com.summer.desktop.bean.gson.NoteDetail;
import com.summer.desktop.bean.gson.TxtNote;
import com.summer.desktop.util.GlideApp;
import com.summer.desktop.util.ScreenUtil;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailAdapter extends RecyclerView.Adapter implements View.OnLongClickListener {

    Context context;

    ArrayList<NoteDetail> data;

    Gson gson = new Gson();

    View.OnLongClickListener onLongClickListener;

    public NoteDetailAdapter(Context context, ArrayList<NoteDetail> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        switch (data.get(position).getType()) {
            case NoteDetail.IMAGE:
                return 0;
            default:
                return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                viewHolder = new ImageHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
                break;
            case 1:
                viewHolder = new TxtHolder(LayoutInflater.from(context).inflate(R.layout.item_txt, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                ImageHolder imageHolder = (ImageHolder) holder;
                ImageNote imageNote = gson.fromJson(data.get(position).getData(), ImageNote.class);
                imageHolder.itemView.getLayoutParams().width = (int) ScreenUtil.w;
                imageHolder.itemView.getLayoutParams().height = (int) (ScreenUtil.w * imageNote.getHeight() / imageNote.getWidth());
                imageHolder.itemView.requestLayout();
                if (imageNote.getLocalSrc() != null && imageNote.getLocalSrc().startsWith("file://")) {
                    File file = new File(imageNote.getLocalSrc().substring("file://".length(), imageNote.getLocalSrc().length()));
                    if (file.exists()) {
                        //ImageLoader.getInstance().displayImage(imageNote.getLocalSrc(),imageHolder.imageView);
                        GlideApp.with(context).asBitmap().error(R.drawable.app).placeholder(R.drawable.app).load(imageNote.getLocalSrc()).encodeQuality(10).into(imageHolder.imageView);
                    } else {
                        GlideApp.with(context).asBitmap().error(R.drawable.app).placeholder(R.drawable.app).diskCacheStrategy(DiskCacheStrategy.ALL).encodeQuality(10).load(imageNote.getSrc()).into(imageHolder.imageView);
                    }
                } else {
                    //ImageLoader.getInstance().displayImage(imageNote.getSrc(),imageHolder.imageView);
                    GlideApp.with(context).asBitmap().error(R.drawable.app).placeholder(R.drawable.app).diskCacheStrategy(DiskCacheStrategy.ALL).encodeQuality(10).load(imageNote.getSrc()).into(imageHolder.imageView);
                }
                imageHolder.itemView.setTag(R.id.position, position);
                imageHolder.itemView.setTag(R.id.data, data.get(position));
                imageHolder.itemView.setOnLongClickListener(this);
                break;
            case 1:
                final TxtHolder txtHolder = (TxtHolder) holder;
                final TxtNote txtNote = gson.fromJson(data.get(position).getData(), TxtNote.class);
                txtHolder.textView.setText(txtNote.getTxt());
                txtHolder.itemView.setTag(R.id.position, position);
                txtHolder.itemView.setTag(R.id.data, data.get(position));
                txtHolder.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            txtNote.setTxt(txtHolder.textView.getText().toString());
                            data.get(position).setData(gson.toJson(txtNote));
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean onLongClick(View v) {
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(v);
        }
        return true;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TxtHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt)
        EditText textView;

        public TxtHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
