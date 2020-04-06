package com.example.taobaotest.presenter.impl;

import com.example.taobaotest.model.Api;
import com.example.taobaotest.model.domain.Categories;
import com.example.taobaotest.presenter.IHomePresenter;
import com.example.taobaotest.utils.LogUtils;
import com.example.taobaotest.utils.RetrofitManager;
import com.example.taobaotest.view.IHomeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenterImpl implements IHomePresenter {
    private IHomeCallback mCallback = null;

    @Override
    public void getCategories() {

        if (mCallback!=null) {
            mCallback.onLoading();
        }

        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                //返回结果
                int code = response.code();
                if (code== HttpURLConnection.HTTP_OK) {
                    Categories categories = response.body();
                    LogUtils.d(this,categories.toString());


                    if (mCallback != null) {
                        if (categories ==null || categories.getData().size()==0){
                            mCallback.onEmpty();
                        }else {

                            mCallback.onCategoriesLoaded(categories);
                        }
                    }

                }else {
                    LogUtils.i(this,"请求失败");
                    if (mCallback != null) {

                        mCallback.onNetworkError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                //返回失败信息
                //TODO:
                LogUtils.i(this,"请求错误"+t.toString());
                if (mCallback != null) {

                    mCallback.onNetworkError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {
        //加载数据成功之后的回调方法
        mCallback = null;
    }
}
