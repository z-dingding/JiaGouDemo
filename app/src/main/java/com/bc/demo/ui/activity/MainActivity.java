package com.bc.demo.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.bc.demo.R;
import com.bc.demo.base.BaseActivity;
import com.bc.demo.bean.Search_ENUM;
import com.bc.demo.interf.BaseActivityInterf;
import com.bc.demo.utils.UIHelper;
import com.bc.demo.widget.MyFragmentTabHost;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainActivity extends BaseActivity implements BaseActivityInterf, NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.main_fmContent)
    FrameLayout mainFmContent;
    @Bind(R.id.main_fmTabHost)
    MyFragmentTabHost mainFmTabHost;
    @Bind(R.id.main_fmTabHost_iv)
    ImageView mainFmTabHostIv;
    @Bind(R.id.main_mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.main_navView)
    NavigationView mNavigattionView;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //有两种监听方式
        /*mainToolbar.setNavigationIcon(R.mipmap.ic_navigation_drawer);
        TextView toolBar_title= (TextView) mainToolbar.findViewById(R.id.toolbar_title);
        //添加标题
        toolBar_title.setText("首页");
        //toolbar默认标题居左，故需要自定义TextView ,;
        mainToolbar.setTitle("");*/


        mainToolbar.setTitle("");
        setSupportActionBar(mainToolbar);

        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, mainToolbar,R.string.open_drawerLayout,R.string.close_drawerLayout );
        //增加图标
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
        //设置监听
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        mNavigattionView.setNavigationItemSelectedListener(this);
        mainFmTabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.main_fmContent);
        initTabHost();
        mainFmTabHost.setCurrentTab(0);
    }

    private void initTabHost() {
        //得到枚举的集合
        MainTab[] mainTab = MainTab.values();
        for (int i = 0; i < mainTab.length; i++) {
            MainTab tab = mainTab[i];
            TabHost.TabSpec tabSpec = mainFmTabHost.newTabSpec(getString(tab.getNameIdRes()));
            View view = View.inflate(MainActivity.this, R.layout.tab_indicator, null);
            ImageView tab_iv = (ImageView) view.findViewById(R.id.tab_icon);
            TextView tab_tv = (TextView) view.findViewById(R.id.tab_title);
            tab_iv.setImageResource(tab.getIcomIdRes());
            tab_tv.setText(tab.getNameIdRes());
            if (i == 2) {
                view.setVisibility(View.INVISIBLE);
                mainFmTabHost.setNoTabChangedTag(getString(tab.getNameIdRes()));
            }
            tabSpec.setIndicator(view);
            mainFmTabHost.addTab(tabSpec, tab.getClzz(), null);
        }
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.main_fmTabHost_iv)
    public void onClick() {
        Toast.makeText(MainActivity.this, "您点击了更多", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_boolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_search:
                UIHelper.goSearchActivity(this, Search_ENUM.SEARCH);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //注意
            super.onBackPressed();
        }
    }

    //NavigationItemSelected的监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home :
                Toast.makeText(MainActivity.this,"您点击了首页",Toast.LENGTH_LONG).show();
            break;
            case R.id.nav_cache :
                Toast.makeText(MainActivity.this,"您点击了缓存",Toast.LENGTH_LONG).show();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }





}
