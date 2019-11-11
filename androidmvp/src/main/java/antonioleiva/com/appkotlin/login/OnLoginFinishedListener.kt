package antonioleiva.com.appkotlin.login

//用于连接Model和presenter
interface OnLoginFinishedListener {
    fun onUsernameError()
    fun onPasswordError()
    fun onSuccess()
}