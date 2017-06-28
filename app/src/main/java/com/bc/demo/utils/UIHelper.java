package com.bc.demo.utils;

import android.content.Context;
import android.content.Intent;

import com.bc.demo.bean.Search_ENUM;
import com.bc.demo.ui.activity.SearchActivity;

/**
 * Created by Ding on 2017/5/24.
 */
public class UIHelper {
    public static void goSearchActivity(Context ct, Search_ENUM search_enum){
        Intent intent =new Intent(ct, SearchActivity.class);
        intent.putExtra("VALUE",search_enum.getTitleContentId());
        int a =search_enum.getTitleContentId();
        ct.startActivity(intent);
    }
}
