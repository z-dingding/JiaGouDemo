package com.bc.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.bc.demo.AppContext;
import com.bc.demo.AppManager;
import com.bc.demo.R;
import com.bc.demo.interf.BaseActivityInterf;

import butterknife.ButterKnife;

/**
 * Created by Ding on 2017/5/21.
 */
public abstract  class BaseActivity extends AppCompatActivity implements BaseActivityInterf{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AppContext.getSwitchModel()){
            setTheme(R.style.AppTheme_Night);
        }else{
            setTheme(R.style.AppTheme_Light);
        }
        AppManager.getInstance().addActivity(this);
        if(setLayoutId() != 0){
            setContentView(setLayoutId());
        }
        ButterKnife.bind(this);
        beforeInitView(savedInstanceState);
        initView();
        initData();
    }

    protected  void beforeInitView( Bundle savedInstanceState){

    };

    protected int setLayoutId(){
       return 0;
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
