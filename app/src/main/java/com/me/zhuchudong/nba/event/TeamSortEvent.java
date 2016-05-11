package com.me.zhuchudong.nba.event;

import com.me.zhuchudong.nba.data.Constant;
import com.me.zhuchudong.nba.model.Teams;

/**
 * Created by SilenceDut on 2015/12/23.
 */
public class TeamSortEvent extends Event {
    private Teams mTeams;
    public TeamSortEvent(Teams teams,Constant.Result result) {
        this.mTeams=teams;
        this.mEventResult=result;
    }

    public Teams getmTeams() {
        return mTeams;
    }
}
