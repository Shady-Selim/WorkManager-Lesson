package com.shady.workmanagerlesson.worker

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shady.workmanagerlesson.ui.main.MainActivity
import kotlin.contracts.contract
import kotlin.random.Random

class StockUpdateWorker(val context: Context, workparams: WorkerParameters): Worker(context, workparams) {
    override fun doWork(): Result {
        val randomValue = "%.2f".format(Random.nextDouble(0.10, 0.70))
        Log.d("worker", "Work Requested!! $randomValue")
        // Notification
        val name= "Stock Notification"
        var intent = Intent(context, MainActivity::class.java)
        val pendingActivity = PendingIntent.getActivity(context,0,intent, 0)
        val notification = NotificationCompat
            .Builder(context, "NOTIFICATION_CHANNEL_ID")
            .setTicker(name)
            .setSmallIcon(android.R.drawable.btn_star_big_on)
            .setContentTitle(name)
            .setContentIntent(pendingActivity)
            .setAutoCancel(true)
            .setContentText("Testing our Worker $randomValue")
            .build()
        val notificationManager = NotificationManagerCompat
            .from(context)
        notificationManager.notify(0, notification)

        val sharedPreferences = context.getSharedPreferences("stockSharedPreference",
            AppCompatActivity.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString("lastStockValue",randomValue)
            .apply()


        return Result.success()
    }
}