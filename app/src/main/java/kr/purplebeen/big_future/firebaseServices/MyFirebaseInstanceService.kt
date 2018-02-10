package kr.purplebeen.big_future.firebaseServices

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by baehyeonbin on 2018. 2. 11..
 */
class MyFirebaseInstanceService  : FirebaseInstanceIdService() {
    private val TAG = "MyFirebaseIDService"

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)
    }
}