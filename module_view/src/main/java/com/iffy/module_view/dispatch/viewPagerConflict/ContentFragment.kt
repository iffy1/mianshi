package com.iffy.module_view.dispatch.viewPagerConflict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.iffy.module_view.R
import kotlinx.android.synthetic.main.content_fragment.view.*

class ContentFragment(var content: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content_fragment, container, false)
        view.neirong.text = content
        view as LinearLayout

        var mainActivity = activity as ViewPagerConflictActivity
        val imageview = mainActivity.getImageView()
        if (imageview.parent != null) {
            var p = imageview.parent
            p as ViewGroup
            p.removeView(imageview)
        }
        //多fragment共享一个view组件（可以是mapview）
        System.out.println("----imageview:$imageview")
        view.addView(imageview)
        return view
    }

}