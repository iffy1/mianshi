package antonioleiva.com.appkotlin.login

//MVP中的Presenter
//持有view 和 model的 控制对象 loginView和LoginModel
//Since the View and the Presenter work closely together,
// they need to have a reference to one another.
// To make the Presenter unit testable with JUnit,
// the View is abstracted and an interface for it used.
// The relationship between the Presenter and its corresponding View is
// defined in a Contract interface class, making the code more
// readable and the connection between the two easier to understand.
class LoginPresenter : LoginContract.Presenter {

    private val loginModel = LoginModel()
    var subscribers = ArrayList<LoginActivityView?>()


    override fun subscribe(loginView: LoginActivityView?) {
        subscribers.add(loginView)
        println("添加订阅 subscribers size:${subscribers.size}")
    }

    override fun unsubscribe(loginView: LoginActivityView?) {
        subscribers.remove(loginView)
        println("取消订阅 subscribers size:${subscribers.size}")
    }


    override fun validateCredentials(username: String, password: String) {

        //通知activiry 显示进度条
        subscribers.forEach {
            it?.showProgress()
        }

        //找model验证用户名密码
        loginModel.login(username, password, object : OnLoginFinishedListener {
            //model验证用户名的结果 返回给 presenter
            override fun onUsernameError() {
                //presenter再调用activity
                subscribers.forEach {
                    it?.apply {
                        setUsernameError()
                        hideProgress()
                    }
                }
            }

            override fun onPasswordError() {
                subscribers.forEach {
                    it?.apply {
                        setPasswordError()
                        hideProgress()
                    }
                }
            }

            override fun onSuccess() {
                subscribers.forEach {
                    it?.apply {
                        navigateToHome()
                    }
                }
            }
        })
    }
}