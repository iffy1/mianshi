package antonioleiva.com.appkotlin.login

interface LoginModel {
    interface OnLoginCallBack {
        fun onUsernameError()
        fun onPasswordError()
        fun onSuccess()
    }
    fun login(userName:String,passWord:String,listener: OnLoginFinishedListener)
}