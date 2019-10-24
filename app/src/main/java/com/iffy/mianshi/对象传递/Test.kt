package com.iffy.mianshi.对象传递

fun main() {
    var student = Student("iffy")
    var teacher = Teacher()
    println(student.name)
    teacher.changeName(student)
    println(student.name)

}