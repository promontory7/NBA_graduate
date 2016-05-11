package com.me.zhuchudong.nba.event;

/**
 * Created by SilenceDut on 2015/11/28.
 */
public class DrawerClickEvent extends Event {
    private int drawId;

    public DrawerClickEvent(int drawId) {
        this.drawId = drawId;
    }

    public int getDrawId() {
        return drawId;
    }

}
