package com.laojiang.androidlearn70.adapter.prise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.utils.weight.adapterpacking.MyBaseAdapter;
import com.laojiang.androidlearn70.utils.weight.adapterpacking.ViewHolder;

import java.util.List;
import java.util.Random;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/1/11 8:06.
 */

public class DragListviewAdapter extends MyBaseAdapter{
    public DragListviewAdapter(Context context, List list) {
        super(context, list);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =  ViewHolder.get(context, view, viewGroup,
                R.layout.item_drag_listview, i);
        TextView textView = holder.getView(R.id.tv_drag_text);
        textView.setText(list.get(i)+"");
        Random random = new Random();
        int[] color = new int[]{R.color.colorAccent,R.color.colorPrimary,R.color.bg_color};
        int i1 = random.nextInt(3);
        textView.setBackgroundResource(color[i1]);
        return holder.getConvertView();
    }
}
