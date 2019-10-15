package com.iffy.mianshi.activity与fragment通信

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iffy.mianshi.R


class MainFragment : Fragment(), IActivityToFragment {
    lateinit var tv: TextView
    lateinit var btn: Button
    lateinit var callback:IFragmentToActivity

    fun setCallBack(cb:IFragmentToActivity){
        callback = cb
    }

    override fun sendmsgToFragment(s: String) {
        tv.setText("${tv.text}+$s")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        var msg = arguments?.get("a")
        tv = view.findViewById(R.id.fragment_tv)
        tv.setText("${tv.text}+$msg")

        btn = view.findViewById(R.id.fragment_btn)
        btn.setOnClickListener {
            callback.OnreceiveMSGFromFragment("fragment 对 activity 说 哈哈")
        }
        return view
    }

}