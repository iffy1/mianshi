package com.iffy.mianshi.imageFrameWork

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.*
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.iffy.mianshi.R
import org.jetbrains.anko.doAsync
import java.io.File
import java.security.MessageDigest

class GlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        val imgA = findViewById<ImageView>(R.id.imageA)
        Glide
            .with(this)
            .load("https://www.2008php.com/2013_Website_appreciate/2013-07-15/20130715214140vLfjDvLfjD.jpg")
            .transition(GenericTransitionOptions.with(R.anim.item_alpha_in))//淡入动画效果
            .placeholder(R.mipmap.loading)//默认图片
            .error(R.mipmap.error)//出错后的图片
            .override(660, 160)
            .priority(Priority.LOW)
            .into(imgA)

        // 缓存参数说明
        // DiskCacheStrategy.NONE：不缓存任何图片，即禁用磁盘缓存
        // DiskCacheStrategy.ALL ：缓存原始图片 & 转换后的图片（默认）
        // DiskCacheStrategy.SOURCE：只缓存原始图片（原来的全分辨率的图像，即不缓存转换后的图片）
        //DiskCacheStrategy.AUTOMATIC//：只缓存转换后的图片（即最终的图像：降低分辨率后 / 或者转换后 ，不缓存原始图片

        val option = RequestOptions()
        option.diskCacheStrategy(DiskCacheStrategy.NONE)


        val imgB = findViewById<ImageView>(R.id.imageB)
        Glide
            .with(this)
            .load("https://i.guancha.cn/news/2016/01/15/20160115143101428.gif")
            .thumbnail(0.1f)
            .apply(option)//需要在Load之后 不然报错
            .priority(Priority.LOW)
            .into(imgB)

        val imgBB = findViewById<ImageView>(R.id.imageBB)
        Glide
            .with(this)
            .asBitmap()//强制转成不动的
            .load("https://i.guancha.cn/news/2016/01/15/20160115143101428.gif")
            .apply(option)//需要在Load之后 不然报错
            .priority(Priority.LOW)
            .into(imgBB)

        //设置跳过内存缓存
        //这意味着 Glide 将不会把这张图片放到内存缓存中去
        //这里需要明白的是，这只是会影响内存缓存！Glide 将会仍然利用磁盘缓存来避免重复的网络请求。

        val imgC = findViewById<ImageView>(R.id.imageC)
        Glide.with(this)
            .load("https://www.517japan.com/rimg_800x800/attachments/uploads/temp/img/201604/05/beb0b743c31e31033fff1c6ecabdb5fc.gif")
            .skipMemoryCache(true)//不使用内存作为缓存 但使用硬盘缓存
            .priority(Priority.HIGH)//设置下载优先级
            .into(imgC)

        val imgD = findViewById<ImageView>(R.id.imageD)
        Glide.with(this)
            .load("https://static.jstv.com/gather/hl/20180302/51/10218429138378134919.jpg")
            .circleCrop()
            .into(imgD)


        val imgE = findViewById<ImageView>(R.id.imageE)
        Glide.with(this)
            .load("https://static.jstv.com/gather/hl/20180302/51/10218429138378134919.jpg")
            .transform(MyTransformation(this))
            .into(imgE)

        val imgF = findViewById<ImageView>(R.id.imageF)
        Glide.with(this)
            .load("https://static.jstv.com/gather/hl/20180302/51/10218429138378134919.jpg")
            .preload()//预加载

        Glide.with(this)
            .load("https://static.jstv.com/gather/hl/20180302/51/10218429138378134919.jpg")
            .into(imgF)


        doAsync {
            //submit()方法的用法明显要比preload()方法复杂不少。这个方法只会下载图片，而不会对图片进行加载。当图片下载完成之后，我们可以得到图片的存储路径，以便后续进行操作。
            var futureTarget = Glide.with(this@GlideActivity)
                .asFile()//纯下载
                .load("https://static.jstv.com/gather/hl/20180302/51/10218429138378134919.jpg")
                .submit()

            println("下载图片的路径为 ${futureTarget.get().path}")
        }






        doAsync {
            //submit()方法的用法明显要比preload()方法复杂不少。这个方法只会下载图片，而不会对图片进行加载。
            // 当图片下载完成之后，我们可以得到图片的存储路径，以便后续进行操作。
            Glide.with(this@GlideActivity)
                .asFile()//纯下载
                .load("https://static.jstv.com/gather/hl/20180302/51/10218429138378134919.jpg")
                    //监听下载状态
                .listener(object : RequestListener<File> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<File>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        println("下载图片状态 失败")
                        return true
                    }

                    override fun onResourceReady(
                        resource: File?,
                        model: Any?,
                        target: Target<File>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        println("下载图片状态 成功， 的路径为 ${resource?.path}")
                        return true
                    }

                })
                .submit()


        }


    }

    class MyTransformation(var context: Context) : BitmapTransformation() {
        override fun transform(
            pool: BitmapPool,
            toTransform: Bitmap,
            outWidth: Int,
            outHeight: Int
        ): Bitmap {

            val out = toTransform.copy(toTransform.config, true)
            val canvas = Canvas(out)
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            val boder = 20f
            paint.color = Color.RED
            paint.textSize = 80f
            val text = "什么值得买"
            println("transform----------outWidth:$outWidth | paint.textSize: ${paint.textSize} | text.length: ${text.length}")
            canvas.drawText(
                text,
                toTransform.width - text.length * paint.textSize - boder,
                toTransform.height - boder,
                paint
            )
            return out
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {

        }

    }



}