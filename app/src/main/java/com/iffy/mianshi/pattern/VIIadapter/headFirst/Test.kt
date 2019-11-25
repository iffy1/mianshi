package com.iffy.mianshi.pattern.VIIadapter.headFirst


fun main() {
    var duck =
        DuckAdapter(WildTurkey())
    duck.quack()
    duck.fly()
}
