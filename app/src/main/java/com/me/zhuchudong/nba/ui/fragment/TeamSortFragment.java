package com.me.zhuchudong.nba.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.me.zhuchudong.nba.R;
import com.me.zhuchudong.nba.app.AppService;
import com.me.zhuchudong.nba.data.Constant;
import com.me.zhuchudong.nba.event.TeamSortEvent;
import com.me.zhuchudong.nba.model.Teams;
import com.me.zhuchudong.nba.ui.adapter.RecycleAdapter.TeamSortAdapter;
import com.me.zhuchudong.nba.ui.fragment.base.SwipeRefreshBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by SilenceDut on 2015/12/12.
 */
public class TeamSortFragment extends SwipeRefreshBaseFragment {

    @Bind(R.id.rv_news)
    RecyclerView mTeamsListView;
    private TeamSortAdapter mTeamSortAdapter;
    protected List<Teams.TeamsortEntity> mTeamsSortEntity = new ArrayList<>();
    public static TeamSortFragment newInstance() {
        TeamSortFragment teamSortFragment = new TeamSortFragment();
        return teamSortFragment;
    }

    @Override
    protected int getTitle() {
        return R.string.sort;
    }

    @Override
    protected void initViews() {
        super.initViews();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mTeamsListView.getContext());
        mTeamsListView.setLayoutManager(linearLayoutManager);
        mTeamSortAdapter=new TeamSortAdapter(getActivity(),mTeamsSortEntity);
        mTeamsListView.setAdapter(mTeamSortAdapter);
        setRefreshing();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }


    public void onEventMainThread(TeamSortEvent teamSortEvent) {
        stopRefreshing();
        if(Constant.Result.FAIL.equals(teamSortEvent.getEventResult())){
            return;
        }
        mTeamsSortEntity.clear();
        mTeamsSortEntity.addAll(teamSortEvent.getmTeams().getTeamsort());
        mTeamSortAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        AppService.getInstance().getTeamSort(getTaskId());
    }

}
