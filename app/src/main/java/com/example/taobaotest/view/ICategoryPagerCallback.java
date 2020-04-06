package com.example.taobaotest.view;

import com.example.taobaotest.model.domain.HomePagerContent;

import java.util.List;

public interface ICategoryPagerCallback {

    /**
     * 数据加载回来
     * @param contents
     */
    void onContentLoaded(List<HomePagerContent.DataBean > contents,int categoryId);

    /**
     * 数据加载
     * @param categoryId
     */
    void onLoading(int categoryId);

    /**
     * 加载错误回调
     * @param categoryId
     */
    void onError(int categoryId);

    /**
     * 数据为空回调
     * @param categoryId
     */
    void onEmpty(int categoryId);

    /**
     * 加载更多网络错误
     * @param categoryId
     */
    void onLoaderMOreError(int categoryId);

    /**
     * 没有更多数据
     * @param categoryId
     */
    void onLoaderMoreEmpty(int categoryId);

    /**
     * 加载到了更多内容
     * @param contents
     */
    void onLoaderMoreLoaded(List<HomePagerContent.DataBean > contents,int categoryId);

    /**
     * 轮播图内容加载到了
     * @param contents
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean > contents,int categoryId);

}
