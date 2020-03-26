package com.iffy.module_animation.propertyAnimation.Interpolator

import android.util.Log
import android.view.animation.Interpolator

//accelerate_interpolator //设置动画为加速动画(动画播放中越来越快)
//decelerate_interpolator //设置动画为减速动画(动画播放中越来越慢)
//accelerate_decelerate_interpolator //设置动画为先加速在减速(开始速度最快 逐渐减慢)
//anticipate_interpolator //先反向执行一段，然后再加速反向回来（相当于我们弹簧，先反向压缩一小段，然后在加速弹出）
//anticipate_overshoot_interpolator //同上先反向一段，然后加速反向回来，执行完毕自带回弹效果（更形象的弹簧效果）
//bounce_interpolator  //执行完毕之后会回弹跳跃几段（相当于我们高空掉下一颗皮球，到地面是会跳动几下）
//cycle_interpolator //循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)
//linear_interpolator  //线性均匀改变
//overshoot_interpolator //加速执行，结束之后回弹

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
