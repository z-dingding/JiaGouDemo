package com.bc.demo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.demo.AppManager;
import com.bc.demo.R;
import com.bc.demo.base.BaseActivity;
import com.bc.demo.bean.Search_ENUM;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ding on 2017/5/24.
 */

public class SearchActivity extends BaseActivity {
    private static final String TAG ="SearchActivity";
    @Bind(R.id.search_toolbar)
    Toolbar searchToolbar;
    @Bind(R.id.search_button)
    Button searchButton;
    @Bind(R.id.activity_root)
    CoordinatorLayout activityRoot;
    private String titleContent;
    private int titleContentId = -1;
    @Bind(R.id.search_imgGlide)
    ImageView show_imgGlide;
    @Bind(R.id.search_imgFrisco)
    SimpleDraweeView show_imgFrisco;
    ArrayList<SampleModel> data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void beforeInitView(Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
        Intent intent = getIntent();
        if (titleContentId == -1) {
           titleContentId = intent.getIntExtra("VALUE", 0);//缺省值
        }
     if (titleContentId != -1) {
            Search_ENUM search_enum = Search_ENUM.getSearch_EnumBySelf(titleContentId);
            if(search_enum != null){
                titleContent = getString(search_enum.getTitleContent());
            }
       }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.search_activity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView() {
        makeSampleModelData();
        searchToolbar.setNavigationIcon(R.mipmap.image_default_back);
        searchToolbar.setTitle("");
        TextView search_title= (TextView) searchToolbar.findViewById(R.id.search_title);
        search_title.setText(titleContent);
        setSupportActionBar(searchToolbar);
    }

    @Override
    public void initData() {
        //使用fresco默认加载此图片
        Uri uri = Uri.parse("http://f.hiphotos.baidu.com/image/pic/item/b151f8198618367ac7d2a1e92b738bd4b31ce5af.jpg");
        show_imgFrisco.setImageURI(uri);
        //使用Glide加载默认图片
        Glide.with(this).load("http://f.hiphotos.baidu.com/image/pic/item/b151f8198618367ac7d2a1e92b738bd4b31ce5af.jpg").into(show_imgGlide);
    }

    @OnClick({R.id.search_button})
    public void onClick(View view){
            switch (view.getId()){
                case R.id.search_button:
                    changeImageView(data);
                break;
            }
    }

    private void changeImageView(List<SampleModel> data){
        rx.Observable.just(data).subscribeOn(Schedulers.newThread()).map(new Func1<List<SampleModel>, SampleModel>() {
            @Override
            public SampleModel call(List<SampleModel> sampleModels) {
                Log.d(TAG, "第一个线程"+Thread.currentThread().getId());
                int randomNum=new Random().nextInt(4);
                return sampleModels.get(randomNum);
            }


        }).map(new Func1<SampleModel, String>() {

            @Override
            public String call(SampleModel sampleModel) {
                Log.d(TAG, "第二个线程"+Thread.currentThread().getId());
                return sampleModel.url;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "第三个线程"+Thread.currentThread().getId());
                show_imgFrisco.setImageURI( Uri.parse(s));
                Glide.with(SearchActivity.this).load(s).into(show_imgGlide);
            }
        });
    }

    //数据初始化
    private void makeSampleModelData() {
        data = new ArrayList<>();
        data.add(new SampleModel("Tiffany one", "http://hiphotos.baidu.com/zhixin/abpic/item/4651a712c8fcc3cea97dbce49045d688d53f206c.jpg"));
        data.add(new SampleModel("Tiffany two", "http://imgsrc.baidu.com/forum/w%3D580/sign=45abdcd6530fd9f9a0175561152cd42b/cb2bcdef76094b3602fce847a1cc7cd98c109db1.jpg"));
        data.add(new SampleModel("Tiffany three", "http://img5q.duitang.com/uploads/item/201410/22/20141022214043_5EEKH.thumb.224_0.jpeg"));
        data.add(new SampleModel("Tiffany four", "http://img5.duitang.com/uploads/item/201512/08/20151208163159_HGEM2.thumb.224_0.png"));
        data.add(new SampleModel("Tiffany five", "http://img4.duitang.com/uploads/item/201510/29/20151029224537_ijEKF.thumb.224_0.jpeg"));
    }


    class SampleModel {
        public String name;
        public String url;

        public SampleModel(String name, String url) {
            this.name = name;
            this.url = url;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AppManager.getInstance().finishActivity(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}
