package com.iffy.mianshi.view.surfaceView

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.TextureView

class MTextureView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    TextureView(context, attrs, defStyleAttr, defStyleRes), TextureView.SurfaceTextureListener, Runnable {

    override fun run() {
        while (true) {

            var canv = surf.lockCanvas(null)
            canv.drawText("我是TextTureView", 100.0f + offset, 100.0f + offset, paint)
            surf.unlockCanvasAndPost(canv)
            offset++
            Log.e("iffy", "我在写字")
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0, 0)
    constructor(context: Context) : this(context, null, 0, 0)

    var paint = Paint()

    var offset = 0
    lateinit var surf: Surface

    init {
        paint.setColor(Color.CYAN)
        paint.textSize = 40f
        //不设置listener 不会显示
        setSurfaceTextureListener(this)
    }

    override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
    }

    override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
    }

    override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
        return true
    }

    override fun onSurfaceTextureAvailable(p0: SurfaceTexture?, p1: Int, p2: Int) {
        surf = Surface(p0)
        Thread(this).start()
    }
}