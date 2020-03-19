package com.iffy.async.handler;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author : iffy
 * time   : 2020/03/14
 */
public class HandlerTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler h1 = new Handler(){};
        Handler h2 = new Handler(){};

        Log.e("iffy","主进程中Handler1.getLooper():的looper对象"+h1.getLooper());
        Log.e("iffy","主进程中Handler2.getLooper():的looper对象"+h2.getLooper());
    }
}
