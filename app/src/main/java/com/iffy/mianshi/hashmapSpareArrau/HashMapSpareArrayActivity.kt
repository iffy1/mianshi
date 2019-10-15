package com.iffy.mianshi.hashmapSpareArrau

import android.os.Bundle
import android.util.SparseArray
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class HashMapSpareArrayActivity : AppCompatActivity() {
    val hash = HashMap<Int, Xiaoming>()
    lateinit var blackBoard :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hashmap_sparearray)
        val btn_hash = findViewById<Button>(R.id.btn_hash_map)
        val btn_sparse = findViewById<Button>(R.id.btn_sparse_array)
         blackBoard = findViewById<TextView>(R.id.blackboard)

        btn_hash.setOnClickListener {
            val cur = System.currentTimeMillis()
            val count = 100000
            initHashData(count)
            val result = System.currentTimeMillis() - cur
            blackBoard.text = "${blackBoard.text} \n hash map add $count xiaoming use $result ms \n"

            var xiaoming999orig =hash[9999] as Xiaoming
            var xiaoming9999new = Xiaoming("Xiaoming 9999", 99990)

            blackBoard.text = "${blackBoard.text} \n hash map 's xiaoming 9999 's name ${xiaoming999orig.name} age: ${xiaoming999orig.age}"

            blackBoard.text = "${blackBoard.text} \n 检查原xiaoming"
            isXiaomingExist(xiaoming999orig)
            blackBoard.text = "${blackBoard.text} \n 检查新建xiaoming 名字和年龄一样"
            isXiaomingExist(xiaoming9999new)


        }

        btn_sparse.setOnClickListener {
            val cur = System.currentTimeMillis()
            val count = 100000
            initSparseArray(count)
            val result = System.currentTimeMillis() - cur
            blackBoard.text = "${blackBoard.text} \n SparseArray add $count xiaoming use $result ms"
        }
    }

    private fun isXiaomingExist(xiaoming:Xiaoming){
        var has = hash.containsValue(xiaoming)
        if(has){
            blackBoard.text = "${blackBoard.text} \n hash map has xiaoming 9999 \n"
        }else{
            blackBoard.text = "${blackBoard.text} \n hash map doesn't has xiaoming 9999 \n"
        }
    }

    private fun initHashData(count: Int): HashMap<Int, Xiaoming> {
        println("hash Start")
        for (i in 1..count) {
            hash.put(i, Xiaoming("Xiaoming $i", 10 * i))
        }
        println("hash End")
        return hash
    }

    private fun initSparseArray(count: Int): SparseArray<Xiaoming> {
        println("Sparse Start")
        val sparse = SparseArray<Xiaoming>()
        for (i in 1..count) {
            sparse.put(i, Xiaoming("Xiaoming $i", 10 * i))
        }
        println("Sparse End")
        return sparse
    }

    class Xiaoming(var name: String, var age: Int) {
    }
}