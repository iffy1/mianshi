package com.iffy.mianshi.rxjava.transformOperation.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import com.iffy.mianshi.rxjava.retrofitUtil.Constant
import com.iffy.mianshi.rxjava.retrofitUtil.Translation
import com.iffy.mianshi.rxjava.retrofitUtil.translate_interface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TransformActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        var retrofit = Retrofit.Builder().baseUrl(Constant.BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var worker = retrofit.create(translate_interface::class.java)

        worker.getTranslated("ja")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.show()
                var jap = it.content?.out
                if (jap != null) {
                    worker.getChinese(jap).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).subscribe {
                            it.show()
                        }
                }

            }

        println("--------------------------------------")
        worker.getTranslated("ja")
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .flatMap(object : Function<Translation, Observable<Translation>> {
                override fun apply(t: Translation): Observable<Translation> {
                    t.show()
                    var jap = t.content?.out
                    if (jap != null) {
                        return worker.getChinese(jap)
                    } else {
                        return worker.getChinese("错的")
                    }
                }
            })
//            .observeOn(Schedulers.io())
//            .subscribeOn(Schedulers.io())
            .subscribe {
                it.show()
            }

    }



}