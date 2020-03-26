package com.iffy.mianshi.pattern.facade.retrofit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iffy.mianshi.pattern.facade.retrofit.bean.Tianqi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : iffy
 * time   : 2020/03/26
 */
public class RetrofitTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit build = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://wthrcdn.etouch.cn")
                .build();

        Tianqi_interface tianqi_interface = build.create(Tianqi_interface.class);
        tianqi_interface.getProduct("沈阳")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Tianqi, String>() {
                    @Override
                    public String apply(Tianqi tianqi) throws Exception {
                        return tianqi.getData().getWendu();
                    }
                })
                .subscribe(wendu -> System.out.println(wendu+"温度"));

    }
}
