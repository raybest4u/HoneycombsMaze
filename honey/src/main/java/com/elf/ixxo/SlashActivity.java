package com.elf.ixxo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.elf.ixxo.view.shimmer.Shimmer;
import com.elf.ixxo.view.shimmer.ShimmerTextView;


public class SlashActivity extends Activity {
    private Shimmer shimmer;
    private ShimmerTextView tv;
    private long mStartTime;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStartTime = System.currentTimeMillis();// 记录开始时间，
        mHandler = new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        tv = findViewById(R.id.shimmer_tv);
        shimmer = new Shimmer();
        shimmer.start(tv);
    }

    Runnable splashGone = new Runnable() {

        @Override
        public void run() {
            Animation anim = AnimationUtils.loadAnimation(SlashActivity.this, R.anim.push_right_out);
            anim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (shimmer != null && shimmer.isAnimating()) {
                        shimmer.cancel();
                    }
                    tv.setVisibility(View.GONE);
                    startActivity(new Intent(SlashActivity.this,MazeActivity.class));
                    finish();
                }
            });

            tv.startAnimation(anim);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        invisibleSplash();
    }
    private static final int SHOW_TIME_MIN = 3000;// 最小显示时间
    private void invisibleSplash() {
        long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
        if (loadingTime < SHOW_TIME_MIN) {// 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
            mHandler.postDelayed(splashGone, SHOW_TIME_MIN - loadingTime);
        } else {
            mHandler.post(splashGone);
        }
    }

}
