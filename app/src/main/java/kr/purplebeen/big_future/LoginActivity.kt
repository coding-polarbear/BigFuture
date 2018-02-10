package kr.purplebeen.big_future

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.facebook.*
import com.facebook.login.widget.LoginButton
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class LoginActivity : AppCompatActivity() {
    private var callbackManager: CallbackManager? = null
    private var request: GraphRequest? = null
    private var accessTokenTracker: AccessTokenTracker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        callbackManager = CallbackManager.Factory.create()
    }

    fun setLoginButton() {
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_education_history", "user_friends", "read_custom_friendlists"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("access token : ", loginResult.accessToken.token)

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
