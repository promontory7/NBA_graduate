package com.me.zhuchudong.nba.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.me.zhuchudong.nba.app.AppService;

import butterknife.ButterKnife;

/**
 * Created by SilenceDut on 2015/11/28.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initViews();
    protected abstract int getContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        AppService.getInstance().addCompositeSub(getTaskId());
        AppService.getInstance().getBus().register(this);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppService.getInstance().removeCompositeSub(getTaskId());
        AppService.getInstance().getBus().unregister(this);
    }
}
