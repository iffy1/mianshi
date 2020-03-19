package com.iffy.async.handlerThread;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.iffy.async.R;
import com.iffy.module_base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * author : iffy
 * time   : 2020/03/17
 */
//正如前面所说，线程间通信的时候，比如Android中常见的更新UI，
// 涉及到的是子线程和主线程之间的通信，实现方式就是Handler+Looper，
// 但是要自己手动操作Looper，不推荐，所以谷歌封装了HandlerThread类
public class HandlerThreadTest extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建主线程handler
        final Handler mainHandler = new MainHandler(this);

        //创建子线程
        HandlerThread subThread = new HandlerThread("子线程");

        //启动子线程
        subThread.start();

        //创建子线程Handler 使用子线程的looper
        Handler subhandler = new Handler(subThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                //工作线程
                super.handleMessage(msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msgToMain = Message.obtain();
                msgToMain.obj = "下载好了";
                mainHandler.sendMessage(msgToMain);
            }
        };
        Message message = Message.obtain();
        message.obj = "去下载";
        subhandler.sendMessage(message);

    }

    @Override
    public int getContentId() {
        return R.layout.activity_handler_thread_c;
    }

    //主线程handler使用静态内部类 防止泄露
    static class MainHandler extends Handler {
        WeakReference context;

        public MainHandler(Context c) {
            context = new WeakReference<Context>(c);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //主线程
            Toast.makeText((Context) context.get(), msg.obj + "", Toast.LENGTH_LONG).show();
        }
    }


}
