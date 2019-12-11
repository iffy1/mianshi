package com.iffy.mianshi.activityFragmentComunication

import android.app.Dialog
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.iffy.mianshi.R

import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.iffy.mianshi.bindService.Beband
import com.iffy.remoteservice.IIFFYAidlInterface


class MainActivity : AppCompatActivity(), IFragmentToActivity {
    lateinit var tv: TextView
    override fun OnreceiveMSGFromFragment(s: String) {
        tv.setText("${tv.text} $s")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        println("---------onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.activity_tv)
        var activity_btn = findViewById<Button>(R.id.activity_btn)
        var b = Bundle()
        b.putString("a", "哈哈哈哈")
        var fragmentStatic = supportFragmentManager.findFragmentById(R.id.fragmentA)
        fragmentStatic as MainFragment
        //匿名内部类要用object 关键字 或者Activity 实现 IFragmentToActivityu接口
        fragmentStatic.setCallBack(object : IFragmentToActivity {
            override fun OnreceiveMSGFromFragment(s: String) {
                tv.setText("${tv.text} s")
            }
        })

        var fragmentRuntime = MainFragment()
        fragmentRuntime.setCallBack(this)
        fragmentRuntime.arguments = b
        supportFragmentManager.beginTransaction().add(R.id.container, fragmentRuntime).commitAllowingStateLoss()

        activity_btn.setOnClickListener {
            fragmentRuntime.sendmsgToFragment("activity 跟 fragment动态 说")
            fragmentStatic.sendmsgToFragment("activity 跟 fragment静态 说")
        }

        var bangdingbtn = findViewById<Button>(R.id.activity_bangding_btn)
        bangdingbtn.setOnClickListener { bangding() }

        var tankuangbtn = findViewById<Button>(R.id.show_dialog_btn)
        tankuangbtn.setOnClickListener {
            //           var ab =  AlertDialog.Builder(this)
//            ab.setTitle("生命周期")
//            ab.setMessage("看Log查看弹框时候的周期")
//            ab.show()


            var dialog = Dialog(this)

            dialog.setTitle("生命周期")
            var ttv = TextView(this)
            ttv.setText("看Log查看弹框时候的周期")
            ttv.height = 500
            ttv.width = 500
            dialog.setContentView(ttv)
            dialog.show()
        }

        //Fragment 滑动 ViewPager + FragmentPagerAdatper
        var vp = findViewById<ViewPager>(R.id.fragment_pager)
        vp.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            var fs = ArrayList<MainFragment>()

            init {
                fs.add(MainFragment())
                fs.add(MainFragment())
                fs.add(MainFragment())
                fs.add(MainFragment())
            }

            override fun getItem(position: Int): Fragment {
                return fs[position]
            }

            override fun getCount(): Int {
                return fs.size
            }


        }

        //bind Service
        println("---------------------start bind service")
        var i = Intent()
        i.setClass(this, Beband::class.java)
        bindService(i, object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
                println("---------------------onServiceDisconnected")
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                p1 as Beband.MyBinder
                println("---------------------onServiceConnected ${p1.getService().guess()}")
            }
        }, Context.BIND_AUTO_CREATE)

    }

    fun bangding() {
        var connection = object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {

            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                //调用远程服务的AIDL方法
                println(IIFFYAidlInterface.Stub.asInterface(p1).msg)
            }

        }
        //通过Intent指定服务端的服务名称和所在包，与远程Service进行绑定
        //参数与服务器端的action要一致,即"服务器包名.aidl接口文件名"
        val intent = Intent("com.iffy.remoteservice.IIFFYAidlInterface")

        //Android5.0后无法只通过隐式Intent绑定远程Service
        //需要通过setPackage()方法指定包名
        intent.setPackage("com.iffy.remoteservice")

        //绑定服务,传入intent和ServiceConnection对象
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        println("---------onDestroy")
        super.onDestroy()
    }

    override fun onPause() {
        println("---------onPause")
        super.onPause()
    }

    override fun onRestart() {
        println("---------onRestart")
        super.onRestart()
    }

    override fun onResume() {
        var a = 123 and 0xffff000
        println("---------onResume $a")



        super.onResume()
    }

    override fun onStop() {
        println("---------onStop")
        super.onStop()
    }

    override fun onStart() {
        println("---------onStart")
        super.onStart()
    }


}
