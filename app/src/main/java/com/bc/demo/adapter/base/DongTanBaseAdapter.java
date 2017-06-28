package com.bc.demo.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bc.demo.bean.ViewHolder;
import java.util.ArrayList;

/**
 * Created by Ding on 2017/5/22.
 */
public  abstract class DongTanBaseAdapter<T> extends BaseAdapter{



   ArrayList<T> mdatas;
    @Override
    public int getCount() {
        return  mdatas != null ? mdatas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        if(i>=0 && i <= mdatas.size()){
           return mdatas.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        T item= (T) getItem(i);
        int layoutId=getLayoutId(i, item);
        ViewHolder mViewHolder =ViewHolder.getViewHolder(getContext(),view,i,layoutId);
        getConvertView(mViewHolder,item,i);

        return mViewHolder.getmConvertView() ;
    }


    protected abstract void  getConvertView(ViewHolder viewHolder,T item, int position);
    protected abstract int getLayoutId( int position, T item);
    protected abstract Context getContext();


    public  void setDatas( ArrayList<T> mdatas){
        this.mdatas = mdatas;
    }
}
