package com.iffy.mianshi.storage.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ViewModelPicture(application: Application) : AndroidViewModel(application) {
    private var imgLiveData: MutableLiveData<ArrayList<String>> = MutableLiveData()
    fun retriveImageData() {
        val re = Retrofit.Builder()
            .baseUrl("https://apii.79bk.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val call = re.create(GetPicture_interface::class.java)
        call.getall("imgj.php")
            .enqueue(object : Callback<List<Picture>> {
                override fun onFailure(call: Call<List<Picture>>, t: Throwable) {
                    println("onFailure:$t")
                }

                override fun onResponse(
                    call: Call<List<Picture>>,
                    response: Response<List<Picture>>
                ) {
                    val data = ArrayList<String>()
                    var list = response.body()
                    list?.forEach {
                        data.add(it.imgurl)
                        println(it.imgurl)
                    }
                    imgLiveData.value = data
                }
            })
    }

    fun getImageData(): MutableLiveData<ArrayList<String>> {
        return imgLiveData
    }


}