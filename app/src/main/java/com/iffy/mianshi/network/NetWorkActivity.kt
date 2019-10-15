package com.iffy.mianshi.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.iffy.mianshi.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.HttpURLConnection


class NetWorkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.iffy.mianshi.R.layout.activity_network)
        /*-----------------volley--------------*/
        var volleyBtn = findViewById<Button>(R.id.volley_btn)
        var volleytv = findViewById<TextView>(R.id.volley_tv)
        var volleyimgView = findViewById<ImageView>(R.id.volley_image_view)

        volleyBtn.setOnClickListener {
            var requset = Volley.newRequestQueue(this)
            var jasonRequset =
                "https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&order=click&copy_right=-1&cate_id=19&&time_from=20190718&time_to=20190921&keyword=%E5%90%83%E9%B8%A1"
            var jr = JsonObjectRequest(Request.Method.GET, jasonRequset, null, Response.Listener {
                var result = it.getJSONArray("result").toString()
                //主线程
                volleytv.setText(result.subSequence(0, 200))
            }, null)
            requset.add(jr)
            var imgRequest = "https://s1.hdslb.com/bfs/static/jinkela/home/asserts/ic_launcher.png"
            var ir = ImageRequest(imgRequest, Response.Listener<Bitmap> {
                //主线程
                volleyimgView.setImageBitmap(it)
            }, 100, 100, Bitmap.Config.ALPHA_8, null)
            requset.add(ir)
        }
        /*-----------------volley--------------*/

        /*-----------------okhttp--------------*/
        var okhttpBtn = findViewById<Button>(R.id.okhttp_btn)
        var oktv = findViewById<TextView>(R.id.okhttp_tv)
        var okimgView = findViewById<ImageView>(R.id.okhttp_image_view)

        okhttpBtn.setOnClickListener {
            var jasonRequset =
                "https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&order=click&copy_right=-1&cate_id=19&&time_from=20190718&time_to=20190921&keyword=%E5%90%83%E9%B8%A1"
            var client = okhttp3.OkHttpClient()
            //第二步构建Request对象
            var request = okhttp3.Request.Builder().url(jasonRequset).build()
            //第三步构建Call对象
            var call = client.newCall(request)
            //第四步:异步get请求
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: okhttp3.Response) {
                    if (response.code == 200) {
                        //得到服务器返回的具体内容
                        val responseData = response.body?.string()
                        //获取数据
                        var jsonObject = JSONObject(responseData)
                        var jasonarray = jsonObject.getJSONArray("result")
                        //返回主线程
                        runOnUiThread { oktv.setText(jasonarray[0].toString().subSequence(0, 100)) }
                    }
                }
            })

            var imgpath = "https://s1.hdslb.com/bfs/static/jinkela/home/asserts/ic_launcher.png"
            var imgRequest = okhttp3.Request.Builder().url(imgpath).build()
            var imgcall = client.newCall(imgRequest)
            imgcall.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: okhttp3.Response) {
                    var res = response.body
                    var ips = res?.byteStream()
                    var btm = BitmapFactory.decodeStream(ips)
                    runOnUiThread {
                        okimgView.setImageBitmap(btm)
                    }
                }
            })
        }
        /*-----------------okhttp--------------*/

        /*-----------------retrofit2--------------*/

        var retrofitBtn = findViewById<Button>(R.id.retrofit_btn)
        var retrofittv = findViewById<TextView>(R.id.retrofit_tv)
        var retrofitimgView = findViewById<ImageView>(R.id.retrofit_image_view)
        retrofitBtn.setOnClickListener {
            var rf = Retrofit
                .Builder()
                .baseUrl("https://s.search.bilibili.com/cate/")
                .build()
            var rfrequest = rf.create(Retrofit_interface::class.java)
            var call = rfrequest.getCall("王者荣耀")
            call.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                    System.out.println("onFailure")
                }

                override fun onResponse(
                    call: retrofit2.Call<ResponseBody>,
                    response: retrofit2.Response<ResponseBody>
                ) {
                    var result = response.body()!!.string()
                    var jobj = JSONObject(result)
                    var array = jobj.getJSONArray("result")
                    retrofittv.setText(array[0].toString().subSequence(0, 100))
                }
            })


            var rfimg = Retrofit.Builder().baseUrl("https://s1.hdslb.com/").build()
            var imgresuest = rfimg.create(Retrofit_interface::class.java)
            var donwloadcall = imgresuest.download("bfs/static/jinkela/home/asserts/ic_launcher.png")
            donwloadcall.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                    System.out.println("onFailure")
                }

                override fun onResponse(
                    call: retrofit2.Call<ResponseBody>,
                    response: retrofit2.Response<ResponseBody>
                ) {
                    System.out.println(Thread.currentThread())
                    retrofitimgView.setImageBitmap(BitmapFactory.decodeStream(response.body()!!.byteStream()))
                }
            })
        }


        /*-----------------retrofit2--------------*/

    }
}