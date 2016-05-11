package com.me.zhuchudong.nba.event;
import com.me.zhuchudong.nba.data.Constant.Result;
/**
 * Created by SilenceDut on 2015/11/28.
 */
public class Event {
    protected Result mEventResult;

    public void setEventResult(Result eventResult) {
        this.mEventResult=eventResult;
    }

    public Result getEventResult() {
        return mEventResult;
    }
}
