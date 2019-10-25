package com.iffy.mianshi.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RegisterService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 步骤1：创建Binder对象 ->>分析1
        Stub stub = new Stub();
        //ServiceManager.addService(Stub.name,stub);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
