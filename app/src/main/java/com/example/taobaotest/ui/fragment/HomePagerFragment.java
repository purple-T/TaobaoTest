package com.example.taobaotest.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.taobaotest.R;
import com.example.taobaotest.base.BaseFragment;
import com.example.taobaotest.model.domain.Categories;
import com.example.taobaotest.model.domain.HomePagerContent;
import com.example.taobaotest.presenter.ICategoryPagerPresent;
import com.example.taobaotest.presenter.impl.CategoryPagerPresentImpl;
import com.example.taobaotest.ui.adapter.HomePagerContentAdapter;
import com.example.taobaotest.ui.adapter.LooperPagerAdapter;
import com.example.taobaotest.utils.Constants;
import com.example.taobaotest.utils.LogUtils;
import com.example.taobaotest.view.ICategoryPagerCallback;

import java.util.List;

import butterknife.BindView;

public class HomePagerFragment extends BaseFragment implements ICategoryPagerCallback {

    private ICategoryPagerPresent mCategoryPagerPresent;
    private int mMaterialId;
    private String mTitle;

    @BindView(R.id.home_pager_content_rv)
    public RecyclerView mRecyclerView;
    private HomePagerContentAdapter mAdapter;

    @BindView(R.id.looper_pager)
    public ViewPager looper_pager;
    private LooperPagerAdapter mPagerAdapter;

    @BindView(R.id.home_pager_title)
    public TextView homepagerTitleEv;


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
    protected void initView(View rootView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new HomePagerContentAdapter();
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom =8;
                outRect.top =8;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mPagerAdapter = new LooperPagerAdapter();
        looper_pager.setAdapter(mPagerAdapter);

    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();

        mTitle = arguments.getString(Constants.KEY_HOME_PAGER_TITLE);
        mMaterialId = arguments.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID);

        LogUtils.d(this,"title----"+ mTitle);
        LogUtils.d(this,"masterialId----"+ mMaterialId);

        if (homepagerTitleEv !=null) {

            homepagerTitleEv.setText(mTitle);
        }

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
    public void onContentLoaded(List<HomePagerContent.DataBean> contents) {


        setUpState(State.SUCCESS);

        mAdapter.setDatas(contents);


    }

    @Override
    public int getCategoryId() {
        return mMaterialId;
    }

    @Override
    public void onLoading() {

        setUpState(State.LOADING);
    }

    @Override
    public void onError() {


        setUpState(State.ERROR);
    }

    @Override
    public void onEmpty()
    {

        setUpState(State.EMPTY);
    }

    @Override
    public void onLoaderMOreError() {

    }

    @Override
    public void onLoaderMoreEmpty() {

    }

    @Override
    public void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents) {

    }

    @Override
    public void onLooperListLoaded(List<HomePagerContent.DataBean> contents) {

        mPagerAdapter.setDatas(contents);
    }


}
