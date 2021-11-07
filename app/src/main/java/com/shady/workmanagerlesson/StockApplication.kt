package com.shady.workmanagerlesson

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.os.Build

class StockApplication: Application() {
    lateinit var appSharedPreferences: SharedPreferences
    override fun onCreate() {
        super.onCreate()
        //appSharedPreferences = this.getSharedPreferences("stockSharedPreference", MODE_PRIVATE)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name= "Stock Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("NOTIFICATION_CHANNEL_ID",
                name, importance)
            val notificationManager : NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}