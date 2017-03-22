package com.laojiang.androidlearn70.adapter.prise.retrofit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.bean.mydownfiles.DownFilesInfo;
import com.laojiang.androidlearn70.utils.weight.adapterpacking.MyBaseAdapter;
import com.laojiang.androidlearn70.utils.weight.adapterpacking.ViewHolder;
import com.laojiang.retrofithttp.weight.downfilesutils.FinalDownFiles;
import com.laojiang.retrofithttp.weight.downfilesutils.action.FinalDownFileResult;

import java.util.List;

/**
 * 类介绍（必填）：文件下载 适配器
 * Created by Jiang on 2017/3/22 15:23.
 */

public class MyDownFilesAdapter extends MyBaseAdapter {
    private MyDownFileInterface myDownFileInterface;
    private FinalDownFiles finalDownFiles;
    private boolean isPause;

    public MyDownFilesAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(context,convertView,parent, R.layout.item_my_downfile,position);
        Button start = holder.getView(R.id.bt_start);
        final Button pause = holder.getView(R.id.bt_pause);
        Button end = holder.getView(R.id.bt_end);
        TextView title = holder.getView(R.id.title);
        final ProgressBar progressBar = holder.getView(R.id.progressBar);

        final DownFilesInfo info = (DownFilesInfo) list.get(position);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //文件下载
                finalDownFiles = new FinalDownFiles(false,context,info.getUrlStr(),
                        info.getOutStr(),new FinalDownFileResult(){
                    @Override
                    public void onLoading(long readLength, long countLength) {
                        super.onLoading(readLength, countLength);
                        progressBar.setMax((int)countLength);
                        progressBar.setProgress((int)readLength);
                    }
                });
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPause){
                    pause.setText("暂停");
                    if (finalDownFiles!=null)
                        finalDownFiles.setRestart();
                    isPause = false;
                }else {
                    if (finalDownFiles!=null)
                        finalDownFiles.setPause();
                    pause.setText("开始");
                    isPause = true;

                }
            }
        });
end.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (finalDownFiles!=null){
            finalDownFiles.setStop();
        }
    }
});

        return holder.getConvertView();
    }
}
