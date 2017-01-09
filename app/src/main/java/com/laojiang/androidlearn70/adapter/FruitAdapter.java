package com.laojiang.androidlearn70.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.bean.Fruit;

import java.util.List;
import java.util.Random;

/**
 * recycle 下拉刷新和上拉加载
 * Created by Jiang on 2016/12/21.
 */

public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView
    private Context mContext;
    private List<Fruit> mFruitList;
    private CallBack callBack;

    public interface CallBack {
        void callBackOnclick(View v);

        void callBackLongOnclick(View v);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        LinearLayout linearLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fuit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private TextView foot_view_item_tv;

        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.tv_foot_text);
        }
    }

    public FruitAdapter(Context mContext, List<Fruit> mFruitList, CallBack callBack) {
        this.mContext = mContext;
        this.mFruitList = mFruitList;
        this.callBack = callBack;
    }

    /**
     * item显示类型
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {

            if (mContext == null) {
                mContext = parent.getContext();
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit, parent, false);

            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = LayoutInflater.from(mContext).inflate(R.layout.recycler_load_more_layout, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            FootViewHolder footViewHolder = new FootViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Fruit fruit = mFruitList.get(position);
            ((ItemViewHolder) holder).fruitName.setText(fruit.getName());
            //瀑布布局
            Random random = new Random();
            CardView.LayoutParams cl = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cl.height =  300;
            ((ItemViewHolder) holder).linearLayout.setLayoutParams(cl);
            Glide.with(mContext).load(fruit.getImageId()).into(((ItemViewHolder) holder).fruitImage);
            ((ItemViewHolder) holder).linearLayout.setTag(position);
            ((ItemViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.callBackOnclick(view);
                }
            });
            ((ItemViewHolder) holder).linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    callBack.callBackLongOnclick(view);
                    return true;
                }
            });
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                    break;
            }
        }
    }
    /**
     * 进行判断是普通Item视图还是FootView视图
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
    @Override
    public int getItemCount() {
        return mFruitList.size()+1;
    }

    //添加数据
    public void addItem(List<Fruit> newDatas) {
        //mTitles.add(position, data);
        //notifyItemInserted(position);
        newDatas.addAll(mFruitList);
        mFruitList.removeAll(mFruitList);
        mFruitList.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(List<Fruit> newDatas) {
        mFruitList.addAll(newDatas);
        notifyDataSetChanged();
    }

    /**
     * //上拉加载更多
     * PULLUP_LOAD_MORE=0;
     * //正在加载中
     * LOADING_MORE=1;
     * //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     * @param status
     */
    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }
}
