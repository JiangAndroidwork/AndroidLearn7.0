package com.laojiang.androidlearn70.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laojiang.androidlearn70.R;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2016/12/29 10:55.
 */

public class PhotosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager,container,false);
        return view;
    }
}
