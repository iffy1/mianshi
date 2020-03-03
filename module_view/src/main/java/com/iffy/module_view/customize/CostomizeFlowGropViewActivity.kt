package com.iffy.module_view.customize

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.iffy.module_base.BaseActivity
import com.iffy.module_view.R

class CostomizeFlowGropViewActivity:BaseActivity() {
    override fun getContentId(): Int {
       return R.layout.activity_customize_flow_grop_view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}