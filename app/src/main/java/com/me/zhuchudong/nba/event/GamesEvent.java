package com.me.zhuchudong.nba.event;

import com.me.zhuchudong.nba.data.Constant;
import com.me.zhuchudong.nba.model.Games;

/**
 * Created by SilenceDut on 2015/12/26.
 */
public class GamesEvent extends Event {
    private Games mAllGames;
    public GamesEvent(Games games,Constant.Result result) {
        this.mAllGames=games;
        this.mEventResult=result;
    }

    public Games getAllGames() {
        return mAllGames;
    }
}
