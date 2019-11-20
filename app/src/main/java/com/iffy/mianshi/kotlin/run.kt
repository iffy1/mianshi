package com.iffy.mianshi.kotlin

//Kotlin扩展函数run,with,let,also和apply的使用和区别
//五个扩展函数当中它们的特性也是十分的简单，无非也就是接收者和返回值的不同。
// 对于with,T.run,T.apply接收者是this，而T.let和T.also接受者是it；
// 对于with,T.run,T.let返回值是作用域的最后一个对象（this）,
// 而T.apply和T.also返回值是调用者本身(itself)。


//函数名 	实现
//let 	fun T.let(block: (T) -> R): R = block(this)
//with 	fun with(receiver: T, block: T.() -> R): R = receiver.block()
//run 	fun T.run(block: T.() -> R): R = block()
//apply fun T.apply(block: T.() -> Unit): T { block(); return this }
//also 	fun T.also(block: (T) -> Unit): T { block(this); return this }


fun main() {
    Student("Zhang").let {
        it.cry()
        it.speak()
    }

    //在run函数当中它不仅仅只是一个作用域，他还有一个返回值。他会返回在这个作用域当中的最后一个对象。
    run {
        val s = Student("Li")
        s.speak()
        s
    }.cry()

    //例如现在有这么一个场景，用户领取app的奖励，如果用户没有登录弹出登录dialog，
    // 如果已经登录则弹出领取奖励的dialog。我们可以使用以下代码来处理这个逻辑。
    var isHuiyuan = true
    run {
        if (isHuiyuan) Student("会员") else Student("非会员")
    }.speak()

    Student("wang").run {
        speak()
        cry()
    }

    Student("huang").let {
        it.speak()
        it.cry()
    }


    Student("liu").apply {
        speak()
        cry()
    }.speak()

}

//给student扩展cry方法
fun Student.cry() {
    println("${this.name}在哭")
}


class Student(var name: String) {

    fun speak() {
        println("$name 在说话")
    }
}