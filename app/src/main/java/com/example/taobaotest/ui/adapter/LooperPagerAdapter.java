package com.example.taobaotest.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.taobaotest.model.domain.HomePagerContent;
import com.example.taobaotest.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

public class LooperPagerAdapter extends PagerAdapter {


    private List<HomePagerContent.DataBean> mDatas = new ArrayList<>();

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        HomePagerContent.DataBean dataBean = mDatas.get(position);
        ImageView imageView = new ImageView(container.getContext());

        Glide.with(container.getContext()).load(UrlUtils.getCoverPath(dataBean.getPict_url())).into(imageView);

        container.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        imageView.setLayoutParams(layoutParams);

        return imageView;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);

        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public void setDatas(List<HomePagerContent.DataBean> contents) {

        mDatas.clear();
        mDatas.addAll(contents);
        notifyDataSetChanged();


    }
}
