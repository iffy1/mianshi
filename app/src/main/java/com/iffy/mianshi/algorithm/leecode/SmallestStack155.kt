package com.iffy.mianshi.algorithm.leecode

class MinStack() {
    lateinit var data: ArrayList<Int>

    /** initialize your data structure here. */


    fun push(x: Int) {
        data.add(x)

    }

    fun pop() {
        if (data.size >= 1) {
            data.removeAt(data.size - 1)
        }

    }

    fun top(): Int {
        return data[data.size - 1]

    }

    fun getMin(): Int {
        var r = data.min()
         if(r != null){
             return r
         }
            return -1

    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */