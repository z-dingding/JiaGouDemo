package com.bc.demo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.bc.demo.R;

/**
 * Created by Ding on 2017/5/20.
 */

public class WelcomeActitity extends AppCompatActivity {

    LinearLayout linearLayout_bg;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.welcom_layout,null);
        setContentView(view);
        initView();
        initData(view);
    }

    private void initData(View view) {

        ObjectAnimator objectAnim1 = ObjectAnimator.ofFloat(view,"alpha",0.2f,1.0f);
        ObjectAnimator objectAnim2 = ObjectAnimator.ofFloat(view,"TranslationX",0,1000);
        AnimatorSet set =new AnimatorSet();
        set.playSequentially(objectAnim1,objectAnim2);
        set.setDuration(2500);
        set.start();

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent  intent=new Intent(WelcomeActitity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }

    private void initView() {
        linearLayout_bg= (LinearLayout) findViewById(R.id.ll_welcome_bg);
    }
}
