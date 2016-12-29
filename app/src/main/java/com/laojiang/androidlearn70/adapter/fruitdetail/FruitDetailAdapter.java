package com.laojiang.androidlearn70.adapter.fruitdetail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.adapter.adapterpacking.MyBaseAdapter;
import com.laojiang.androidlearn70.adapter.adapterpacking.ViewHolder;

import java.util.List;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2016/12/29 9:36.
 */

public class FruitDetailAdapter extends MyBaseAdapter {
    public FruitDetailAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =  ViewHolder.get(context,view,viewGroup, R.layout.item_text,i);
        TextView tvText = holder.getView(R.id.tv_text);
        tvText.setText(list.get(i)+"");
        return holder.getConvertView();
    }
}
