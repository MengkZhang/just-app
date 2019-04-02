package com.mengk.justapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mengk.justapp.module.GlobalThreadPool;
import com.mengk.justapp.module.PreferencesUtils;

/**
 * 闪屏页
 */
public class SplashActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isFinishing()) {
                return;
            }
            //进入到主页
            enterHomeOrOther();
        }
    };

    /**
     * 进入到主页或其他页
     */
    private void enterHomeOrOther() {
        try {
            GlobalThreadPool.getInstance().getGlobalExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    go2main();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            go2main();
        }
    }

    /**
     * 到主页
     * 如果是第一次进入APP 则需要先到引导页
     * 如果不是第一次进入APP 则直接到主页
     */
    private void go2main() {
        boolean isFirstRun = PreferencesUtils.getBoolean(this, "IS_FIRST_RUN", true);
        Intent intent = null;
        if (isFirstRun) {
            //是第一次进入app 到引导页
//            intent = new Intent(SplashActivity.this, GuideActivity.class);
        } else {
            //不是第一次将进入app 到主页
//            intent = new Intent(SplashActivity.this, MainActivity.class);
        }
        intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //把背景图设置为空避免图片会一直存在于内存中
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //延迟两秒到主页
        mHandler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
