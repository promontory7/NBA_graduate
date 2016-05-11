package com.me.zhuchudong.nba.network;

import com.me.zhuchudong.nba.model.NewsDetile;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by SilenceDut on 2015/12/10.
 */
public interface NewsDetileAPI {
    @GET("{date}/{detileId}")
    Observable<NewsDetile> getNewsDetile(@Path("date") String type,@Path("detileId") String newsId);
}
