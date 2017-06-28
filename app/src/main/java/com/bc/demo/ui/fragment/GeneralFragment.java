package com.bc.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.demo.R;
import com.bc.demo.adapter.ZongHeAdapter;
import com.bc.demo.adapter.base.ViewPagerFragmentAdapter;
import com.bc.demo.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ding on 2017/5/25.
 */
public class GeneralFragment extends BaseFragment{


    View mView;
    TabLayout mTablyout;
    ViewPager mViewpager;
    ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    String [] titles;
    ArrayList<Fragment> alist_fragment;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_general, container,false);
            mViewpager= (ViewPager) mView.findViewById(R.id.fm_general_viewpager);
            mTablyout= (TabLayout) mView.findViewById(R.id.fm_general_tablyout);
            ButterKnife.bind(this, mView);
            initView(mView);
        }
        return mView;
    }

    @Override
    public void initView(View view) {
        alist_fragment=new ArrayList<Fragment>();
        titles=getResources().getStringArray(R.array.news_titles);
        addTab();
        addFragment();
        viewPagerFragmentAdapter=new ViewPagerFragmentAdapter(getChildFragmentManager(),alist_fragment,titles);
        // if(mViewpager.getAdapter() == null){ } //不太明白
        mViewpager.setAdapter(viewPagerFragmentAdapter);
        mTablyout.setupWithViewPager(mViewpager);
        mTablyout.setTabsFromPagerAdapter(viewPagerFragmentAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void addTab(){
        for(int i=0;i<titles.length;i++){
          TabLayout.Tab tab =mTablyout.newTab();
            tab.setText(titles[i]);
            mTablyout.addTab(tab);
        }
    }


    private void addFragment(){
        for(int i=0;i<titles.length;i++){
            ZongHeFragment fragment =new ZongHeFragment();
          /* Bundle bundle =new Bundle();
            bundle.putString(i+"",i+"");
           Fragment  fragment = Fragment.instantiate(getActivity(), ZongHeFragment.class.getName(), bundle);*/
            alist_fragment.add(fragment) ;
        }
    }


}



