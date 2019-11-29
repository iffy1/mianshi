package com.iffy.mianshi.rxjava.retrofitUtil

//data class Translation(
//    var content: Content,
//    var status: Int
//) {
//    data class Content(
//        var ciba_out: String,
//        var ciba_use: String,
//        var err_no: Int,
//        var from: String,
//        var `out`: String,
//        var to: String,
//        var vendor: String
//    )
//
//    fun show() {
//        println(content.out)
//    }
//}

class Translation {
    var content: Content? = null
    var status: Int = 0

    class Content {
        var ciba_out: String? = null
        var ciba_use: String? = null
        var err_no: Int = 0
        var from: String? = null
        var out: String? = null
        var to: String? = null
        var vendor: String? = null
        val word_mean: List<String>? = null
    }

    fun show() {
        println(content?.out)
    }
}


