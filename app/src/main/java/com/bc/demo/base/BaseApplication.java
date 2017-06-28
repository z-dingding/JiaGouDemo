package com.bc.demo.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

/**
 * Created by Ding on 2017/5/21.
 */

public class BaseApplication extends Application {

    //sharedPreference的名字
    private static String  sharedFName="shardName";
    public static Context mContext;
    public static Resources mResources;

    @Override
    public void onCreate() {
        super.onCreate();
        //整个应用程序的Context
        mContext=getApplicationContext();
        mResources=mContext.getResources();
    }

    public static SharedPreferences getSharedPreference(){
        return getAppContext().getSharedPreferences(sharedFName,Context.MODE_PRIVATE);
    }


    public static void setSharedPreferences(String key,boolean value){
        SharedPreferences.Editor sharedF_edit=getSharedPreference().edit();
        sharedF_edit.putBoolean(key,value);
        sharedF_edit.apply();
    }

    public static synchronized BaseApplication getAppContext(){
        return (BaseApplication) mContext;
    }


    public static Resources getAppResources(){
        return mResources;
    }



}
