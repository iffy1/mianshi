package antonioleiva.com.appkotlin.login

//用于连接View和presenter
interface LoginViewListener {
    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToHome()
}