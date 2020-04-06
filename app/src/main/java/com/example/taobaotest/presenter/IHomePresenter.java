package com.example.taobaotest.presenter;

import com.example.taobaotest.base.IBasePresenter;
import com.example.taobaotest.view.IHomeCallback;

public interface IHomePresenter extends IBasePresenter<IHomeCallback> {

    /**
     * 获取商品分类
     */
    void getCategories();

//    /**
//     * 注册UI通知的接口
//     * @param callback
//     */
//    void registerViewCallback(IHomeCallback callback);
//
//    /**
//     * 取消UI通知接口
//     * @param callback
//     */
//    void unregisterViewCallback(IHomeCallback callback);

}
