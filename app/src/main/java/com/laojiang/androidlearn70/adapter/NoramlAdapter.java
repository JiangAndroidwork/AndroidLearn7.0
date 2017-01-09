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
 * Created by Jiang on 2016/12/21.
 */

public class NoramlAdapter extends RecyclerView.Adapter<NoramlAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> mFruitList;
    private CallBack callBack;

    public interface CallBack{
        void callBackOnclick(View v);
        void callBackLongOnclick(View v);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fuit_image);
            fruitName = (TextView)itemView.findViewById(R.id.fruit_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public NoramlAdapter(Context mContext, List<Fruit> mFruitList, CallBack callBack) {
        this.mContext = mContext;
        this.mFruitList = mFruitList;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NoramlAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        //瀑布布局
        Random random =new Random();
        CardView.LayoutParams cl = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cl.height = random.nextInt(500)+300;
        holder.linearLayout.setLayoutParams(cl);
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
        holder.linearLayout.setTag(position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.callBackOnclick(view);
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callBack.callBackLongOnclick(view);
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
