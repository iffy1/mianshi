package antonioleiva.com.appkotlin.login

import android.os.Looper
import org.junit.Test

import org.junit.Assert.*

class LoginModelTest {

    @Test
    fun testLogin() {
        //Looper.prepare()
        println("-------------------AA")
        LoginModel().login("", "aa", object : OnLoginFinishedListener {
            override fun onUsernameError() {
                assert(true)
            }

            override fun onPasswordError() {
                fail()
            }

            override fun onSuccess() {
                fail()
            }
        })


    }

    @Test
    fun testPlus() {
        var result = LoginModel().plus(1, 1)
        assertEquals(2, result)
    }
}