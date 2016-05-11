package com.me.zhuchudong.nba.ui.fragment;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import com.me.zhuchudong.nba.R;
import com.me.zhuchudong.nba.data.Constant;
import com.me.zhuchudong.nba.event.NewsAnimatEndEvent;
import com.me.zhuchudong.nba.event.NewsEvent;
import com.me.zhuchudong.nba.model.News;
import com.me.zhuchudong.nba.model.News.NewslistEntity;
import com.me.zhuchudong.nba.ui.adapter.RecycleAdapter.LoadAdapter;
import com.me.zhuchudong.nba.ui.fragment.base.SwipeRefreshBaseFragment;
import com.me.zhuchudong.nba.ui.listener.RecyclerViewLoadMoreListener;
import com.me.zhuchudong.nba.ui.listener.RecyclerViewLoadMoreListener.OnLoadMoreListener;
import com.me.zhuchudong.nba.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by SilenceDut on 2015/12/4.
 */
public abstract class NewsFragment extends SwipeRefreshBaseFragment implements OnLoadMoreListener {
    @Bind(R.id.rv_news)
    RecyclerView mNewsListView;
    @Bind(R.id.newsContainer)
    CoordinatorLayout newsContainer;


    protected List<NewslistEntity> mNewsListEntity = new ArrayList<NewslistEntity>();
    protected LoadAdapter mLoadAdapter;
    protected String mNewsId="";

    abstract void setAdapter();

    @Override
    protected void initViews() {
        super.initViews();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mNewsListView.getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        if (getTitle()==R.string.news){
            mNewsListView.setLayoutManager(staggeredGridLayoutManager);
        }else {
            mNewsListView.setLayoutManager(linearLayoutManager);
        }

        mNewsListView.addOnScrollListener(new RecyclerViewLoadMoreListener(linearLayoutManager, this, 0));
        mNewsListView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        );
        setAdapter();
    }

    protected void stopAll() {
        stopRefreshing();
        stopLoading();
    }

    protected void stopLoading() {
        mLoadAdapter.notifyItemChanged(mLoadAdapter.getItemCount() - 1);
        mLoadAdapter.setLoading(false);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }


    protected void updateView(NewsEvent newsEvent) {

        if (Constant.Result.FAIL.equals(newsEvent.getEventResult())) {
            if(newsEvent.getNewsWay().equals(Constant.GETNEWSWAY.INIT)) {
                setRefreshing();
            }else {
                stopAll();
                AppUtils.showSnackBar(newsContainer, R.string.load_fail);
            }
        } else {
            News news = newsEvent.getNews();
            mNewsId = news.getNextId();
            switch (newsEvent.getNewsWay()) {
                case INIT:
                    mNewsListEntity.clear();
                    mNewsListEntity.addAll(news.getNewslist());
                    break;
                case UPDATE:
                    mNewsListEntity.clear();
                    mNewsListEntity.addAll(news.getNewslist());
                    stopRefreshing();
                    mLoadAdapter.setAnimate(false);
                    break;
                case LOADMORE:
                    mNewsListEntity.addAll(news.getNewslist());
                    stopLoading();
                    mLoadAdapter.setAnimate(false);
                    break;
                default:
                    break;
            }
            mLoadAdapter.updateItem();
            if (Constant.GETNEWSWAY.UPDATE.equals(newsEvent.getNewsWay())) {
                AppUtils.showSnackBar(newsContainer, R.string.load_success);
            }

        }


    }

    public void onEventMainThread(NewsAnimatEndEvent newsAnimatEndEvent) {
        setRefreshing();
    }

}
