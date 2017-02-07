package com.laojiang.androidlearn70.activity.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.githang.statusbar.StatusBarCompat;
import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.server.TestBindService;
import com.laojiang.androidlearn70.system.L;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类介绍（必填）：绑定服务测试client
 * Created by Jiang on 2017/2/3 8:43.
 */

public class TestBindServiceActivity extends BaseActivity {
    private static final java.lang.String TAG = "绑定服务";
    @BindView(R.id.bt_bind_server)
    Button btBindServer;
    @BindView(R.id.bt_unbind_server)
    Button btUnbindServer;
    private TestBindService serviceTest;

    @Override
    protected void initViews() {

        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.white),true);
    }

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_testbindserver);
    }

    private boolean isBound;
    private ServiceConnection conn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isBound = true;
        TestBindService.TestBinder testBindService = (TestBindService.TestBinder) service;
        serviceTest = testBindService.getService();
        int randomNumber = serviceTest.getRandomNumber();
        L.d(TAG,"我是绑定服务获取的方法-=="+randomNumber);


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBound =false;
        L.d(TAG,"我已经解除了绑定");
    }
};


    @OnClick({R.id.bt_bind_server, R.id.bt_unbind_server})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_bind_server:
                L.d(TAG,"绑定按钮");
                Intent intent = new Intent(mContext,TestBindService.class);
                bindService(intent,conn,BIND_AUTO_CREATE);

                break;
            case R.id.bt_unbind_server:
                if (isBound){
                    unbindService(conn);
                }
                break;
        }
    }
}
