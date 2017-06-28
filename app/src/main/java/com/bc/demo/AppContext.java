package com.bc.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.bc.demo.base.BaseApplication;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Ding on 2017/5/24.
 */
public class AppContext extends BaseApplication {

    private static AppContext appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        init();
    }


    private void init() {
        //网络请求初始化， 部分类库初始化
        //Log日志打印控制
        //自动登录相关
        Fresco.initialize(appContext);
    }

    public static void setSwitchModel(boolean model){
        setSharedPreferences(AppConfig.sharedF_Key,model);
    }
    /**默认白天模式*/
    public static boolean  getSwitchModel(){
          return getSharedPreference().getBoolean(AppConfig.sharedF_Key,false);
    }

}
