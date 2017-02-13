package com.laojiang.androidlearn70.activity.intent;

import android.view.KeyEvent;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/2/13 9:22.
 */

public class AnimationActivity extends BaseActivity {
    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_animation);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finishAfterTransition();
        }
        return true;
    }
}
