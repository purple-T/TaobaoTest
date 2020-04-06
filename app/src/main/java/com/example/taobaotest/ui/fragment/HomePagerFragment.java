package com.example.taobaotest.ui.fragment;

import android.os.Bundle;

import com.example.taobaotest.R;
import com.example.taobaotest.base.BaseFragment;
import com.example.taobaotest.model.domain.Categories;
import com.example.taobaotest.model.domain.HomePagerContent;
import com.example.taobaotest.presenter.ICategoryPagerPresent;
import com.example.taobaotest.presenter.impl.CategoryPagerPresentImpl;
import com.example.taobaotest.utils.Constants;
import com.example.taobaotest.utils.LogUtils;
import com.example.taobaotest.view.ICategoryPagerCallback;

import java.util.List;

public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallback {

    private ICategoryPagerPresent mCategoryPagerPresent;
    private int mMaterialId;
    private String mTitle;

    public static HomePagerFragment newInstance(Categories.DataBean categories){

        HomePagerFragment homePagerFragment = new HomePagerFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_HOME_PAGER_TITLE,categories.getTitle());
        bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID,categories.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;

    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initPresenter() {

        mCategoryPagerPresent = CategoryPagerPresentImpl.getInstance();
        mCategoryPagerPresent.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();

        mTitle = arguments.getString(Constants.KEY_HOME_PAGER_TITLE);
        mMaterialId = arguments.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID);

        LogUtils.d(this,"title----"+ mTitle);
        LogUtils.d(this,"masterialId----"+ mMaterialId);

        //TODO:加载数据
        if (mCategoryPagerPresent!=null) {
            mCategoryPagerPresent.getContentByCategoryId(mMaterialId);
        }
    }



    @Override
    protected void release() {
        if (mCategoryPagerPresent!=null) {
            mCategoryPagerPresent.unregisterViewCallback(this);
        }

    }


    @Override
    public void onContentLoaded(List<HomePagerContent.DataBean> contents, int categoryId) {

        if (mMaterialId !=categoryId) {
            return;
        }

        setUpState(State.SUCCESS);


    }

    @Override
    public void onLoading(int categoryId) {
        if (mMaterialId !=categoryId) {
            return;
        }
        setUpState(State.LOADING);
    }

    @Override
    public void onError(int categoryId) {

        if (mMaterialId !=categoryId) {
            return;
        }
        setUpState(State.ERROR);
    }

    @Override
    public void onEmpty(int categoryId)
    {
        if (mMaterialId !=categoryId) {
            return;
        }
        setUpState(State.EMPTY);
    }

    @Override
    public void onLoaderMOreError(int gategoryId) {

    }

    @Override
    public void onLoaderMoreEmpty(int gategoryId) {

    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents, int categoryId) {

    }

    @Override
    public void onLooperListLoaded(List<HomePagerContent.DataBean> contents, int categoryId) {

    }


}
