package com.example.taobaotest.presenter.impl;

import com.example.taobaotest.model.Api;
import com.example.taobaotest.model.domain.HomePagerContent;
import com.example.taobaotest.presenter.ICategoryPagerPresent;
import com.example.taobaotest.utils.LogUtils;
import com.example.taobaotest.utils.RetrofitManager;
import com.example.taobaotest.utils.UrlUtils;
import com.example.taobaotest.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryPagerPresentImpl implements ICategoryPagerPresent {

    private Map<Integer,Integer> pagesInfo = new HashMap<>();

    public static final int DEFAULT_PAGE = 1;


    private CategoryPagerPresentImpl(){

    }

    private List<ICategoryPagerCallback> mCallbacks = new ArrayList<>();

    private static ICategoryPagerPresent sInstance;

    public static ICategoryPagerPresent getInstance() {

        sInstance = new CategoryPagerPresentImpl();
        return sInstance;
    }



    @Override
    public void getContentByCategoryId(int categoryId) {
        //根据分类id去加载内容

        //加载指示器
        for (ICategoryPagerCallback callback : mCallbacks) {
            if (callback.getCategoryId()==categoryId) {

                callback.onLoading();
            }
        }

        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        Integer targetPage = pagesInfo.get(categoryId);
        if (targetPage ==null) {
            targetPage = DEFAULT_PAGE;
            pagesInfo.put(categoryId,targetPage);
        }

        String homePagerUrl = UrlUtils.createHomePagerUrl(categoryId, targetPage);
        Call<HomePagerContent> task = api.getHomePageContent(homePagerUrl);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                int code = response.code();
                LogUtils.d(CategoryPagerPresentImpl.this,"onResponse code---->"+code);

                if (code == HttpURLConnection.HTTP_OK) {
                    HomePagerContent pagerContent = response.body();
                    LogUtils.d(CategoryPagerPresentImpl.this,"pageContent---->"+pagerContent.toString());
                    //把数据给到ui 更新
                    handleHomePageContentResult(pagerContent,categoryId);
//                    if (callback!=null) {
//
//                        callback.onContentLoaded(pagerContent.getData());
//                    }
                }else{
                    //处理错误
                    handleNetworkError(categoryId);
                }

            }



            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.d(CategoryPagerPresentImpl.this,"onFailure---->"+t.toString());
                //处理错误
                handleNetworkError(categoryId);
//                if (callback!=null) {
//
//                    callback.onError(categoryId);
//                }
            }
        });
    }

    @Override
    public void loaderMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }


    private void handleNetworkError(int categoryId) {
        for (ICategoryPagerCallback callback : mCallbacks) {

            if (callback.getCategoryId()==categoryId) {

                callback.onError();
            }

        }
    }

    private void handleHomePageContentResult(HomePagerContent pagerContent, int categoryId) {
        //更新ui
        for (ICategoryPagerCallback callback : mCallbacks) {

            if (callback.getCategoryId()==categoryId) {

                if (pagerContent==null||pagerContent.getData().size()==0) {

                    callback.onEmpty();

                }else{

                    List<HomePagerContent.DataBean> pagerContentData = pagerContent.getData();
                    List<HomePagerContent.DataBean> looperData = pagerContentData.subList(pagerContentData.size() - 5, pagerContentData.size());
                    callback.onLooperListLoaded(looperData);
                    callback.onContentLoaded(pagerContentData);

                }
            }

        }


    }

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {

        if (!mCallbacks.contains(callback)) {

            mCallbacks.add(callback);
        }

    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {

        if (mCallbacks.contains(callback)) {

            mCallbacks.remove(callback);
        }

    }
}
