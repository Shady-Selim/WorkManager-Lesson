package com.shady.workmanagerlesson.ui.main

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shady.workmanagerlesson.R
import com.shady.workmanagerlesson.StockApplication

class MainActivity : AppCompatActivity() {
    private val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var stockTV: TextView
    private lateinit var getStockUpdateBtn: Button
    /*private val sharedPreference: SharedPreferences by lazy {
        StockApplication().appSharedPreferences
    }*/
    lateinit var sharedPreference: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference = this.getSharedPreferences("stockSharedPreference", MODE_PRIVATE)
        stockTV = findViewById(R.id.tvStock)
        stockTV.text = sharedPreference.getString(
            "lastStockValue", "First Run")

        getStockUpdateBtn = findViewById(R.id.btnGetStockUpdate)
        getStockUpdateBtn.setOnClickListener {
             vm.returnStockChange(this)
        }


    }
}