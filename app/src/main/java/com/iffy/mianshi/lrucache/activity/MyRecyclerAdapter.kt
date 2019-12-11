package com.iffy.mianshi.lrucache.activity


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.LruCache
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class MyRecyclerAdapter(var data: ArrayList<String>, var context: Context) :
    RecyclerView.Adapter<MyVH>() {
    //data里大概有20张图，如果maxSize小于20 lru会自动释放多余的图片，会导致浏览时候下载 删掉的图片
    var mlrucacher: MyLruCacher =
        MyLruCacher(10)
    private val UserAsyncTask = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.lru_recyclerview_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val img = mlrucacher.get(data[position])
        if (img != null) {
            holder.imgv.setImageBitmap(img)
        } else {
            println("下载新的图片 position:$position")
            if (UserAsyncTask) {
                //注意leak
                MyDonwloadPictureAsyncTask(position, context).execute()
            } else {
                //使用Anko库里的 异步方法
                doAsync {
                    var bitmap =
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.drawable.ic_launcher_background
                        )
                    val connection = URL(data[position]).openConnection()
                    connection as HttpURLConnection
                    //设置请求方式
                    connection.requestMethod = "GET"
                    connection.connect()
                    if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                        bitmap = BitmapFactory.decodeStream(connection.inputStream)
                    }
                    mlrucacher.put(data[position], bitmap)

                    uiThread {
                        notifyItemChanged(position)
                    }
                }
            }
        }
    }
    //非静态内部类注意防止leak
    inner class MyDonwloadPictureAsyncTask(var position: Int, var context: Context) :
        AsyncTask<Void, Void, Void>() {
        //弱引用防止leak
        private var weakContext: WeakReference<Context> = WeakReference(context)

        override fun doInBackground(vararg params: Void?): Void? {
            var bitmap =
                BitmapFactory.decodeResource(
                    weakContext.get()?.resources,
                    R.drawable.ic_launcher_background
                )
            val connection = URL(data[position]).openConnection()
            connection as HttpURLConnection
            //设置请求方式
            connection.requestMethod = "GET"
            connection.connect()
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(connection.inputStream)
            }
            mlrucacher.put(data[position], bitmap)
            return null
        }


        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            notifyItemChanged(position)
        }
    }

}


class MyLruCacher(maxSize: Int) : LruCache<String, Bitmap>(maxSize) {
    override fun entryRemoved(evicted: Boolean, key: String, oldValue: Bitmap, newValue: Bitmap?) {
        println("移除缓存图片key:$key")
    }
}




