package com.laojiang.androidlearn70.activity.intent.retrofit;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.adapter.prise.retrofit.MyDownFilesAdapter;
import com.laojiang.androidlearn70.bean.mydownfiles.DownFilesInfo;
import com.laojiang.retrofithttp.weight.downfilesutils.FinalDownFiles;
import com.laojiang.retrofithttp.weight.downfilesutils.action.FinalDownFileResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.laojiang.androidlearn70.R.id.bt_pause;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/22 15:19.
 */

public class MyDownFilesActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(bt_pause)
    Button btPause;
    @BindView(R.id.bt_end)
    Button btEnd;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private List<DownFilesInfo> list = new ArrayList<>();
    private MyDownFilesAdapter myDownFilesAdapter;
    private String[] downUrl;
    private FinalDownFiles finalDownFiles;
    private boolean isPause;
    private String[] downUrl1;

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.item_my_downfile);
    }

    @Override
    protected void initViews() {
        super.initViews();
        downUrl = new String[]{"http://www.izaodao.com/app/izaodao_app.apk"
                ,"http://114.215.142.151/cloudfile/public/classfile/2017031713563149420/东京喰种01_20170317135631460.mp4"};
    }


    @OnClick({R.id.bt_start, bt_pause, R.id.bt_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                //文件下载
                finalDownFiles = new FinalDownFiles(false,mContext,downUrl[1],
                        Environment.getExternalStorageDirectory() + "/bjhj/accessory/" + "东京喰种01_20170317135631460.mp4",new FinalDownFileResult(){
                    @Override
                    public void onLoading(long readLength, long countLength) {
                        super.onLoading(readLength, countLength);
                        progressBar.setMax((int)countLength);
                        progressBar.setProgress((int)readLength);
                    }
                });
                break;
            case R.id.bt_pause:
                if (isPause){
                    btPause.setText("暂停");
                    if (finalDownFiles!=null)
                        finalDownFiles.setRestart();
                    isPause = false;
                }else {
                    if (finalDownFiles!=null)
                        finalDownFiles.setPause();
                    btPause.setText("开始");
                    isPause = true;

                }
                break;
            case R.id.bt_end:
                if (finalDownFiles!=null){
                    finalDownFiles.setStop();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finalDownFiles.stopAll();
    }
}
