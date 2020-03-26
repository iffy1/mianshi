package com.iffy.mianshi.pattern.facade.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;


/**
 * author : iffy
 * time   : 2020/03/26
 */
public class MyPicture {
    private static MyPicture sInstance;
    //外观模式封装Glide
    private RequestManager glide;

    //单例模式
    public static MyPicture getInstance(Context context) {
        if (sInstance == null) {
            synchronized (MyPicture.class) {
                if (sInstance == null) {
                    sInstance = new MyPicture(context);
                }
            }
        }
        return sInstance;
    }

    private MyPicture(Context context) {
        glide = Glide.with(context);
    }

    public void showPicture(String url, ImageView iv) {
        glide.load(url).into(iv);
    }

}
