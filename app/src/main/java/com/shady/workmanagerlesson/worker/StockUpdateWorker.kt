package com.shady.workmanagerlesson.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class StockUpdateWorker(context: Context, workparams: WorkerParameters): Worker(context, workparams) {
    override fun doWork(): Result {
        Log.d("worker", "Work Requested!!")
        return Result.success()
    }
}