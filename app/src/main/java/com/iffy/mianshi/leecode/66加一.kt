package com.iffy.mianshi.leecode


class Solution {
    fun plusOne(digits: IntArray): IntArray {
        //全是9的话位数加1
        var fullNine = true
        digits.forEach{
            if(it != 9){
                fullNine = false
            }
        }

        if(fullNine){
            var newd = IntArray(digits.size + 1)
            newd[0] = 1
            return newd
        }
        //全是9的话位数加1


        if (digits[digits.size - 1] == 9) {
            var newd = IntArray(digits.size)
            //最后一位设置为0
            //newd[newd.size - 1] = 0

            digits.reverse()
            var di = 1
            var found = false
            var firstFound = false
            digits.forEach {
                if (it == 9 && !found) {
                    //遇到9设置为0
                    newd[newd.size - di] = 0
                } else if (!firstFound) {
                    found = true
                    firstFound = true
                    newd[newd.size - di] = it + 1


                } else {
                    newd[digits.size - di] = it
                }
                di++
            }
            return newd
        } else {
            digits[digits.size - 1] += 1
            return digits
        }
    }
}



