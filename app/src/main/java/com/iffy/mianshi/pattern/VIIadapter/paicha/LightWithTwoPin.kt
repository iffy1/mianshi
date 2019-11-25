package com.iffy.mianshi.pattern.VIIadapter.paicha

import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Huo
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Ling

class LightWithTwoPin : TwoPin {
    override var ling = Ling()
    override var huo = Huo()
    override fun yitongdian() {
        println("我是两脚灯，我亮了")
    }

}