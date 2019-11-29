package com.iffy.mianshi.rxjava.retrofitUtil

import com.iffy.mianshi.rxjava.retrofitUtil.Translation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface translate_interface {
    //翻译成中文 w为要翻译的字符串
    @GET("ajax.php?a=fy&f=auto&t=zh")
    fun getCall(@Query("w") s: String): Observable<Translation>

    //得到hello world的翻译 t为目标语言 可以为 "en", "ja", "de", "zh", "ko", "es", "fr"
    @GET("ajax.php?a=fy&f=auto&w=hello world")
    fun getCallC(@Query("t") language:String): Observable<Translation>

    //得到hello world的翻译 t为目标语言 可以为 "en", "ja", "de", "zh", "ko", "es", "fr"
    @GET("ajax.php?a=fy&f=auto&w=hello world")
    fun getTranslated(@Query("t") targetLanguage: String): Observable<Translation>

    //给出字词翻译成中文
    @GET("ajax.php?a=fy&f=auto&t=zh")
    fun getChinese(@Query("w") s: String): Observable<Translation>
}