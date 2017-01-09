package com.laojiang.androidlearn70.activity.intent;

import android.widget.GridView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.adapter.prise.GvPriseAdapter;

import butterknife.BindView;

/**
 * Created by Jiang on 2016/12/27.
 */

public class PriseActivity extends BaseActivity {


    @BindView(R.id.gv_list)
    GridView gvList;
    private GvPriseAdapter adapter;

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_prise);

    }

    @Override
    protected void initViews() {
        super.initViews();
        String[] buttonName = new String[]{"打电话"};
        adapter = new GvPriseAdapter(mContext,buttonName);
        gvList.setAdapter(adapter);

    }
}
