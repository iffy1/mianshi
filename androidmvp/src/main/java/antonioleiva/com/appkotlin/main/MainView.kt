package antonioleiva.com.appkotlin.main

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setItems(adapter: MainAdapter)
    fun showMessage(message: String)
}