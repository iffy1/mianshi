package com.iffy.mianshi.pattern.facade.retrofit;

import com.iffy.mianshi.pattern.facade.retrofit.bean.Tianqi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author : iffy
 * time   : 2020/03/26
 */
public interface Tianqi_interface {
    @GET("weather_mini?")
    Observable<Tianqi> getProduct(@Query("city") String city);
}
