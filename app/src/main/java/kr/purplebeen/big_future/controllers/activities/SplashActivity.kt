package kr.purplebeen.big_future.controllers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import com.facebook.AccessToken
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kr.purplebeen.big_future.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Facebook SDK initialize
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)

        FirebaseMessaging.getInstance().subscribeToTopic("test")
        FirebaseInstanceId.getInstance().getToken();

        val handler = Handler()
        handler.postDelayed({
            val token : AccessToken? = AccessToken.getCurrentAccessToken()
            if (token != null) {
                Toast.makeText(applicationContext, "자동로그인이 되었습니다.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }, 3000)
    }
}
