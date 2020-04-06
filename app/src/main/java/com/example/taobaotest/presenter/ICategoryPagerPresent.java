package com.example.taobaotest.presenter;

import com.example.taobaotest.base.IBasePresenter;
import com.example.taobaotest.view.ICategoryPagerCallback;

public interface ICategoryPagerPresent extends IBasePresenter<ICategoryPagerCallback> {



    /**
     * 根据分类id去查询内容
     * @param categoryId
     */
    void getContentByCategoryId(int categoryId);

    void loaderMore(int categoryId);

    void reload(int categoryId);






//    /**
//     * 注册UI通知的接口
//     * @param callback
//     */
//    void registerViewCallback(ICategoryPagerCallback callback);
//
//    /**
//     * 取消UI通知接口
//     * @param callback
//     */
//    void unregisterViewCallback(ICategoryPagerCallback callback);

}
