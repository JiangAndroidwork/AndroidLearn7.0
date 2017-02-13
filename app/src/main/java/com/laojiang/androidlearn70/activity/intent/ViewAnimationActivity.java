package com.laojiang.androidlearn70.activity.intent;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/2/13 8:51.
 */

public class ViewAnimationActivity extends BaseActivity {
    @BindView(R.id.bt_visible_view)
    Button btVisibleView;
    @BindView(R.id.bt_gone_view)
    Button btGoneView;
    @BindView(R.id.tv_animation_view)
    TextView tvAnimationView;
    @BindView(R.id.bt_transition)
    Button btTransition;
    @BindView(R.id.iv_view)
    ImageView ivView;

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_view_animation);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }


    @OnClick({R.id.bt_visible_view, R.id.bt_gone_view,R.id.bt_transition})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_visible_view:
                if (tvAnimationView.getVisibility() == View.INVISIBLE) {
                    visibleView();
                }
                break;
            case R.id.bt_gone_view:
                if (tvAnimationView.getVisibility() == View.VISIBLE)
                    goneView();
                break;
            case R.id.bt_transition:
                Intent intent = new Intent(mContext,AnimationActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, ivView, "robot");
                startActivity(intent,options.toBundle());
                break;
        }
    }

    private void goneView() {
        int cx = (tvAnimationView.getLeft() + tvAnimationView.getRight()) / 2;
        int cy = (tvAnimationView.getTop() + tvAnimationView.getBottom()) / 2;
        int max = tvAnimationView.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(tvAnimationView, cx, cy, max, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tvAnimationView.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();

    }

    private void visibleView() {
        int cx = (tvAnimationView.getLeft() + tvAnimationView.getRight()) / 2;
        int cy = (tvAnimationView.getTop() + tvAnimationView.getBottom()) / 2;
        int max = Math.max(tvAnimationView.getWidth(), tvAnimationView.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(tvAnimationView, cx, cy, 0, max);
        tvAnimationView.setVisibility(View.VISIBLE);
        anim.start();
    }





}
