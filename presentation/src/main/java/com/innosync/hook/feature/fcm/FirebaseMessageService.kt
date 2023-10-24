package com.innosync.hook.feature.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.innosync.domain.usecase.firebase.FirebaseTokenInsertUseCase
import com.innosync.domain.usecase.sharedpreferences.SharedPreferencesGetUseCase
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class FirebaseMessageService : FirebaseMessagingService() {

    @Inject
    lateinit var firebaseTokenInsertUseCase: FirebaseTokenInsertUseCase

    @Inject
    lateinit var sharedPreferencesGetUseCase: SharedPreferencesGetUseCase


    private val serviceScope = CoroutineScope(Dispatchers.IO)
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("LOG", "onNewToken: $token")
        serviceScope.launch {
            firebaseTokenInsertUseCase.invoke(
                token
            ).onSuccess {
                Log.d(TAG, "onNewToken: success")
            }.onFailure {
                Log.d(TAG, "onNewToken: $it")
            }
        }
        // TODO( "서버에 저장해야함 " )
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "onMessageReceived Data: ${message.data} ")
        Log.d(TAG, "onMessageReceived Noti: ${message.notification} ")


        val title = message.notification?.title
        val body = message.notification?.body
//        Log.d(TAG, "onMessageReceived: ${message.notification?.tag}")
        var tag = ""
        runBlocking {
            sharedPreferencesGetUseCase.invoke().let {
                tag = it
                Log.d(TAG, "onMessageReceivedShared: $it")
            }
        }
//        Log.d(TAG, "onMessageReceivedqwewqee: ${message.data["user"]}")
//        Log.d(TAG, "onMessageReceived: $tag")
//
//        Log.d(TAG, "onMessageReceived: $title $body")
        // 알림 생성 및 표시
        if (message.data["user"] != tag) {
            createNotificationChannel()

            val intent = Intent(this, MainActivity::class.java) // 알림을 클릭했을 때 열릴 액티비티 지정
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )

            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val notificationBuilder = NotificationCompat.Builder(this, "hook_default_channels")
                .setSmallIcon(R.drawable.ic_launcher_foreground) // 알림 아이콘
                .setContentTitle(title) // 알림 제목
                .setContentText(body) // 알림 내용
                .setAutoCancel(true) // 알림을 클릭하면 자동으로 닫힘
                .setSound(defaultSoundUri) // 알림 소리
                .setContentIntent(pendingIntent) // 알림 클릭 시 실행될 Intent

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(0, notificationBuilder.build())
        }
    }

//    private fun createNotificationChannel() {
//        // Android 8.0 이상에서는 알림 채널을 만들어야 합니다.
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            val channelId = "hook_default_channel"
//            val channelName = "Hook Default Channel"
//            val channelDescription = "Hook Default Channel Description"
//            val channelImportance = NotificationManager.IMPORTANCE_HIGH//IMPORTANCE_DEFAULT
//            val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
//            notificationChannel.description = channelDescription
//            notificationChannel.enableLights(true)
//            notificationChannel.lightColor = Color.RED
//            notificationChannel.enableVibration(true)
//            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500)
//
//            val notificationManager = getSystemService(NotificationManager::class.java)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//    }
private fun createNotificationChannel() {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Hook Default Channel"
        val descriptionText = "Hook Default Channel Description"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("hook_default_channels", name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: 작동함")

    }

    override fun onDestroy() {
        super.onDestroy()
    }



    companion object {
        const val TAG = "LOG"
    }




}