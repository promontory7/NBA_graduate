package com.me.zhuchudong.nba.event;

/**
 * Created by SilenceDut on 2015/12/9.
 */
public class NewsDetileEvent extends Event {
    private String mNewsContent;
    public NewsDetileEvent(String newsContent) {
        this.mNewsContent=newsContent;
    }
    public String getContent() {
        return mNewsContent;
    }
}
