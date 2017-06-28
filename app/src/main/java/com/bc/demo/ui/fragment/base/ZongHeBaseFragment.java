package com.bc.demo.ui.fragment.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bc.demo.R;
import com.bc.demo.adapter.base.DongTanBaseAdapter;
import com.bc.demo.bean.DongTan;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ding on 2017/5/25.
 */

public abstract class ZongHeBaseFragment<T> extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener  {
    View mView;
    ListView mListView;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    //万能适配器
    DongTanBaseAdapter<T> dongTanBaseAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(mView == null){
           mView=inflater.inflate(R.layout.fragment_zonghe,container,false);
           ButterKnife.bind(this,mView);//区分view,getActivity,this区别
           initView(mView);
           initData();
       }

        return mView;
    }

    protected  void initData(){
        dongTanBaseAdapter=getAdapter();
        mListView.setAdapter(dongTanBaseAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    };



    protected  void initView(View view){
        mListView= (ListView) view.findViewById(R.id.zh_fm_listView);
        LayoutAnimationController lac =new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.layoutanimation));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        mListView.setLayoutAnimation(lac);
        mListView.startLayoutAnimation();
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.zh_fm_swiperRefershLayout);
        //设置拉进度条的颜色主题
        mSwipeRefreshLayout.setColorSchemeResources(R.color.swiperefresh_color1,R.color.swiperefresh_color2,R.color.swiperefresh_color3,R.color.swiperefresh_color4);
        //设置监听
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);

    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract  DongTanBaseAdapter<T> getAdapter();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static SwipeRefreshLayout getmSwipeRefreshLayout(){
        if(mSwipeRefreshLayout != null){
            return mSwipeRefreshLayout;
        }
       return null;
    }







}
