package antonioleiva.com.appkotlin.login

//MVP中的Presenter
//持有view 和 model的 控制对象 LoginViewListener和loginInteractorModel
class LoginPresenter(var loginViewListener: LoginViewListener?) {

    private val loginInteractorModel = LoginInteractorModel()


    fun validateCredentials(username: String, password: String) {

        //通知activiry 显示进度条
        loginViewListener?.showProgress()
        //找model验证用户名密码
        loginInteractorModel.login(username, password, object : OnLoginFinishedListener {
            //model验证用户名的结果 返回给 persenter
            override fun onUsernameError() {
                //presenter再调用activity
                loginViewListener?.apply {
                    setUsernameError()
                    hideProgress()
                }
            }

                override fun onPasswordError() {
                loginViewListener?.apply {
                    setPasswordError()
                    hideProgress()
                }
            }

            override fun onSuccess() {
                loginViewListener?.navigateToHome()
            }
        })
    }

    fun onDestroy() {
        loginViewListener = null
    }


}