package kr.purplebeen.big_future.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import kr.purplebeen.big_future.R
import kr.purplebeen.big_future.SignService
import kr.purplebeen.big_future.UserSignIn
import kr.purplebeen.big_future.utills.RetrofitUtil
import ninja.sakib.pultusorm.core.PultusORM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class LoginActivity : AppCompatActivity() {
    lateinit var pultusORM : PultusORM
    private var callbackManager: CallbackManager? = null
    private var request: GraphRequest? = null
    private var accessTokenTracker: AccessTokenTracker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        callbackManager = CallbackManager.Factory.create()
        val appPath : String = applicationContext.filesDir.absolutePath
        pultusORM = PultusORM("bigfuture.db", appPath)
        setLoginButton()
    }

    fun setLoginButton() {
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_education_history", "user_friends", "read_custom_friendlists"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("access token : ", loginResult.accessToken.token)
                var signService = RetrofitUtil.getLoginRetrofit(applicationContext).create(SignService::class.java)
                var call : Call<UserSignIn> = signService.siginIn()
                call.enqueue(object : Callback<UserSignIn> {
                    override fun onResponse(call: Call<UserSignIn>?, response: Response<UserSignIn>) {
                        if(response.body()!!.status.success) {
                            Log.d("user", response.body()!!.user.toString())
                            pultusORM.save(response!!.body()!!.user)
                            startActivity(Intent(this@LoginActivity, GraduationActivity::class.java))
                        }
                    }

                    override fun onFailure(call: Call<UserSignIn>?, t: Throwable?) {
                        Log.e("retrofit Error", t!!.message)
                        Toast.makeText(applicationContext, "네트워크를 확인해주세요!", Toast.LENGTH_LONG)
                    }

                })
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {
                Log.e("LoginErr", error.toString())
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)

    }
}
