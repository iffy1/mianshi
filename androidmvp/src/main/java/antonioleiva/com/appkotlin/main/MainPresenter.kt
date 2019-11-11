package antonioleiva.com.appkotlin.main

//Presenter持有 view(activity) 和 model(findItemsInteractorModel)的实例
//用于和view model交互


//mainView presenter---view
//MyPresenterOnClickListener presenter---adapter
//findItemsInteractorModel presenter---model
class MainPresenter(var mainView: MainView?) : MyPresenterOnClickListener {
    override fun onClick(s: String) {
        mainView?.showMessage(s)
    }

    //presenter---Model
    val findItemsInteractorModel = FindItemsInteractorModel()

    fun onResume() {
        mainView?.showProgress()
        //跟Model获取数据
        findItemsInteractorModel.findItems(::onItemsLoaded)
    }

    //拿到数据后 通知activity更新数据
    private fun onItemsLoaded(items: List<String>) {
        mainView?.apply {
            val adapter = MainAdapter(items, this@MainPresenter)
            setItems(adapter)
            hideProgress()
        }
    }

    fun onDestroy() {
        mainView = null
    }
}