package com.laojiang.androidlearn70.adapter.prise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.utils.weight.adapterpacking.MyBaseAdapter;
import com.laojiang.androidlearn70.utils.weight.adapterpacking.ViewHolder;

import java.util.List;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/14 13:19.
 */

public class PictureAdapter extends MyBaseAdapter {
    public PictureAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(context,convertView,parent, R.layout.item_picture,position);
        ImageView iv = holder.getView(R.id.iv_picture);
        String resultsEntity = (String) list.get(position);
        Glide.with(context).load(resultsEntity).placeholder(R.drawable.icon_food_2).into(iv);
        return holder.getConvertView();
    }
}
