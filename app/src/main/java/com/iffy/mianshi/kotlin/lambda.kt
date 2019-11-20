package com.iffy.mianshi.kotlin


//Lambda表达式总是被大括号括着
fun main() {

    wucanhanshu()
    val resulta = youcanhanshuA(1, 1)
    println("有参函数A的结果是${resulta}")


    val resultb = youcanhanshuB(2, 2)
    println("有参函数B的结果是${resultb}")


    val resultc = testlambdaA(1) { a, b ->
        a - b
    }
    println("函数作为参数C的结果是${resultc}")


    testlambdaB(3) {
        speak("hello")
        cry()
    }


}

//  1. 无参数的情况 ： 闭包
val wucanhanshu = { println("我是无参函数") }

//2. 有参数的情况
val youcanhanshuA: (Int, Int) -> Int = { a, b -> a + b }

//2. 可等价于 此种写法：即表达式的返回值类型会根据操作的代码自推导出来。
val youcanhanshuB = { a: Int, b: Int -> a + b }

//3 lambda表达式作为函数中的参数的时候，这里举一个例子
//当Lambda表达式作为其一个参数时，只为其表达式提供了参数类型与返回类型，所以，我们在调用此高阶函数的时候我们要为该Lambda表达式写出它的具体实现。
fun testlambdaA(i: Int, c: (a: Int, b: Int) -> Int): Int {
    return i + c(1, 1)
}

//Say.() 执行Say里面的一些函数
// Lambda表达式作为接收者类型
//要用Lambda表达式作为接收者类型的前提是接收着类型可以从上下文中推断出来。
fun testlambdaB(i: Int, sayAction: Say.() -> Unit) {
    val s = Say(i)
    s.sayAction()
}


class Say(var i:Int) {
    fun speak(s: String) {
        for(i in 0..i){
            println("I am speaking:$s")
        }

    }

    fun cry() {
        for(i in 0..i){
            println("I am crying")
        }

    }

}



