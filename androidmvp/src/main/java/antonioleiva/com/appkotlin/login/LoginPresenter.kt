package antonioleiva.com.appkotlin.login

interface LoginPresenter {
    fun validateCredentials(username: String, password: String)
}