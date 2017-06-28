package com.bc.demo.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.bc.demo.R;
import com.bc.demo.adapter.base.DongTanBaseAdapter;
import com.bc.demo.widget.MyGridView;
import com.bc.demo.widget.MyListView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ding on 2017/5/22.
 */
public abstract class DongTanBaseFragment<T> extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    View mRoot;
    @Bind(R.id.dt_fm_GV)
    MyGridView dtFmGV;
    @Bind(R.id.dt_fm_LV)
    MyListView dtFmLV;

    public  static SwipeRefreshLayout dtFmSwiperRefershLayout;

    DongTanBaseAdapter<T> dongTanBaseAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_dongtan, container, false);
            ButterKnife.bind(this, mRoot);
            initView(mRoot);
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if(parent != null){
                parent.removeView(mRoot);
            }

        }

        return mRoot;
    }

    protected void initView(View mRoot) {
        dtFmSwiperRefershLayout= (SwipeRefreshLayout) mRoot.findViewById(R.id.dt_fm_swiperRefershLayout);

        //设置拉进度条的颜色主题
        dtFmSwiperRefershLayout.setColorSchemeResources(R.color.colorAccent_Light);
        //设置监听
        dtFmSwiperRefershLayout.setOnRefreshListener(this);
        dtFmGV.setOnItemClickListener(this);
        dtFmLV.setOnItemClickListener(this);
    }

    protected void initData() {
        dongTanBaseAdapter=getDongTanBaseAdapter();
        dtFmGV.setAdapter(dongTanBaseAdapter);
        dtFmLV.setAdapter(dongTanBaseAdapter);
    }

    protected abstract DongTanBaseAdapter getDongTanBaseAdapter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
