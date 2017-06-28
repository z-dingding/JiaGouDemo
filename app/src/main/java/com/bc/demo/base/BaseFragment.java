package com.bc.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.demo.interf.BaseFragmentInterf;

import java.util.zip.Inflater;

/**
 * Created by Ding on 2017/5/21.
 */
public class BaseFragment extends Fragment implements BaseFragmentInterf{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view=super.onCreateView(inflater, container, savedInstanceState);
       initView(view);
       return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }




    @Override
    public void initView(View view) {

    }



    @Override
    public void initData() {

    }
}
