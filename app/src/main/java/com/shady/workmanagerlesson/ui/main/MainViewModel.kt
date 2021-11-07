package com.shady.workmanagerlesson.ui.main

import androidx.lifecycle.ViewModel
import com.shady.workmanagerlesson.data.StockRepo

class MainViewModel: ViewModel() {
    private val repo = StockRepo()
    fun returnStockChange(mainActivity: MainActivity) =
        "%.2f".format(repo.returnStockChange(mainActivity))
}