package com.me.zhuchudong.nba.ui.adapter.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.me.zhuchudong.nba.ui.fragment.playersort.BarFragment;

import java.util.List;

public class StatPageAdapter extends FragmentStatePagerAdapter {

    private List<BarFragment> mBarFragments;

    public StatPageAdapter(FragmentManager fm, List<BarFragment> barFragments) {
        super(fm);
        this.mBarFragments=barFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mBarFragments.get(position);
    }

    @Override
    public int getCount() {
        return mBarFragments.size();
    }
}
