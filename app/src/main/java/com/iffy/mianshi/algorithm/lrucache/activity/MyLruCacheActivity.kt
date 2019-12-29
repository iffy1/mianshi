package com.iffy.mianshi.algorithm.lrucache.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R
import leakcanary.AppWatcher


class MyLruCacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lrucache_recycler)
        val recyclerview = findViewById<RecyclerView>(R.id.LrcrecyclerView)
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layout

        var data = ArrayList<String>()
        data.add("https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/pa/srs_xb12_19082_600295.jpg")
        data.add("https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/pa/block_wf_1000xm3_0723_600703.jpg")
        data.add("https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/pa/block_nw_zx505_295398.jpg")
        data.add("https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/Di/block_a7rm4_1030_600703.jpg")
        data.add("https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/intelligent/block_xperia5_xpss.jpg")
        data.add("https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/av/block_z9g_xpss_600703.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_pic01.png")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_05a.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_05b.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_06.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_07.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_08.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_09_181102.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_10.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_11.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/lenses/e_lens/sel24f14gm/feature/sel24f14gm_yz01.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/headphone/wf_1000xm3/Product/img_wf_1000xm3_b1_fecture.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/headphone/wf_1000xm3/feature/p1a.jpg")
        data.add("https://www.sonystyle.com.cn/content/dam/sonystyle/products/headphone/wf_1000xm3/feature/p4.jpg")


        recyclerview.adapter = MyRecyclerAdapter(data, this)
        recyclerview.addItemDecoration(
            RecycleViewDivider(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        AppWatcher.objectWatcher.watch(this)
    }
}