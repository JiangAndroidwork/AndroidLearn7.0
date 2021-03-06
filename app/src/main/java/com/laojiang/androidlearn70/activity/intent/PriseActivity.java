package com.laojiang.androidlearn70.activity.intent;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.activity.intent.pickerimag.PickerImagerActivity;
import com.laojiang.androidlearn70.activity.intent.retrofit.MyDownFilesActivity;
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
        String[] buttonName = new String[]{"打电话","draglistview","bindServer","IntentService","相册1","使用揭露效果","LitePal数据库","lottie动画","MyRetrofitActivity","文件下载","图片选择器","发短信","打电话","发短信"};
        adapter = new GvPriseAdapter(mContext,buttonName);
        gvList.setAdapter(adapter);
        iniview();
    }

    private void iniview() {
        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intentTel = new Intent(Intent.ACTION_DIAL);
                        intentTel.setData(Uri.parse("tel:10086"));
                        startActivity(intentTel);
                        break;
                    case 1:
                        Intent intentDrag = new Intent(mContext,DragListViewActivity.class);

                        startActivity(intentDrag);
                        break;
                    case 2:
                        Intent bindServer = new Intent(mContext,TestBindServiceActivity.class);
                        startActivity(bindServer);
                        break;
                    case 3:

                        break;
                    case 4:
                        Intent photoPicker = new Intent(mContext,PhotosSelectActivity.class);
                        startActivity(photoPicker);
                        break;
                    case 5:
                        Intent photo =  new Intent(mContext,ViewAnimationActivity.class);
                        startActivity(photo);
                        break;
                    case 6:
                        Intent litePal = new Intent(mContext,LitePalActivity.class);
                        startActivity(litePal);
                        break;
                    case 7:
                        Intent lottie = new Intent(mContext,LottieActivity.class);
                        startActivity(lottie);
                        break;
                    case 8:
                        Intent myRetrofit = new Intent(mContext,MyRetrofitActivity.class);
                        startActivity(myRetrofit);
                        break;
                    case 9:
                        Intent myDownFiles = new Intent(mContext,MyDownFilesActivity.class);
                        startActivity(myDownFiles);
                        break;
                    case 10:
                        Intent pickImag = new Intent (mContext,PickerImagerActivity.class);
                        startActivity(pickImag);
                        break;
                }
            }
        });
    }
}
