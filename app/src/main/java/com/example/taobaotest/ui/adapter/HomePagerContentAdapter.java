package com.example.taobaotest.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taobaotest.R;
import com.example.taobaotest.model.domain.HomePagerContent;
import com.example.taobaotest.utils.LogUtils;
import com.example.taobaotest.utils.UrlUtils;
import com.lcodecore.tkrefreshlayout.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePagerContentAdapter extends RecyclerView.Adapter<HomePagerContentAdapter.InnerHolder> {

    List<HomePagerContent.DataBean> datas = new ArrayList<>();

    @NonNull
    @Override
    public HomePagerContentAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pager_content,parent,false);
        InnerHolder innerHolder = new InnerHolder(view);
        return innerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePagerContentAdapter.InnerHolder holder, int position) {
        HomePagerContent.DataBean dataBean = datas.get(position);
        holder.setDatas(dataBean);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<HomePagerContent.DataBean> contents) {
        datas.clear();

        datas.addAll(contents);
        notifyDataSetChanged();


    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_goods_img)
        public ImageView mImageView;


        @BindView(R.id.item_goods_title)
        public TextView mTitleTv;


        @BindView(R.id.item_goods_off_price_tv)
        public TextView offPriceTv;


        @BindView(R.id.item_goods_after_price_tv)
        public TextView goodsAfterPriceTv;

        @BindView(R.id.item_goods_original_price)
        public TextView goodsOriginalPrice;


        @BindView(R.id.item_goods_sell_count)
        public TextView goodsSellCountTv;




        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setDatas(HomePagerContent.DataBean dataBean) {
            Context context = itemView.getContext();
            String coverPath = UrlUtils.getCoverPath(dataBean.getPict_url());
            Glide.with(context).load(coverPath).into(mImageView);

            mTitleTv.setText(dataBean.getTitle());

            offPriceTv.setText(String.format(context.getString(R.string.text_goods_off_price),dataBean.getCoupon_amount()));

            String finalPrice = dataBean.getZk_final_price();
            int coupon_amount = dataBean.getCoupon_amount();
            float resultPrice = Float.parseFloat(finalPrice)- coupon_amount;
            goodsAfterPriceTv.setText(String.format("¥%.2f",resultPrice));

            goodsOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            goodsOriginalPrice.setText(String.format(context.getString(R.string.text_goods_original_price),finalPrice));
//            goodsOriginalPrice
            goodsSellCountTv.setText(String.format(context.getString(R.string.text_goods_sell_count),dataBean.getVolume()));
        }
    }
}
