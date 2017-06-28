package com.bc.demo.ui.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.bc.demo.adapter.DongTanAdapter;
import com.bc.demo.adapter.base.DongTanBaseAdapter;
import com.bc.demo.bean.DongTan;
import com.bc.demo.ui.fragment.base.DongTanBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ding on 2017/5/22.
 */

public class DongTanFragment extends DongTanBaseFragment {
    ArrayList<DongTan> list;
    DongTanAdapter dongTanAdapter;
    @Override
    protected DongTanBaseAdapter getDongTanBaseAdapter() {
        dongTanAdapter=new DongTanAdapter(getActivity());
        return dongTanAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<3;i++){
                    list.add(new DongTan("新标题","这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容","2017/05/22","dingDing"));
                }
                dongTanAdapter.notifyDataSetChanged();
                DongTanBaseFragment.dtFmSwiperRefershLayout.setRefreshing(false);
            }
        },3000);
    }

    @Override
    protected void initData() {
        super.initData();
       list = new ArrayList<>();
        for(int i=0;i<10;i++){
          list.add(new DongTan("标题","这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容","2017/05/22","dingDing"));
        }
        dongTanAdapter.setDatas(list);
    }
}
