package com.me.zhuchudong.nba.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.me.zhuchudong.nba.R;
import com.me.zhuchudong.nba.event.Event;
import com.me.zhuchudong.nba.ui.wigdets.RevealBackgroundView;

import butterknife.Bind;

public class AboutActivity extends SwipeBackActivity implements RevealBackgroundView.OnStateChangeListener {


    @Bind(R.id.revealBackgroundView)
    RevealBackgroundView mRevealBackgroundView;
    @Bind(R.id.aboutView)
    View aboutView;

    public static void navigateFrom(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initViews() {
        super.initViews();
        setTitle();
        setupRevealBackground();
    }

    @Override
    void setTitle() {
        mToolBar.setTitle(getResources().getString(R.string.about));

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    private void setupRevealBackground() {
        mRevealBackgroundView.setOnStateChangeListener(this);
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        final int[] startingLocation = {screenWidth, 0};
        mRevealBackgroundView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mRevealBackgroundView.getViewTreeObserver().removeOnPreDrawListener(this);
                mRevealBackgroundView.startFromLocation(startingLocation);
                return true;
            }
        });

    }

    @Override
    public void onStateChange(int state) {

        if (RevealBackgroundView.STATE_FINISHED == state) {
            aboutView.setVisibility(View.VISIBLE);
        } else {
            aboutView.setVisibility(View.INVISIBLE);
        }
    }


    public void onEventMainThread(Event event) {

    }

}
