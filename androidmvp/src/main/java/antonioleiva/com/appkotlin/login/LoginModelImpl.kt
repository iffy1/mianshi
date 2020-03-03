package antonioleiva.com.appkotlin.login

import antonioleiva.com.appkotlin.postDelayed

//MVP中的Model
class LoginModelImpl : LoginModel {
    override fun login(username: String, password: String, listener: OnLoginFinishedListener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        postDelayed(2000) {
            when {
                username.isEmpty() -> listener.onUsernameError()
                password.isEmpty() -> listener.onPasswordError()
                else -> listener.onSuccess()
            }
        }
    }
}