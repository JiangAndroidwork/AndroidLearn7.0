package com.laojiang.androidlearn70.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/2/3 8:48.
 */

public class TestBindService extends Service {
    public class TestBinder extends Binder{
        public TestBindService getService() {
            return TestBindService.this;
        }
    }
    private TestBinder testBinder = new TestBinder();
    private Random random = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return testBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    public int getRandomNumber(){
        return random.nextInt();
    }
}
