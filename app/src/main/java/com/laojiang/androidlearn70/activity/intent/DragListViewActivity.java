package com.laojiang.androidlearn70.activity.intent;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.adapter.prise.DragListviewAdapter;
import com.laojiang.androidlearn70.utils.weight.CanDragListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/1/10 17:46.
 */

public class DragListViewActivity extends BaseActivity {


    @BindView(R.id.drag_listview)
    CanDragListView dragListview;
    private DragListviewAdapter adapter;
    private List<String> list;

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_drag_listview);
    }

    @Override
    protected void initViews() {
        super.initViews();
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        for (int i=0;i<50;i++){
            list.add("item"+i);
        }
        adapter = new DragListviewAdapter(mContext, list);
        dragListview.setAdapter(adapter);
dragListview.setOnChangeListener(new CanDragListView.OnChanageListener() {
    @Override
    public void onChange(int start, int to) {
        //数据交换
        if(start < to){
            for(int i=start; i<to; i++){
                Collections.swap(list, i, i+1);
            }
        }else if(start > to){
            for(int i=start; i>to; i--){
                Collections.swap(list, i, i-1);
            }
        }
        adapter.notifyDataSetChanged();
    }
});
    }


}
