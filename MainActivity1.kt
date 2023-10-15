package com.example.amlab2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
import android.provider.Settings.EXTRA_APP_PACKAGE
import androidx.core.app.NotificationCompat
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //알람채널 생성
        createNotificationChannel("channelId1", "default channel")
        createNotificationChannel("channelId2", "ad channel")
        //button 클릭횟수
        var i = 0

        //알림 보여주기 버튼 클릭시 실행
        val showNotification = findViewById<Button>(R.id.notify1)
        showNotification.setOnClickListener {

            i++
            val builder = NotificationCompat.Builder(this, "channelId1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification Lab.")
                .setContentText("Notification # $i")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory("default channel")

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(1, builder.build())
            }
        }

        //입력한 값을 버튼 클릭시 실행
        val editShowNotification = findViewById<Button>(R.id.notify2)
        editShowNotification.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editTextNotification)
            val builder = NotificationCompat.Builder(this, "channelId2")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification Lab2.")
                .setContentText(editText.text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory("ad channel")

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(2, builder.build())
            }
        }

        //setting버튼 클릭시 실행
        val settingButton = findViewById<Button>(R.id.settings)
        settingButton.setOnClickListener {
            val intent = Intent(ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(EXTRA_APP_PACKAGE, packageName);
            startActivity(intent)
        }
    }

    //채널
    private fun createNotificationChannel(channelId: String, channelName: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Notification Lab."
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}