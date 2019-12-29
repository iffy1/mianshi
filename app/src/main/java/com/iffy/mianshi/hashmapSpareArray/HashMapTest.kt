package com.iffy.mianshi.hashmapSpareArray


var s = "aaa"
var a = 1
var b = 3
class A{
    override fun equals(other: Any?): Boolean {
        return true
    }

}
fun main() {
    var aa = A()
    var bb = aa
    var cc = A()

    println(aa.hashCode())
    println(bb.hashCode())

    var m = HashMap<String,String>()



    m.put("a", "rrr1");
    m.put("b", "tt9");
    m.put("c", "tt8");
    m.put("d", "g7");
    m.put("e", "d6");
    m.put("f", "d4");
    m.put("g", "d4");
    m.put("h", "d3");
    m.put("i", "d2");
    m.put("j", "d1");
    m.put("k", "1");
    m.put("o", "2");
    m.put("p", "3");
    m.put("q", "4");
    m.put("r", "5");
    m.put("s", "6");
    m.put("t", "7");
    m.put("u", "8");
    m.put("v", "9");

    print(m)

}

