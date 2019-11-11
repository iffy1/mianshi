package antonioleiva.com.appkotlin.main

import antonioleiva.com.appkotlin.postDelayed

class FindItemsInteractorModel {

    fun findItems(callback: (List<String>) -> Unit) {
        postDelayed(2000) { callback(createArrayList()) }
    }

    private fun createArrayList(): List<String> = (1..10).map { "Item $it" }
}