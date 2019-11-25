package com.iffy.mianshi.pattern.VIIadapter.paicha

import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Di
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Huo
import com.iffy.mianshi.pattern.VIIadapter.paicha.type.Ling

//插板有两脚插孔 三个脚插头
class ChabanAdapter : ThreePin {
    var dianqi: TwoPin

    override var di: Di
    override var ling: Ling
    override var huo: Huo


    constructor(dianqi: TwoPin) {
        this.dianqi = dianqi
        this.di = Di()
        this.ling = dianqi.ling
        this.huo = dianqi.huo
    }

    constructor(dianqi: ThreePin) {
        this.dianqi = dianqi
        this.di = dianqi.di
        this.ling = dianqi.ling
        this.huo = dianqi.huo
    }

    override fun yitongdian() {
        println("插板已通电")
        dianqi.yitongdian()
    }

}