package com.iffy.mianshi.objectAsParameter

fun main() {
    val student = Student("iffy")
    val teacher = Teacher()
    println(student.name)
    teacher.changeName(student)
    println(student.name)//结果student的名字被改成zhang san

}