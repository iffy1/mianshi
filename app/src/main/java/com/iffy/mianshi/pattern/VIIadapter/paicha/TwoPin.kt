package com.iffy.mianshi.pattern.VIIadapter.paicha

import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Huo
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Ling

//两针插头有两条线
interface TwoPin {
    var huo: Huo
    var ling: Ling
    fun yitongdian()
}