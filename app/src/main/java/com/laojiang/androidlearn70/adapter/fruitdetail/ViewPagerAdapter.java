package com.laojiang.androidlearn70.adapter.fruitdetail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.laojiang.androidlearn70.fragment.PhotosFragment;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2016/12/29 10:53.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] str;
    private Context context;
    public ViewPagerAdapter(FragmentManager fm, String[] str, Context context) {
        super(fm);
        this.str = str;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment photosFragment = new PhotosFragment();
        return photosFragment;
    }

    @Override
    public int getCount() {
        return str.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
