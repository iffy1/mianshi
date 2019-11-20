package antonioleiva.com.appkotlin.main

interface MainContract {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setItems(adapter: MainAdapter)
        fun showMessage(message: String)
    }

    interface MainPresenter{
        fun onResume()
        fun onDestroy()
    }
}