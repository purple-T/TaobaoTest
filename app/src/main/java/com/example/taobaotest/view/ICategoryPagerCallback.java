package com.example.taobaotest.view;

import com.example.taobaotest.base.IBaseCallback;
import com.example.taobaotest.model.domain.HomePagerContent;

import java.util.List;

public interface ICategoryPagerCallback extends IBaseCallback {

    /**
     * 数据加载回来
     */
    void onContentLoaded(List<HomePagerContent.DataBean > contents);

    int getCategoryId();



    /**
     * 加载更多网络错误
     */
    void onLoaderMOreError();

    /**
     * 没有更多数据
     */
    void onLoaderMoreEmpty();

    /**
     * 加载到了更多内容
     */
    void onLoaderMoreLoaded(List<HomePagerContent.DataBean > contents);

    /**
     * 轮播图内容加载到了
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean > contents);

}
