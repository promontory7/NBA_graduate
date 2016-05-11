package com.me.zhuchudong.nba.event;

import com.me.zhuchudong.nba.model.News;
import com.me.zhuchudong.nba.data.Constant.GETNEWSWAY;
/**
 * Created by Silencedut on 2015/11/28.
 */
public class NewsEvent extends Event {

    private News news;
    private GETNEWSWAY getNewsWay;
    private String newsType;

    public String getNewsType() {
        return newsType;
    }

    public NewsEvent(News news,GETNEWSWAY getNewsWay,String newsType) {
        this.news=news;
        this.newsType=newsType;
        this.getNewsWay=getNewsWay;

    }

    public News getNews() {
        return news;
    }

    public GETNEWSWAY getNewsWay() {
        return getNewsWay;
    }
}
