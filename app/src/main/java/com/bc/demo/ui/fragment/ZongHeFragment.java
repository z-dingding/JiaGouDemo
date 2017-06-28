package com.bc.demo.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bc.demo.adapter.ZongHeAdapter;
import com.bc.demo.adapter.base.DongTanBaseAdapter;
import com.bc.demo.bean.DongTan;
import com.bc.demo.ui.fragment.base.ZongHeBaseFragment;

import java.util.ArrayList;

/**
 * Created by Ding on 2017/5/25.
 */
public class ZongHeFragment extends ZongHeBaseFragment {

    ArrayList<DongTan> list;
    ZongHeAdapter zongHeAdapter;
     ArrayList<DongTan> refresh_data;

   Handler handler =new Handler(){
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what){
               case 0x111:
                   getRefershData();
                   list.addAll(refresh_data);
                   zongHeAdapter.setDatas(list);
                   zongHeAdapter.notifyDataSetChanged();
                   refresh_data.clear();
                   ZongHeBaseFragment.mSwipeRefreshLayout.setRefreshing(false);
               break;
           }
       }
   };


    @Override
    public DongTanBaseAdapter getAdapter() {
        zongHeAdapter=new ZongHeAdapter(getActivity());
        return zongHeAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onRefresh() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               handler.sendEmptyMessage(0x111);
           }
       },3000);
    }

    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();
        refresh_data=new ArrayList<DongTan>();
        for(int i=0;i<10;i++){
            list.add(new DongTan("标题","这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容","2017/05/22","dingDing"));
        }
        zongHeAdapter.setDatas(list);
    }


    private void getRefershData(){
        DongTan tan =new DongTan("新标题","这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容","2017/05/22","dingDing");
         for(int i=0;i<3;i++){
             refresh_data.add(tan);
         }
    }
}
