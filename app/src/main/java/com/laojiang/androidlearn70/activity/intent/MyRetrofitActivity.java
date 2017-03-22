package com.laojiang.androidlearn70.activity.intent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.adapter.prise.PictureAdapter;
import com.laojiang.androidlearn70.bean.PictureBean;
import com.laojiang.androidlearn70.retrofitInterface.RetrofitMethodsInterface;
import com.laojiang.retrofithttp.weight.ui.ProgressBarOfRetrofit;
import com.laojiang.retrofithttp.weight.ui.RetrofitOfRxJavaCallBack;
import com.laojiang.retrofithttp.weight.weight.ApiSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cc.dagger.photopicker.PhotoPicker;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/10 17:14.
 */

public class MyRetrofitActivity extends BaseActivity {
    private static final String STR_URL = "https://gank.io/";
    @BindView(R.id.gv_picture)
    GridView gvPicture;
    private ArrayList<String> list;
    private PictureAdapter pictureAdapter;


    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_myretrofit);
    }

    @Override
    protected void initViews() {
        super.initViews();
        initAdapter();
        init();
    }

    private void initAdapter() {
        list = new ArrayList<>();

        pictureAdapter = new PictureAdapter(mContext, list);
        gvPicture.setAdapter(pictureAdapter);
        gvPicture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoPicker.preview()
                        .paths((ArrayList<String>) list)
                        .currentItem(position)
                        .start(MyRetrofitActivity.this);
            }
        });

    }

    private void init() {
        ProgressBarOfRetrofit ss = ProgressBarOfRetrofit.getInstance(this, STR_URL, new RetrofitOfRxJavaCallBack() {
            @Override
            public void callBack(Retrofit retrofit) {
                retrofit.create(RetrofitMethodsInterface.class)
                        .getPicture(500, 1)
                        .delay(1, TimeUnit.SECONDS)
                        .map(new Function<PictureBean, List<String>>() {
                            @Override
                            public List<String> apply(PictureBean pictureBean) throws Exception {
                                List<String > pictures = new ArrayList<String>();
                                List<PictureBean.ResultsEntity> results = pictureBean.getResults();
                                if(!pictureBean.isError()) {
                                    for (PictureBean.ResultsEntity pr : results) {
                                        pictures.add(pr.getUrl());
                                    }
                                }
                                return pictures;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ApiSubscriber<List<String>>() {
                            @Override
                            protected void onError(String s, int i) {
                                Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            protected void onSuceess(List<String> strings) {
                            list.addAll(strings);
                                pictureAdapter.notifyDataSetChanged();
                            }
                        });

            }
        });

        ss.setStart(false);
    }



}
