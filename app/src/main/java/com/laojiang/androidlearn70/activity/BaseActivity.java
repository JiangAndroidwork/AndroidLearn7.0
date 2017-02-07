package com.laojiang.androidlearn70.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.githang.statusbar.StatusBarCompat;
import com.laojiang.androidlearn70.R;

import butterknife.ButterKnife;

/**
 *
 * Created by Jiang on 2016/12/27.
 */

public class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initSetContentView();
        ButterKnife.bind(this);
        initViews();
    }

    protected void initSetContentView(){

    }
    protected void initViews(){
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.bg_color),true);
    }

}
