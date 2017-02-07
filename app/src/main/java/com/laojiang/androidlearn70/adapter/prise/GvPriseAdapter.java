package com.laojiang.androidlearn70.adapter.prise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.adapter.adapterpacking.MyBaseAdapter;
import com.laojiang.androidlearn70.adapter.adapterpacking.ViewHolder;

/**
 * listview适配器应用
 * Created by Jiang on 2016/12/27.
 */

public class GvPriseAdapter extends MyBaseAdapter {
    public GvPriseAdapter(Context context, String[] str) {
        super(context, str);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //实例化一个viewHolder
        ViewHolder viewHolder = ViewHolder.get(context, convertView, parent,
                R.layout.item_prise, position);
        //通过getView获取控件
        TextView tv = viewHolder.getView(R.id.tv_name);
        //使用
        tv.setText(str[position]);
        return viewHolder.getConvertView();
    }
}