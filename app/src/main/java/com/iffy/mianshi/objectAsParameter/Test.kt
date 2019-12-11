package com.iffy.mianshi.objectAsParameter

fun main() {
    var student = Student("iffy")
    var teacher = Teacher()
    println(student.name)
    teacher.changeName(student)
    println(student.name)

}