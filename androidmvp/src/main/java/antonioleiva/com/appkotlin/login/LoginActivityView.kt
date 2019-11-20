package antonioleiva.com.appkotlin.login

import android.content.Intent
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import antonioleiva.com.appkotlin.R
import antonioleiva.com.appkotlin.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

//代码层次很清楚，View层接受用户的点击操作，回调Presenter层的相关接口，
// Presenter层再调用到Model层去执行登录操作，同时修改View层的Progress显示情况，
// Model层执行完登录操作之后，回调到Presenter层的对应接口，
// Presenter再去对View层的布局进行相应的修改

//MVP模式三个层次之间是通过两个接口（LoginViewListener OnLoginFinishedListener）来进行交互的

//Since the View and the Presenter work closely together,
// they need to have a reference to one another.
// To make the Presenter unit testable with JUnit,
// the View is abstracted and an interface for it used.

//MVP中的View
class LoginActivityView : AppCompatActivity() {

    private val presenter = LoginPresenter(object : LoginViewListener {
        //interface LoginViewListener
        override fun showProgress() {
            progress.visibility = View.VISIBLE
        }

        //interface LoginViewListener
        override fun hideProgress() {
            progress.visibility = View.GONE
        }

        //interface LoginViewListener
        override fun setUsernameError() {
            username.error = getString(R.string.username_error)
        }

        //interface LoginViewListener
        override fun setPasswordError() {
            password.error = getString(R.string.password_error)
        }

        //interface LoginViewListener
        override fun navigateToHome() {
            startActivity(Intent(this@LoginActivityView, MainActivity::class.java))
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button.setOnClickListener { validateCredentials() }
    }

    //验证数据交给presenter
    private fun validateCredentials() {
        presenter.validateCredentials(username.text.toString(), password.text.toString())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


}
