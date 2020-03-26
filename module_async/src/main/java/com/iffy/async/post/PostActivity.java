package com.iffy.async.post;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iffy.async.R;

/**
 * author : iffy
 * time   : 2020/03/19
 */
public class PostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View v = findViewById(R.id.contentLoadingProgressBarD);

        v.getMeasuredHeight();//这里是0

        //这里没有切换线程，如果想到得到v的mearsure的高宽 必须等performTraversal（）执行完毕以后
        //所以post一个runable放到主线程messagequeue后面，等performOnmeasure后就能拿到尺寸了
        v.post(new Runnable() {
            @Override
            public void run() {
                v.getMeasuredHeight();//这里可以得到测量完的尺寸
            }
        });
    }
}
