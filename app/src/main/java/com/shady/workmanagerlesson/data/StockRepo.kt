package com.shady.workmanagerlesson.data

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.shady.workmanagerlesson.STOCK_SHARED_KEY
import com.shady.workmanagerlesson.ui.main.MainActivity
import com.shady.workmanagerlesson.worker.StockUpdateWorker
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class StockRepo {
    private val stockPrice = MutableLiveData<String>()
    fun returnStockChange(mainActivity: MainActivity): MutableLiveData<String> {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val oneTimeWorker = OneTimeWorkRequestBuilder<StockUpdateWorker>()
            .setConstraints(constraints)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        val periodicWorker = PeriodicWorkRequest
            .Builder(StockUpdateWorker::class.java, 15, TimeUnit.SECONDS)
            .build()
        val workManager = WorkManager.getInstance(mainActivity)
        workManager.enqueue(oneTimeWorker)
        workManager.getWorkInfoByIdLiveData(oneTimeWorker.id).observe(mainActivity,{
            if (it.state == WorkInfo.State.SUCCEEDED){
                stockPrice.postValue(it.outputData.getString(STOCK_SHARED_KEY))
            }
        })
        //WorkManager.getInstance().cancelAllWork()
        WorkManager.getInstance(mainActivity).enqueueUniquePeriodicWork(
            "periodicStockWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorker
            )
        //WorkManager.getInstance().cancelUniqueWork("periodicStockWorker")
        return stockPrice
    }
}