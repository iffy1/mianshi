package com.iffy.module_animation.propertyAnimation.Interpolator

import android.util.Log
import android.view.animation.Interpolator


class MyInterpolator : Interpolator {
    companion object {
        var result: Double = 0.0
    }

    override fun getInterpolation(input: Float): Float {
        Log.e("iffy", "input: $input")
        when (input) {
            in 0.0..0.3 -> result += 0.01
            in 0.31..0.70 -> result += +0.02
            in 0.71..0.99 -> result += 0.01
        }
        if (result >= 1.0) {
            result = 1.0
        }

        Log.e("iffy", "result: $result")
        return result.toFloat()
        // 返回的result值 = 随着动画进度呈先减速后加速的变化趋势
    }

}
