package com.bc.demo.adapter;

import android.content.Context;

import com.bc.demo.R;
import com.bc.demo.adapter.base.DongTanBaseAdapter;
import com.bc.demo.bean.DongTan;
import com.bc.demo.bean.ViewHolder;

/**
 * Created by Ding on 2017/5/22.
 */
public class DongTanAdapter extends DongTanBaseAdapter<DongTan> {
    private Context mContext;

    public DongTanAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    protected void getConvertView(ViewHolder viewHolder, DongTan item, int position) {
        viewHolder.setText(R.id.tv_title,item.getTitlle());
        viewHolder.setText(R.id.tv_description,item.getContent());
        viewHolder.setText(R.id.tv_time,item.getTime());
        viewHolder.setText(R.id.tv_comment_count,item.getAuthor());


    }

    @Override
    protected int getLayoutId(int position, DongTan item) {
        return R.layout.item_list_dongtan;
    }

    @Override
    protected Context getContext() {
        return mContext;
    }


}
