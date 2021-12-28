package com.shady.workmanagerlesson.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.shady.workmanagerlesson.NOTIFICATION_CHANNEL_ID
import com.shady.workmanagerlesson.STOCK_NOTIFICATION_NAME
import com.shady.workmanagerlesson.STOCK_SHARED_KEY
import com.shady.workmanagerlesson.STOCK_SHARED_PREF
import com.shady.workmanagerlesson.ui.main.MainActivity
import kotlin.random.Random

class StockUpdateWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    override fun doWork(): Result {
        val randomValue = "%.2f".format(Random.nextDouble(0.10, 0.70))
        Log.d("worker", "Work Requested!! $randomValue")
        // Notification
        val name= STOCK_NOTIFICATION_NAME
        val intent = Intent(context, MainActivity::class.java)
        val pendingActivity = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
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

        val sharedPreferences = context.getSharedPreferences(
            STOCK_SHARED_PREF,
            AppCompatActivity.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(STOCK_SHARED_KEY,randomValue)
            .apply()
        val outPutData = workDataOf(STOCK_SHARED_KEY to randomValue)

        return Result.success(outPutData)
    }
}