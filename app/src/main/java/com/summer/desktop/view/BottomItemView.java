package com.summer.desktop.view;

//by summer on 2017-05-23.

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;

public class BottomItemView extends ViewPager{



    public BottomItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true,new MyPagerTransForm());
    }

    public static class MyPagerTransForm implements PageTransformer {

        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            if(position>-1 && position<=1){
                float y = (float) (0.5f+0.5f*Math.sin((position+0.1f)*Math.PI));
                view.setScaleX(y);
                view.setScaleY(y);
            }
        }
    }

    public static class MenuItemFrag extends BaseFrag {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.frag_menu,null);
        }
    }

    public static class MenuAdapter extends PagerAdapter implements OnClickListener{

        Context context;

        OnClickListener onClickListener;

        public MenuAdapter(Context context){
            this.context = context;
        }


        @Override
        public int getCount() {
            return Integer.MAX_VALUE>>2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.frag_menu,container,false);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(position+"");
            container.addView(view);
            view.setOnClickListener(this);
            view.setTag(R.id.data,position);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public float getPageWidth(int position) {
            return 0.2f;
        }


        @Override
        public void onClick(View v) {
            if(onClickListener!=null){
                onClickListener.onClick(v);
            }
        }

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }
}
