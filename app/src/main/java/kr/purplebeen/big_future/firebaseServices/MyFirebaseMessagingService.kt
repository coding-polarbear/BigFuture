package kr.purplebeen.big_future.firebaseServices

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kr.purplebeen.big_future.controllers.activities.MainActivity
import kr.purplebeen.big_future.R

/**
 * Created by baehyeonbin on 2018. 2. 11..
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "FirebaseMessageService"

    //start receive_message

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        //        super.onMessageReceived(remoteMessage);
        val messageBody = remoteMessage!!.data["message"]
        val title = remoteMessage.data["title"]
        sendNotification(messageBody!!, title!!)
    }

    private fun sendNotification(messageBody: String, title: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /*request code*/, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title + "님 의 메시지입니다.")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}