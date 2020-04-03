package com.example.taobaotest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.taobaotest.R;
import com.example.taobaotest.base.BaseFragment;
import com.example.taobaotest.model.domain.Categories;
import com.example.taobaotest.presenter.IHomePresenter;
import com.example.taobaotest.presenter.impl.HomePresenterImpl;
import com.example.taobaotest.ui.adapter.HomePagerAdapter;
import com.example.taobaotest.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    @BindView(R.id.home_indicator)
    public TabLayout mTabLayout;

    @BindView(R.id.home_pager)
    public ViewPager homePager;

    private IHomePresenter mHomePresenter;
    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView(View rootView) {

        mTabLayout.setupWithViewPager(homePager);
        // 给viewPager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        //设置适配器
        homePager.setAdapter(mHomePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        // 加载数据成功
        if (mHomePagerAdapter!=null) {

            mHomePagerAdapter.setCategories(categories);
        }
    }

    @Override
    protected void release() {
        //取消回调注册
        if (mHomePresenter!=null) {
            mHomePresenter.unregisterCallback(this);
        }
    }
}
