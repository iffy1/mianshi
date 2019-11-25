package com.iffy.mianshi.pattern.VIIadapter.paicha

//两脚插头灯 需要转换器才能插到三脚墙插上
//三脚插头灯 可以直接插墙插 也能插插板
//三脚插板   有两孔插座也有三孔插座
//三孔墙插
fun main() {

    println("part1 两脚灯->转换器->三孔墙插：")

    //两插头灯对象
    var lightTwoPin = LightWithTwoPin()
    //两孔台灯插入 2转3 转换器
    var chaban = ChabanAdapter(lightTwoPin)
    //三孔插座
    var threeHoleWall = ThreeHoleWall()
    //将转换器插入三孔插座
    threeHoleWall.pluginThreeHole(chaban)



    println("part2 三脚灯->三孔墙插：")
    //三角灯
    var lightThreePin = LightWithThreePin()
    //将三插头灯直接插入三孔墙插
    threeHoleWall.pluginThreeHole(lightThreePin)

    println("part3 三脚灯->转换器->三孔墙插：")
    chaban = ChabanAdapter(lightThreePin)
    threeHoleWall.pluginThreeHole(chaban)





    //三孔插座是不能插入两插头灯的
    //threeHoleWall.pluginTheeHole(lightTwoPin)



}