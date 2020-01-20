import android.content.Context


object DPUnitUtil {


    fun dip2px(context: Context, dp: Float): Float {
        var resources = context.getResources()
        var metrics = resources.getDisplayMetrics()
        var px = dp * (metrics.densityDpi / 160f)
        return px
    }

    fun px2dip(context:Context, px:Int): Float {
        var resources = context.getResources()
        var metrics = resources.getDisplayMetrics()
        var dp = px / (metrics.densityDpi / 160f)
        return dp
    }


    fun px2sp(context: Context, px: Float): Float {
        var scaledDensity = context.getResources().getDisplayMetrics().scaledDensity
        return px / scaledDensity
    }


    fun sp2px(context: Context, sp: Float): Float {
        var scaledDensity = context.getResources().getDisplayMetrics().scaledDensity
        return sp * scaledDensity
    }

}
