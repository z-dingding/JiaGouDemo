package com.bc.demo.ui.activity;


import com.bc.demo.R;
import com.bc.demo.ui.fragment.DongTanFragment;
import com.bc.demo.ui.fragment.GeneralFragment;
import com.bc.demo.ui.fragment.NewsFragment;
import com.bc.demo.ui.fragment.ZongHeFragment;

public enum MainTab {

    NEWS(0, R.string.news, R.drawable.table_icon_new_selector, ZongHeFragment.class),
    TWEET(1, R.string.tweet, R.drawable.table_icon_tweet_selector, DongTanFragment.class),
    QUICK(2, R.string.quick, R.drawable.table_icon_new_selector, GeneralFragment.class),
    EXPLORE(3, R.string.explore, R.drawable.table_icon_explore_selector, GeneralFragment.class),
    ME(4, R.string.me, R.drawable.table_icon_me_selector, NewsFragment.class);

    public int getIdx() {
        return idx;
    }

    public int getNameIdRes() {
        return nameIdRes;
    }

    public int getIcomIdRes() {
        return icomIdRes;
    }

    private int idx;
    private int nameIdRes;
    private int icomIdRes;
    private Class<?> clzz;

    MainTab(int idx, int nameIdRes, int icomIdRes, Class<?> clz) {
        this.idx = idx;
        this.nameIdRes = nameIdRes;
        this.icomIdRes = icomIdRes;
        this.clzz = clz;
    }

    public Class<?> getClzz() {
        return clzz;
    }

}
