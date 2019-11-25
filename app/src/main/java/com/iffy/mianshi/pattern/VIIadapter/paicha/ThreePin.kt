package com.iffy.mianshi.pattern.VIIadapter.paicha

import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Di
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Huo
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Ling

//三针插头 有三条线
interface ThreePin:TwoPin {
    var di: Di
}