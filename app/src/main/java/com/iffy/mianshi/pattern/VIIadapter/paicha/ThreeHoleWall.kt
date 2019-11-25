package com.iffy.mianshi.pattern.VIIadapter.paicha

//墙插只接受三脚插头
class ThreeHoleWall : ThreeHole {
    override fun pluginThreeHole(treePing: ThreePin) {
        treePing.yitongdian()
    }
}