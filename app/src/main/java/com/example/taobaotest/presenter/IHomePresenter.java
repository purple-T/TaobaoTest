package com.example.taobaotest.presenter;

import com.example.taobaotest.view.IHomeCallback;

public interface IHomePresenter {

    /**
     * 获取商品分类
     */
    void getCategories();

    /**
     * 注册UI通知的接口
     * @param callback
     */
    void registerCallback(IHomeCallback callback);

    /**
     * 取消UI通知接口
     * @param callback
     */
    void unregisterCallback(IHomeCallback callback);

}
