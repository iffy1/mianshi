package com.iffy.mianshi.pattern.VIIadapter.paicha

import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Di
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Huo
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Ling

class LightWithThreePin : ThreePin {
    override var di =Di()
    override var ling = Ling()
    override var huo = Huo()
    override fun yitongdian() {
        println("我是三脚灯，我亮了")
    }

}